package com.dyy.nba.commonlibs.dialog.model;

import com.dyy.nba.commonlibs.dialog.listener.OnListItemClickListener;

import java.util.List;

/**
 * Created by 段钰莹 on 2017/9/4.
 * 列表框，列表部分必须，其他可选,可用KeyValue作为泛型替代量
 */

public class ListDialogData<T> extends BaseModel{
    private List<T> data;
    private OnListItemClickListener<T> listItemClickListener;

    public OnListItemClickListener<T> getListItemClickListener() {
        return listItemClickListener;
    }

    public void setListItemClickListener(OnListItemClickListener<T> listItemClickListener) {
        this.listItemClickListener = listItemClickListener;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
