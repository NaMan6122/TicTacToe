package com.example.myapplication;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class TicTacToeboard extends View {
    private final int boardColor;
    private final int Xcolor;
    private final int Ocolor;
    private final int WinLinecolor;
    private int cellSize = getWidth()/3;

    private final Paint paint = new Paint();
    public TicTacToeboard(Context context, @Nullable AttributeSet attrs) {
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
    protected void onDraw(Canvas canvas){
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        drawGameBoard(canvas);
    }

    private void drawGameBoard(Canvas canvas){
        paint.setColor(boardColor);
        paint.setStrokeWidth(16);
        for(int row=1; row<=4; row++){
            canvas.drawLine(0, cellSize*(row-1), canvas.getWidth(), cellSize*(row-1), paint);
        }
        for(int col=1; col<=4; col++){
            canvas.drawLine(cellSize*(col-1), 0, cellSize*(col-1), canvas.getWidth(), paint);
        }
    }
}
