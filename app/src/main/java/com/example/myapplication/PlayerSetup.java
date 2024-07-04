package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class PlayerSetup extends AppCompatActivity {

    private EditText player1;
    private EditText player2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_setup);

        //assigning the player ids to the variables.
        player1 = findViewById(R.id.editTextText);
        player2 = findViewById(R.id.editTextText2);
    }

    public void submitButtonClick(View view){
        String player1Name = player1.getText().toString();
        String player2Name = player2.getText().toString();
        Intent intent = new Intent(this, GameDisplay.class);
        intent.putExtra("Player Names", new String[] {player1Name, player2Name});
        startActivity(intent);
    }
}