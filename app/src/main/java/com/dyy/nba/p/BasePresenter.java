package com.dyy.nba.p;

import com.dyy.nba.d.IInteractor;
import com.dyy.nba.v.IView;

/**
 * Created by 段钰莹 on 2017/9/5.
 */

public abstract class BasePresenter<T extends IView,Q extends IInteractor> {
    protected T view;
    protected Q interactor;
    public BasePresenter(T view){
        this.view = view;
        interactor = getInteractor();
    }

    protected abstract Q getInteractor();

    public abstract void initData();
}
