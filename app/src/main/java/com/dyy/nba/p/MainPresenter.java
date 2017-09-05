package com.dyy.nba.p;

import android.view.View;

import com.dyy.nba.d.MainInteractor;
import com.dyy.nba.model.listdata.MainMenuData;
import com.dyy.nba.v.IMainView;

import java.util.List;

/**
 * Created by 段钰莹 on 2017/9/5.
 */

public class MainPresenter extends BasePresenter<IMainView,MainInteractor> {
    public MainPresenter(IMainView iView) {
        super(iView);
    }

    @Override
    protected MainInteractor getInteractor() {
        if(interactor == null)
            interactor = new MainInteractor();
        return interactor;
    }

    @Override
    public void initData() {
        view.initLeftMenu(getLeftMenuData());
        view.initViewPager(getPages());
    }


    private List<MainMenuData> getLeftMenuData() {
        return getInteractor().getMenuItems();
    }

    private List<View> getPages(){
        return getInteractor().getPages();
    }

    public String getTitleByPos(int position){
        List<MainMenuData> temp = getLeftMenuData();
        if(temp == null) return "";
        if(position < 0) position = 0;
        if(position > temp.size()) position = temp.size() - 1;


        return temp.get(position).getTxt();
    }
}
