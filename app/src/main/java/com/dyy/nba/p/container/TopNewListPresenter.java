package com.dyy.nba.p.container;

import com.dyy.nba.d.container.TopNewListIndicator;
import com.dyy.nba.p.BasePresenter;
import com.dyy.nba.v.container.ITopNewBaseView;

/**
 * Created by 段钰莹 on 2017/9/6.
 */

public class TopNewListPresenter extends BasePresenter<ITopNewBaseView,TopNewListIndicator> {
    public TopNewListPresenter(ITopNewBaseView view) {
        super(view);
    }

    @Override
    protected TopNewListIndicator getInteractor() {
        if(interactor == null)
            interactor = new TopNewListIndicator();
        return interactor;
    }

    @Override
    public void initData() {

    }
}
