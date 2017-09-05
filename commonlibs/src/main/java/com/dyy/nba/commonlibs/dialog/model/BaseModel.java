package com.dyy.nba.commonlibs.dialog.model;

import java.util.List;

/**
 * Created by 段钰莹 on 2017/9/4.
 */

public class BaseModel {
    private String title;
    private int titleId;
    private List<ButtonModel> btnData;
    private boolean canCancel  = true;

    public boolean isCanCancel() {
        return canCancel;
    }

    public void setCanCancel(boolean canCancel) {
        this.canCancel = canCancel;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTitleId() {
        return titleId;
    }

    public void setTitleId(int titleId) {
        this.titleId = titleId;
    }

    public List<ButtonModel> getBtnData() {
        return btnData;
    }

    public void setBtnData(List<ButtonModel> btnData) {
        this.btnData = btnData;
    }
}
