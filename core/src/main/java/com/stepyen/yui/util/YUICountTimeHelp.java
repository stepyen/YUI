package com.stepyen.yui.util;

import java.util.Timer;
import java.util.TimerTask;

/**
 * date：2020-01-02
 * author：stepyen
 * description：计时帮助类
 * <p>
 * 功能：
 * 1、支持 计时 和 倒计时
 */
public class YUICountTimeHelp {

    // 是否是倒计时
    private boolean isCountDownTime = false;

    //最大计时时间，单位：毫秒
    private long maxCountTime = -1;

    // 当前时间，单位：毫秒
    private long currentTime = 0;

    /**
     * 倒计时是否完成
     */
    private boolean isFinish = false;

    /**
     * 间隔时间，单位：毫秒
     */
    private long period = 1000;


    private OnCountListener mOnCountListener;

    private Timer timer;
    private TimerTask timerTask;


    private YUICountTimeHelp(boolean isCountDownTime, long maxCountTime) {
        this.isCountDownTime = isCountDownTime;
        reset(maxCountTime);
        timer = new Timer();
    }


    public static YUICountTimeHelp newCountDownHelp(long maxCountTime) {
        if (maxCountTime <= 0) {
            throw new IllegalStateException("倒计时时间不能小于等于0");
        }
        return new YUICountTimeHelp(true, maxCountTime);
    }

    public static YUICountTimeHelp newCountUpHelp() {
        return new YUICountTimeHelp(false, -1);
    }

    public static YUICountTimeHelp newCountUpHelp(long maxCountTime) {
        if (maxCountTime <= 0) {
            throw new IllegalStateException("计时时间不能小于等于0");
        }
        return new YUICountTimeHelp(false, maxCountTime);
    }

    /**
     * 开始计时
     */
    public void start() {

        if (isFinish) {
            return;
        }

        if (timer == null || timerTask != null) {
            return;
        }

        synchronized (YUICountTimeHelp.class) {

            if (timer == null || timerTask != null) {
                return;
            }

            stop();
            initTask();
            try {
                timer.schedule(timerTask, 0, period);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void initTask() {
        timerTask = new TimerTask() {
            @Override
            public void run() {
                if (isCountDownTime) {
                    countDown();
                } else {
                    countUp();
                }
            }
        };
    }


    /**
     * 停止计时
     */
    public void stop() {
        if (timerTask != null) {
            timerTask.cancel();
        }
        timerTask = null;
    }

    public void destory() {
        stop();
        if (timer != null) {
            timer.cancel();
        }
        timer = null;
    }

    /**
     * 设置时间
     *
     * @param time
     */
    public void setMaxCountTime(long time) {
        if (time <= 0) {
            throw new IllegalStateException("倒计时时间不能小于等于0");
        }

        stop();
        reset(time);
    }

    public void reset() {
        setMaxCountTime(maxCountTime);
    }

    private void reset(long time) {
        isFinish = false;
        maxCountTime = time;
        if (isCountDownTime) {
            currentTime = maxCountTime;
        } else {
            currentTime = 0;
        }
    }

    /**
     * 设置间隔时间，单位：毫秒
     *
     * @param period
     */
    public void setPeriod(long period) {
        if (period <= 0) {
            throw new IllegalStateException("间隔时间不能小于等于0");
        }
        isFinish = false;
        this.period = period;
    }


    private void countDown() {
        callbackCountTime(currentTime);
        currentTime -= period;
        if (currentTime < 0) {
            stop();
            isFinish = true;
            if (mOnCountListener != null) {
                mOnCountListener.onFinish();
            }
        }
    }

    private void countUp() {
        callbackCountTime(currentTime);
        currentTime += period;
        // 有最大限制时间
        if (maxCountTime != -1 && currentTime > maxCountTime) {
            stop();
            isFinish = true;
            if (mOnCountListener != null) {
                mOnCountListener.onFinish();
            }
        }
    }

    private void callbackCountTime(long time) {
        if (mOnCountListener != null) {
            long hour = time / 1000 / 3600;
            long minute = time / 1000 / 60 % 60;
            float second = time / 1000f % 60;
            mOnCountListener.onCount(time, hour, minute, second);
        }
    }


    public void setOnCountListener(OnCountListener onCountListener) {
        this.mOnCountListener = onCountListener;
    }

    public interface OnCountListener {

        /**
         * 计时
         *
         * @param time   总时间
         * @param hour   小时
         * @param minute 分钟
         * @param second 秒
         */
        void onCount(long time, long hour, long minute, float second);


        void onFinish();


    }


}
