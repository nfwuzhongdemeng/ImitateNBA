package com.dyy.nba.commonlibs.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dyy.nba.commonlibs.dialog.model.BaseModel;
import com.dyy.nba.commonlibs.dialog.model.ButtonModel;

import java.util.List;

/**
 * Created by 段钰莹 on 2017/9/4.
 */

public abstract class BaseDialog<T extends BaseModel> implements IDialog<T>{
    protected Dialog dialog;
    @Override
    public void canCancel(boolean canCancel) {
        if(dialog!=null)
        dialog.setCancelable(true);
    }

    @Override
    public void show() {
       if(dialog!=null)
           dialog.show();
    }

    @Override
    public void dismiss() {
        if(dialog!=null && dialog.isShowing())
            dialog.dismiss();
    }

    @Override
    public void create(Context context, T t, int layoutId) {
        if(dialog == null)
            dialog = new Dialog(context);
        View view = LayoutInflater.from(context).inflate(layoutId,null);
        BaseModel model= t;
        if(model.getTitleId()!=0){
            TextView titleView = (TextView) view.findViewById(model.getTitleId());
            titleView.setText(model.getTitle());
        }

        List<ButtonModel> btns = model.getBtnData();
        if(btns!=null && btns.size() > 0 ){
            for(int i = 0; i < btns.size(); i++){
                ButtonModel btnModel = btns.get(i);
                Button btn = (Button) view.findViewById(btnModel.getBtnId());
                btn.setText(btnModel.getBtnName());
                btn.setOnClickListener(btnModel.getListener());
            }
        }

        initData(t,view);
        dialog.setContentView(view);
    }

    protected abstract void initData(T t,View parentView);
}
