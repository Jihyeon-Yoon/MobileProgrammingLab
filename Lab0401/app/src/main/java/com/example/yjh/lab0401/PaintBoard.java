package com.example.yjh.lab0401;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import java.io.OutputStream;

/**
 * Created by YJH on 2018-05-10.
 */

public class PaintBoard extends View {

    //cavas instance
    Canvas mCanvas;
    //bitmap for double buffering
    Bitmap mBitmap;
    //paint instance
    final Paint mPaint;
    //X coordinate
    int lastX;
    //Y coordinate
    int lastY;

    //initialze paint object and coordinates
    public PaintBoard(Context context) {
        super(context);

        //create a new paint object
        this.mPaint = new Paint();
        this.mPaint.setColor(Color.BLACK);

        this.lastX = -1;
        this.lastY = -1;
    }

    //onSizeChanged
    protected void onSizeChanged(int w, int h, int dldw, int oldh) {
        Bitmap img = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas();
        canvas.setBitmap(img);
        canvas.drawColor(Color.WHITE);

        mBitmap = img;
        mCanvas = canvas;
    }

    //draw the bitmap
    protected void onDraw(Canvas canvas) {
        if(mBitmap != null) {
            canvas.drawBitmap(mBitmap, 0, 0, null);
        }
    }

    //handles touch event, up, down and move
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();

        int X = (int) event.getX();
        int Y = (int) event.getY();

        switch(action) {
            case MotionEvent.ACTION_UP:
                //reset coordinates
                lastX=-1;
                lastY=-1;

                break;

            case MotionEvent.ACTION_DOWN:
                //draw line with the coordinates
                if(lastX != -1) {
                    if(X != lastX || Y != lastY) {
                        mCanvas.drawLine(lastX, lastY, X, Y, mPaint);
                    }
                }

                //set the last coordinates
                lastX = X;
                lastY = Y;

                break;
            case MotionEvent.ACTION_MOVE:
                //draw line with the coordinates
                if(lastX != -1) {
                    mCanvas.drawLine(lastX, lastY, X, Y, mPaint);
                }

                lastX = X;
                lastY = Y;

                break;
        }
        //repaint the screen
        invalidate();

        return true;
    }

    //save this contents into a jpeg image
    public boolean Save(OutputStream outstream) {
        try{
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, outstream);
            invalidate();

            return true;
        } catch(Exception e) {
            return false;
        }
    }

}
