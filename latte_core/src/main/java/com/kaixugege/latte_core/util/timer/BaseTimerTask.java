package com.kaixugege.latte_core.util.timer;

import java.util.TimerTask;

/**
 * @Author: KaixuGege
 * Time:           2019/3/26
 * ProjectName:    FestEC
 * ClassName:
 * Info:
 */
public class BaseTimerTask extends TimerTask {
    public ITimerListener getmITimerListener() {
        return mITimerListener;
    }

    public void setmITimerListener(ITimerListener mITimerListener) {
        this.mITimerListener = mITimerListener;
    }

    ITimerListener mITimerListener = null;

    public BaseTimerTask(ITimerListener iTimerListener) {
        this.mITimerListener = iTimerListener;
    }


    @Override
    public void run() {
        if (mITimerListener != null) {
            mITimerListener.onTimer();
        }

    }

}
