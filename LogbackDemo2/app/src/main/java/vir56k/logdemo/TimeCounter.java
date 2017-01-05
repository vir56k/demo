package vir56k.logdemo;

import android.util.Log;

import java.util.Calendar;

/**
 * Created by zhangyunfei on 15/9/8.
 */
public class TimeCounter {
    String tag;

    public TimeCounter(String tag) {
        this.tag = tag == null ? "" : tag;
    }

    public String getTag() {
        return tag;
    }

    long beginDate;
    long endDate;

    public void begin() {
        beginDate = Calendar.getInstance().getTime().getTime();
    }

    public void end() {
        endDate = Calendar.getInstance().getTime().getTime();
    }

    public long getHowLong() {
        return endDate - beginDate;
    }

    public void print() {
        long time = getHowLong();
        Log.d("TimeCounter", String.format("%s 时长: %s 毫秒", getTag(), time));
        //Log.d(TAG, String.format("%s 时长: %s 秒", getTag(), time / 1000f));

    }
}