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
        return getInteractor().getPages(view.getContext());
    }

    public String getTitleByPos(int position){
        List<MainMenuData> leftMenuItems = getLeftMenuData();

        if(leftMenuItems != null){
            return getLeftMenuName(leftMenuItems,getSafePosition(position,leftMenuItems.size()));
        }

        return "";
    }

    private String getLeftMenuName(List<MainMenuData> leftMenuItems,int position) {
        return leftMenuItems.get(position).getTxt();
    }

    private int getSafePosition(int position, int size) {
        if(position < 0) position = 0;
        if(position > size) position = size - 1;
        return position;
    }
}
