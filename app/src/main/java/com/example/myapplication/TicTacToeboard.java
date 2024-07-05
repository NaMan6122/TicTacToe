package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class TicTacToeboard extends View {
    private final int boardColor;
    private final int Xcolor;
    private final int Ocolor;
    private final int WinLinecolor;
    private int cellSize;
    private final GameLogic game = new GameLogic();
    private boolean winnerLine;
    private final Paint paint = new Paint();
    public TicTacToeboard(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.TicTacToeboard, 0, 0);

        try{
            boardColor = a.getInteger(R.styleable.TicTacToeboard_boardColor, 0);
            Xcolor = a.getInteger(R.styleable.TicTacToeboard_Xcolor, 0);
            Ocolor = a.getInteger(R.styleable.TicTacToeboard_Ocolor, 0);
            WinLinecolor = a.getInteger(R.styleable.TicTacToeboard_WinLinecolor, 0);
        }finally{
            a.recycle();
        }
    }

    @Override
    protected void onMeasure(int width, int height){
        super.onMeasure(width, height);
        int dimensions = Math.min(getMeasuredWidth(), getMeasuredHeight());
        cellSize = dimensions/3;
        setMeasuredDimension(dimensions, dimensions);
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas){
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        drawGameBoard(canvas);
        drawMarkers(canvas);
    }

    private void drawGameBoard(Canvas canvas){
        paint.setColor(boardColor);
        paint.setStrokeWidth(16);
        for(int row=1; row<=4; row++){
            canvas.drawLine(0, cellSize*(row-1), canvas.getWidth(), cellSize*(row-1), paint);
        }
        for(int col=1; col<=4; col++) {
            canvas.drawLine(cellSize * (col - 1), 0, cellSize * (col - 1), canvas.getWidth(), paint);
        }
    }
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN) {
            int row = (int)(y / cellSize);
            int col = (int)(x / cellSize);

            if(!winnerLine){
                if (game.updateGameBoard(row, col)) {
                    if(game.winnerCheck()){
                        winnerLine = true;
                        invalidate();
                    }
                    invalidate();
                    game.setPlayer(game.getPlayer() % 2 == 0 ? game.getPlayer()-1 : game.getPlayer()+1);
                }
            }
            return true;
        }
        return false;
    }

    private void drawMarkers(Canvas canvas){
        for(int row=0; row<3; row++){
            for(int col=0; col<3; col++){
                if(game.getGameBoard()[row][col] != 0){
                    if(game.getGameBoard()[row][col] == 1){
                        toDrawX(canvas, row, col);
                    }else{
                        toDrawO(canvas, row, col);
                    }
                }
            }
        }
    }

    //reduction value/padding is added to both the shapes to enhance looks.
    private void toDrawX(Canvas canvas, int row, int col){
        paint.setColor(Xcolor);

        float startX1 = col*cellSize + cellSize*0.2f;
        float startY1 = row*cellSize + cellSize*0.2f;
        float endX1 = (col+1)*cellSize - cellSize*0.2f;
        float endY1 = (row+1)*cellSize - cellSize*0.2f;
        canvas.drawLine(startX1, startY1, endX1, endY1, paint);

        float startX2 = (col+1)*cellSize - cellSize*0.2f;
        float startY2 = row*cellSize + cellSize*0.2f;
        float endX2 = (col)*cellSize + cellSize*0.2f;
        float endY2 = (row+1)*cellSize - cellSize*0.2f;
        canvas.drawLine(startX2, startY2, endX2, endY2, paint);
    }
    private void toDrawO(Canvas canvas, int row, int col){
        paint.setColor(Ocolor);
        canvas.drawOval(col*cellSize + cellSize*0.2f,
                row*cellSize + cellSize*0.2f,
                (col+1)*cellSize - cellSize*0.2f,
                (row+1)*cellSize - cellSize*0.2f,
                paint);
    }

    public void setUpGame(Button afresh, Button home, TextView playerDisplay, String[] names){
        game.setAfreshButton(afresh);
        game.setHomeButton(home);
        game.setPlayerTurn(playerDisplay);
        game.setPlayerNames(names);
    }
    public void resetBoard(){ //works with afresh button.
        game.resetGame();
        winnerLine = false;
        invalidate(); //redraw the board again.
    }
}
