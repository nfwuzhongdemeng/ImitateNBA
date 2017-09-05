package com.dyy.nba.commonlibs.dialog.model;

import android.view.View;

/**
 * Created by 段钰莹 on 2017/9/4.
 */

public class ButtonModel {
    private String btnName;
    private int btnId;
    private View.OnClickListener listener;

    public String getBtnName() {
        return btnName;
    }

    public void setBtnName(String btnName) {
        this.btnName = btnName;
    }

    public int getBtnId() {
        return btnId;
    }

    public void setBtnId(int btnId) {
        this.btnId = btnId;
    }

    public View.OnClickListener getListener() {
        return listener;
    }

    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
    }
}
