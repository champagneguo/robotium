package com.robotium.solo;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/**
 * Created by hongwei21 on 2016/12/21.
 */
class Utils {

    static final int THREAD_LEAK_CLEANING_MS = 1000;

    /**
     * Prior to Android 5, HandlerThread always keeps a stack local reference to the last message
     * that was sent to it. This method makes sure that stack local reference never stays there
     * for too long by sending new messages to it every second.
     */
    static void flushStackLocalLeaks(Looper looper) {
        Handler handler = new Handler(looper) {
            @Override public void handleMessage(Message msg) {
                sendMessageDelayed(obtainMessage(), THREAD_LEAK_CLEANING_MS);
            }
        };
        handler.sendMessageDelayed(handler.obtainMessage(), THREAD_LEAK_CLEANING_MS);
    }

}
