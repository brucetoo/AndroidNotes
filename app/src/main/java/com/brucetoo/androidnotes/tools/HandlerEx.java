package com.brucetoo.androidnotes.tools;

import android.os.Handler;
import android.os.Looper;

/**
 * Created by Bruce Too
 * On 2018/10/26.
 * At 14:26
 * Handler with custom name
 */
public class HandlerEx extends Handler {
    private String mName;

    public HandlerEx(String name) {
        setName(name);
    }

    public HandlerEx(String name, Callback callback) {
        super(callback);
        setName(name);
    }

    public HandlerEx(String name, Looper looper) {
        super(looper);
        setName(name);
    }

    public HandlerEx(String name, Looper looper, Callback callback) {
        super(looper, callback);
        setName(name);
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getName() {
        return mName;
    }

    @Override
    public String toString() {
        return "HandlerEx (" + mName + ") {}";
    }
}
