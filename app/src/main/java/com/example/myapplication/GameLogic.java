package com.example.myapplication;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameLogic {
    private int[][] gameBoard;
    private int[] winType = new int[3];
    private String[] playerNames = {"Player1", "Player2"}; //keeping a default value for player names.
    private Button afreshButton;
    private Button homeButton;
    private TextView playerTurn;
    private int player = 1; //player1 will always go first, (X).
    GameLogic(){ //default constructor.
        //populating the game board with all zeroes.
        gameBoard = new int[3][3];
    }

    public boolean updateGameBoard(int row, int col){
        if(gameBoard[row][col] == 0){
            gameBoard[row][col] = player;

            if(player == 1){
                playerTurn.setText((String.format("%s's turn", playerNames[1])));
            }else{
                playerTurn.setText((String.format("%s's turn", playerNames[0])));
            }
            return true;
        }else{
            return false;
        }
    }

    @SuppressLint("SetTextI18n")
    public boolean winnerCheck(){
        boolean isWinner = false;
        //horizontal check:
        for(int row = 0; row< 3; row++){
            if(gameBoard[row][0] == gameBoard[row][1] && gameBoard[row][0] == gameBoard[row][2] && gameBoard[row][0] != 0) {
                winType[0] = row; winType[1] = 0; winType[2] = 1; //{row, 0, 1}.
                isWinner = true;
            }
        }
        //vertical check:
        for(int col=0; col<3; col++){
            if(gameBoard[0][col] == gameBoard[1][col] && gameBoard[0][col] == gameBoard[2][col] && gameBoard[0][col] != 0){
                winType[0] = 0; winType[1] = col; winType[2] = 2; //{0, col, 2}.
                isWinner = true;
            }
        }
        //diagonal check:
        if(gameBoard[0][0] == gameBoard[1][1] && gameBoard[0][0] == gameBoard[2][2] && gameBoard[0][0] != 0){
            winType[0] = 0; winType[1] = 2; winType[2] = 4; //{0, 2, 3}.
            isWinner = true;
        }
        if(gameBoard[0][2] == gameBoard[1][1] && gameBoard[0][2] == gameBoard[2][0] && gameBoard[0][2] != 0){
            isWinner = true;
            winType[0] = 2; winType[1] = 2; winType[2] = 3; //{2, 2, 4}.
        }

        int isFilled = 0;
        for(int row=0; row<3; row++){
            for(int col=0; col<3; col++){
                if(gameBoard[row][col] != 0){
                    isFilled++;
                }
            }
        }
        if(isWinner){
            afreshButton.setVisibility(View.VISIBLE);
            homeButton.setVisibility(View.VISIBLE);
            playerTurn.setText(String.format("%sWon!", playerNames[player - 1]));
            return true;

        }else if(isFilled == 9){
            afreshButton.setVisibility(View.VISIBLE);
            homeButton.setVisibility(View.VISIBLE);
            playerTurn.setText("Draw!");
            return true;
        }
        return false;
    }

    //defining getter and setter for updating and accessing the gameBoard data.
    public int[][] getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(int[][] gameBoard) {
        this.gameBoard = gameBoard;
    }

    public void setPlayer(int player){
        this.player = player;
    }

    public int getPlayer(){
       return player;
    }

    public void resetGame(){ //works with afresh button.
        for(int row=0; row<3; row++){
            for(int col=0; col<3; col++){
                gameBoard[row][col] = 0;
            }
        }
        player = 1;
        afreshButton.setVisibility(View.GONE);
        homeButton.setVisibility(View.GONE);
        playerTurn.setText(String.format("%s's turn", playerNames[0]));
    }

    public void setAfreshButton(Button afreshButton) {
        this.afreshButton = afreshButton;
    }

    public void setHomeButton(Button homeButton){
        this.homeButton = homeButton;
    }

    public void setPlayerTurn(TextView playerTurn){
        this.playerTurn = playerTurn;
    }

    public void setPlayerNames(String[] playerNames) {
        this.playerNames = playerNames;
    }

    public int[] getWinType() {
        return winType;
    }
}
