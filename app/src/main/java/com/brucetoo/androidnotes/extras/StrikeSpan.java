package com.brucetoo.androidnotes.extras;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.style.ReplacementSpan;

/**
 * Created by Bruce Too
 * On 11/09/2018.
 * At 14:04
 */
public class StrikeSpan extends ReplacementSpan {

    private final Paint mPaint;
    private int mWidth;

    public StrikeSpan(int strokeWidth) {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLUE);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(strokeWidth);
    }

    @Override
    public int getSize(Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fm) {
        mWidth = (int) paint.measureText(text, start, end);
        return mWidth;
    }

    @Override
    public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
        float centerY = (top + bottom) * 0.5f;
        canvas.drawText(text, start, end, x, y, paint);
        canvas.drawLine(x, centerY, x + mWidth, centerY, mPaint);
    }
}
