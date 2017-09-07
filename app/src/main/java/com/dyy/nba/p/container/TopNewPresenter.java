package com.dyy.nba.p.container;

import com.dyy.nba.d.container.TopNewIndicator;
import com.dyy.nba.p.BasePresenter;
import com.dyy.nba.v.container.ITopNewsView;

/**
 * Created by 段钰莹 on 2017/9/6.
 */

public class TopNewPresenter extends BasePresenter<ITopNewsView,TopNewIndicator>{
    public TopNewPresenter(ITopNewsView view) {
        super(view);
    }

    @Override
    protected TopNewIndicator getInteractor() {
        if(interactor == null)
            interactor = new TopNewIndicator();
        return interactor;
    }

    @Override
    public void initData() {
        view.initTabs(getInteractor().getTabs());
    }
}
