package com.example.pong;
import android.graphics.RectF;

public class Ball {
    private RectF mRect;
    private float mXVelocity;
    private float mYVelocity;
    private float mBallWidth;
    private float mBallHeight;

    public Ball(int screenX) {
        mBallWidth = screenX/100;
        mBallHeight = screenX/100;
        mRect = new RectF();
    }

    RectF getRect() {
        return mRect;
    }

    void update(long fps) {
        mRect.left = mRect.left + (mXVelocity / fps);
        mRect.top = mRect.top + (mYVelocity / fps);
        mRect.right = mRect.left + mBallWidth;
        mRect.bottom = mRect.top + mBallHeight;
    }

    void reverseYVelocity() {
        mYVelocity = - mYVelocity;
    }

    void reverseXVelocity() {
        mXVelocity = - mXVelocity;
    }
}
