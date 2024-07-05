package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameDisplay extends AppCompatActivity {

    private TicTacToeboard newBoard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_display);

        Button afreshButton = findViewById(R.id.afresh_button);
        Button homeButton = findViewById(R.id.home_button);
        TextView playerDisplay = findViewById(R.id.player_display);
        String[] playerNames = getIntent().getStringArrayExtra("Player Names");

        //these buttons will appear when the game ends.
        afreshButton.setVisibility(View.GONE);
        homeButton.setVisibility(View.GONE);

        //to assign the name at the starting of the game.
        if(playerNames != null){
            playerDisplay.setText((String.format("%s's turn", playerNames[0])));
        }

        newBoard = findViewById(R.id.ticTacToeboard);
        newBoard.setUpGame(afreshButton, homeButton, playerDisplay, playerNames);
    }

    public void AfreshButtonClick(View view){
        newBoard.resetBoard();
    }

    public void HomeButtonClick(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}