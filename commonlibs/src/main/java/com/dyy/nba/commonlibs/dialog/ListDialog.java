package com.dyy.nba.commonlibs.dialog;

import android.view.View;

import com.dyy.nba.commonlibs.dialog.model.ListDialogData;

import java.util.List;

/**
 * Created by 段钰莹 on 2017/9/4.
 */

public class ListDialog<T> extends BaseDialog<ListDialogData<T>> {

    @Override
    protected void initData(ListDialogData<T> tListDialogData, View parentView) {
        List<T> datas = tListDialogData.getData();

    }
}
