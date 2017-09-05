package com.dyy.nba.commonlibs.dialog.model;

import com.dyy.nba.commonlibs.dialog.listener.OnDateSelectedListener;
import com.dyy.nba.commonlibs.dialog.listener.OnTimeSelectedListener;

public class TimeDateData extends BaseModel{
    private boolean showTime;
    private boolean showDate;
    private OnTimeSelectedListener onTimeSelectedListener;
    private OnDateSelectedListener onDateSelectedListener;

    public OnDateSelectedListener getOnDateSelectedListener() {
        return onDateSelectedListener;
    }

    public void setOnDateSelectedListener(OnDateSelectedListener onDateSelectedListener) {
        this.onDateSelectedListener = onDateSelectedListener;
    }

    public OnTimeSelectedListener getOnTimeSelectedListener() {
        return onTimeSelectedListener;
    }

    public void setOnTimeSelectedListener(OnTimeSelectedListener onTimeSelectedListener) {
        this.onTimeSelectedListener = onTimeSelectedListener;
    }

    public boolean isShowTime() {
        return showTime;
    }

    public void setShowTime(boolean showTime) {
        this.showTime = showTime;
    }

    public boolean isShowDate() {
        return showDate;
    }

    public void setShowDate(boolean showDate) {
        this.showDate = showDate;
    }
}