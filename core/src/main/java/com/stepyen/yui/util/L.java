package com.stepyen.yui.util;

import android.text.TextUtils;
import android.util.Log;

/**
 * date：2019-12-06
 * author：stepyen
 * description：日志工具类
 */
public class L {

    private static final String DEFAULT_TAG = "YUI_TAG";

    private static boolean isLog = true;

    private L() {
        throw new IllegalStateException("you can't instantiate me!");
    }

    public static boolean isLog() {
        return isLog;
    }

    public static void setLog(boolean isLog) {
        L.isLog = isLog;
    }

    public static void d(String tag, String msg) {
        if (!isLog || TextUtils.isEmpty(msg)) return;
        Log.d(tag, msg);
    }

    public static void d(String msg) {
        d(DEFAULT_TAG, msg);
    }


    public static void dLongInfo(String msg) {
        dLongInfo(DEFAULT_TAG, msg);
    }


    /**
     * 日志太长会无法全部打印，使用此方法分节打印
     *
     * @param tag 标签
     * @param msg 日志内容
     */
    public static void dLongInfo(String tag, String msg) {
        if (!isLog || TextUtils.isEmpty(msg)) return;
        msg = msg.trim();
        int index = 0;
        int maxLength = 3500;
        String sub;
        while (index < msg.length()) {
            if (msg.length() <= index + maxLength) {
                sub = msg.substring(index);
            } else {
                sub = msg.substring(index, index + maxLength);
            }

            index += maxLength;
            Log.d(tag, sub.trim());
        }
    }


    public static void e(String tag, String msg) {
        if (!isLog || TextUtils.isEmpty(msg)) return;
        Log.e(tag, msg);
    }

    public static void e(String msg) {
        e(DEFAULT_TAG, msg);
    }

    public static void e(int errorCode, String reason) {
        StringBuilder sb = new StringBuilder();
        sb.append("error code : ");
        sb.append(errorCode);
        sb.append("     reason：");
        sb.append(reason);

        e(DEFAULT_TAG, sb.toString());
    }



}
