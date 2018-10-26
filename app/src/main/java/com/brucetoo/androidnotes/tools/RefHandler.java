package com.brucetoo.androidnotes.tools;

import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * Created by Bruce Too
 * On 2018/10/26.
 * At 13:51
 * Handler that prevent any reference memory leak,especially {@link android.app.Activity}
 */
public class RefHandler<T> extends Handler {

    private WeakReference<T> mReferSelf;
    private MessageCallback<T> mMsgCallback;

    public RefHandler(T referSelf,MessageCallback<T> callback){
        this.mReferSelf = new WeakReference<>(referSelf);
        this.mMsgCallback = callback;
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        T refer = mReferSelf.get();
        if(refer != null && mMsgCallback != null){
            mMsgCallback.handleMessage(refer,msg);
        }
    }

    public interface MessageCallback<T> {
        void handleMessage(T referSelf, Message msg);
    }
}
