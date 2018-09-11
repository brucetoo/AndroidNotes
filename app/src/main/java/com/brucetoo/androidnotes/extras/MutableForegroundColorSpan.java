package com.brucetoo.androidnotes.extras;

import android.graphics.Color;
import android.os.Parcel;
import android.support.annotation.IntRange;
import android.text.TextPaint;
import android.text.style.ForegroundColorSpan;

/**
 * Created by Bruce Too
 * On 11/09/2018.
 * At 13:36
 */
public class MutableForegroundColorSpan extends ForegroundColorSpan {

    private int mAlpha = 255;
    private int mForegroundColor;

    public MutableForegroundColorSpan(int alpha,int color) {
        super(color);
        this.mAlpha = alpha;
        this.mForegroundColor = color;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(mAlpha);
        dest.writeInt(mForegroundColor);
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        super.updateDrawState(ds);
        ds.setColor(getForegroundColor());
    }

    @Override
    public int getForegroundColor() {
        return Color.argb(mAlpha,Color.red(mForegroundColor), Color.green(mForegroundColor), Color.blue(mForegroundColor));
    }

    public void setAlpha(@IntRange(from = 0,to = 255) int alpha){
        this.mAlpha = alpha;
    }

    public void setForegroundColor(int foregroundColor){
        this.mForegroundColor = foregroundColor;
    }
}
