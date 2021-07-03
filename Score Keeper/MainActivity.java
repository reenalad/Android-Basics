package com.example.reena.scorekeeper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //variable to hold the score for Player 1
    double scorePlayer1 = 0;
    //variable to hold the score for player 2
    double scorePlayer2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //increment score for Player 1 by 1 when the win button is clicked
    public void winPlayer1(View view) {
        scorePlayer1 = scorePlayer1 + 1;
        displayForPlayer1(scorePlayer1);
    }

    //display current score for Player 1 (no changes)
    public void losePlayer1(View view) {
        displayForPlayer1(scorePlayer1);
    }

    //increment score for Player 1 by 0.5 when the draw button is clicked
    public void drawPlayer1(View view) {
        scorePlayer1 = scorePlayer1 + 0.5;
        displayForPlayer1(scorePlayer1);
    }

    //display the score for Player 1
    public void displayForPlayer1(double score) {
        TextView scoreView = (TextView) findViewById(R.id.player1_score);
        scoreView.setText(String.valueOf(score));
    }

    //increment score for Player 2 by 1 when the win button is clicked
    public void winPlayer2(View view) {
        scorePlayer2 = scorePlayer2 + 1;
        displayForPlayer2(scorePlayer2);
    }

    //display the current score for Player 2 (no changes)
    public void losePlayer2(View view) {
        displayForPlayer2(scorePlayer2);
    }

    //increment score for Player 2 by 0.5 when the draw button is clicked
    public void drawPlayer2(View view) {
        scorePlayer2 = scorePlayer2 + 0.5;
        displayForPlayer2(scorePlayer2);
    }

    //displays the score for Player 2
    public void displayForPlayer2(double score) {
        TextView scoreView = (TextView) findViewById(R.id.player2_score);
        scoreView.setText(String.valueOf(score));
    }


    //reset the scores for both players to 0
    public void reset(View view) {
        scorePlayer1 = 0;
        scorePlayer2 = 0;
        displayForPlayer1(scorePlayer1);
        displayForPlayer2(scorePlayer2);
    }
}
