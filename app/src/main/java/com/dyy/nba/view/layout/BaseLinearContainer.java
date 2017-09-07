package com.dyy.nba.view.layout;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.dyy.nba.d.IInteractor;
import com.dyy.nba.p.BasePresenter;
import com.dyy.nba.v.IView;

/**
 * Created by 段钰莹 on 2017/9/6.
 */

public abstract class BaseLinearContainer<A extends IView,B extends IInteractor,C extends BasePresenter<A,B>> extends LinearLayout{
    public BaseLinearContainer(Context context) {
        super(context);
    }

    public BaseLinearContainer(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseLinearContainer(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public BaseLinearContainer(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    protected C presenter;
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        presenter = getPresenter();
        presenter.initData();
    }

    protected abstract C getPresenter() ;

    public View getViewById(int id) {
        return findViewById(this, id);
    }

    protected View findViewById(ViewGroup viewGroup, int id) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View inner = viewGroup.getChildAt(i);
            if (viewGroup.getChildAt(i).getId() == id) {
                return viewGroup.getChildAt(i);
            } else if (inner instanceof ViewGroup) {
                if (((ViewGroup) inner).getChildCount() == 0)
                    continue;
                View returnView = findViewById((ViewGroup) inner, id);
                if (returnView == null)
                    continue;
                return returnView;
            }
        }
        return null;
    }
}
