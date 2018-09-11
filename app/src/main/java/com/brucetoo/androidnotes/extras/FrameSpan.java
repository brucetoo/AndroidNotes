package com.brucetoo.androidnotes.extras;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.style.ReplacementSpan;

/**
 * Created by Bruce Too
 * On 11/09/2018.
 * At 14:09
 */
public class FrameSpan extends ReplacementSpan {

    private final Paint mPaint;
    private int mWidth;

    public FrameSpan() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLUE);
        mPaint.setAntiAlias(true);
    }

    @Override
    public int getSize(Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fm) {
        //return text with relative to the Paint
        mWidth = (int) paint.measureText(text, start, end);
        return mWidth;
    }

    @Override
    public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
        //draw the frame with custom Paint
        canvas.drawRect(x, top, x + mWidth, bottom, mPaint);
    }
}
