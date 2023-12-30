package com.example.pong;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class PongGame extends SurfaceView implements Runnable {
    private final boolean DEBUGGING = true;
    private SurfaceHolder mOurHolder;
    private Canvas mCanvas;
    private Paint mPaint;
    private long mFPS;
    private final int MILLIS_IN_SECOND = 1000;
    private int mScreenX;
    private int mScreenY;
    private int mFontSize;
    private int mFontMargin;
    private Bat mBat;
    private Ball mBall;
    private int mScore;
    private int mLives;
    private Thread mGameThread = null;
    private volatile boolean mPlaying;
    private boolean mPaused;
    public PongGame(Context context, int x, int y) {
        super(context);
        mScreenX = x;
        mScreenY = y;
        mFontSize = mScreenX / 20;
        mFontMargin = mScreenX / 75;
        mOurHolder = getHolder();
        mPaint = new Paint();

        startNewGame();
    }

    private void startNewGame() {
        //put the ball back to the starting position

        //reset the score and lives
        mScore = 0;
        mLives = 3;
    }

    @Override
    public void run() {
        while (mPlaying) {
            long frameStartTime = System.currentTimeMillis();
            if (!mPaused) {
                update();
                detectCollisions();
            }
            draw();
            long timeThisFrame = System.currentTimeMillis() - frameStartTime;
            if (timeThisFrame > 0) {
                mFPS = MILLIS_IN_SECOND / timeThisFrame;
            }
        }
    }

    private void update() {
        // Update the bat and ball
    }

    private void detectCollisions() {
        //Has the bat hit the ball?
        //Has the ball hit the edge if the screen
        //Bottom
        //Top
        //Left
        //Right
    }

    public void pause() {
        mPlaying = false;
        try {
            mGameThread.join();
        } catch (InterruptedException e) {
            Log.e("ERROR: ", "joining thread");
        }
    }

    public void resume() {
        mPlaying = true;
        mGameThread = new Thread(this);
        mGameThread.start();
    }

    private void draw() {
        if (mOurHolder.getSurface().isValid()) {
            // LOck the canvas (graphic memory) ready to draw
            mCanvas = mOurHolder.lockCanvas();
            // Fill the screen with a  solid color
            mCanvas.drawColor(Color.argb(255, 26, 128, 182));
            // Choose a color to paint with
            mPaint.setColor(Color.argb(255, 255, 255, 255));
            //Draw the ball and bat
            //Choose the font size
            mPaint.setTextSize(mFontSize);
            //draw the HUD
            mCanvas.drawText("Score: " + mScore + "  Lives: " + mLives, mFontMargin, mFontSize, mPaint);
            if (DEBUGGING) {
                printDebuggingText();
            }
            //Display the drawing on the screen
            //Unlock canvas and post
            mOurHolder.unlockCanvasAndPost(mCanvas);
        }
    }

    private void printDebuggingText() {
        int debugSize = mFontSize / 2;
        int debugStart = 150;
        mPaint.setTextSize(debugSize);
        mCanvas.drawText("FPS: " + mFPS, 10, debugStart + debugSize, mPaint);
    }



}
