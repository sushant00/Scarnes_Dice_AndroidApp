package com.example.scarnesdice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/*
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
*/

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private int userScore;
    private int userTurnScore;
    private int comScore;
    private int comTurnScore;
    private int currentRoll ;
    Random random;
    int[] images = {R.drawable.dice1, R.drawable.dice2, R.drawable.dice3, R.drawable.dice4, R.drawable.dice5, R.drawable.dice6};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userTurnScore = 0;
        userScore = 0;
        comTurnScore = 0;
        comScore = 0;
        random = new Random();
        Button hold = (Button)findViewById(R.id.holdbutton);
        hold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userScore += userTurnScore;
                Toast.makeText(getApplicationContext(), "You turn score is "+Integer.toString(userTurnScore), Toast.LENGTH_SHORT).show();
                userTurnScore = 0;
                changeTextView();
                computerTurn();
            }
        });



        Button roll = (Button)findViewById(R.id.rollbutton);
        roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentRoll = Roll();
                if(currentRoll == 1) {
                    userTurnScore = 0;
                    Toast.makeText(getApplicationContext(), "You turn score is 0", Toast.LENGTH_SHORT).show();
                    changeTextView();
                    computerTurn();
                    return;
                }
                else {
                    userTurnScore += currentRoll;

                }
                changeTextView();
                if(userScore + userTurnScore > 100) {
                    Toast.makeText(getApplicationContext(), "YOU WON!!!", Toast.LENGTH_LONG).show();
                    userTurnScore = 0;
                    comTurnScore = 0;
                    userScore = 0;
                    comScore = 0;
                    changeTextView();
                }
            }
        });


        Button reset = (Button)findViewById(R.id.resetbutton);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Reset", Toast.LENGTH_SHORT).show();
                userTurnScore = 0;
                comTurnScore = 0;
                userScore = 0;
                comScore = 0;
                changeTextView();
            }
        });
    }


    public int Roll() {

        ImageView dice = (ImageView)findViewById(R.id.imgView);
        int die = 1 + random.nextInt(6);
        dice.setImageResource(images[die-1]);
        return die;
    }

    public void computerTurn() {
        //take moves and update the screen for every move made

        int current = Roll();
        comTurnScore += current;
        while(current != 1 && comTurnScore < 20) {
            changeTextView();
            current = Roll();
            comTurnScore += current;
        }
        if(current == 1) {
            comTurnScore = 0;
        }
        comScore+= comTurnScore;
        changeTextView();
        comTurnScore = 0;
        Toast.makeText(getApplicationContext(), "Computer scored: " + Integer.toString(comTurnScore), Toast.LENGTH_SHORT).show();
        if (comScore >= 100){
            Toast.makeText(getApplicationContext(), "YOU LOST!!!", Toast.LENGTH_LONG).show();
            userScore = 0;
            userTurnScore = 0;
            comTurnScore = 0;
            comScore = 0;
            changeTextView();

        }
    }




    public void changeTextView() {

        TextView textView = (TextView)findViewById(R.id.scoreboard);
        textView.setText("Final User Score: " + userScore + " User's Current Turn Score: " + userTurnScore +"Final Com Score: " + comScore + " Com's Current Turn Score: " + comTurnScore);
    }


}
