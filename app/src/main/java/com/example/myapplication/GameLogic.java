package com.example.myapplication;

public class GameLogic {
    private int[][] gameBoard;
    private int player = 1; //player1 will always go first, (X).
    GameLogic(){ //default constructor.
        //populating the game board with all zeroes.
        gameBoard = new int[3][3];
    }

    public boolean updateGameBoard(int row, int col){
        if(gameBoard[row][col] == 0){
            gameBoard[row][col] = player;
            return true;
        }else{
            return false;
        }
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

    public void resetGame(){
        for(int row=0; row<3; row++){
            for(int col=0; col<3; col++){
                gameBoard[row][col] = 0;
            }
        }
    }
}
