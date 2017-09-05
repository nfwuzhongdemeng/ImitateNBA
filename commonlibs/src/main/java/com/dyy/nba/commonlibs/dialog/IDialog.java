package com.dyy.nba.commonlibs.dialog;

import android.content.Context;

import com.dyy.nba.commonlibs.dialog.model.BaseModel;

/**
 * Created by 段钰莹 on 2017/9/4.
 */

public interface IDialog<T extends BaseModel> {
    void canCancel(boolean canCancel);
    void show();
    void dismiss();
    void create(Context context, T t, int layoutId);
}
