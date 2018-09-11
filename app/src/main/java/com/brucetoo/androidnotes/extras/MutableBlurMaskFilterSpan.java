package com.brucetoo.androidnotes.extras;

import android.graphics.BlurMaskFilter;
import android.graphics.MaskFilter;
import android.text.TextPaint;
import android.text.style.CharacterStyle;
import android.text.style.UpdateAppearance;

/**
 * Created by Bruce Too
 * On 11/09/2018.
 * At 14:06
 */
public class MutableBlurMaskFilterSpan extends CharacterStyle implements UpdateAppearance {

    private static final String TAG = "MutableBlurMaskFilterSpan";
    private float mRadius;
    private MaskFilter mFilter;

    public MutableBlurMaskFilterSpan(float radius) {
        mRadius = radius;
    }

    public void setRadius(float radius) {
        mRadius = radius;
        mFilter = new BlurMaskFilter(mRadius, BlurMaskFilter.Blur.NORMAL);
    }

    public float getRadius() {
        return mRadius;
    }

    public MaskFilter getFilter() {
        return mFilter;
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        ds.setMaskFilter(mFilter);
    }
}
