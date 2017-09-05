package com.dyy.nba.d;

import android.view.LayoutInflater;
import android.view.View;

import com.dyy.nba.R;
import com.dyy.nba.commonlibs.AppUtils;
import com.dyy.nba.model.listdata.MainMenuData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 段钰莹 on 2017/9/5.
 */

public class MainInteractor implements IInteractor{
    private final int[] menuImgs = {R.drawable.ic_top_news,R.drawable.ic_videos,R.drawable.ic_score,R.drawable.ic_sort,R.drawable.ic_hupu,R.drawable.ic_others};
    private final String[] menuNames = {"NBA头条","赛事直播","球队战绩","数据排行","虎扑专区","其他"};
    private List<MainMenuData> list = new ArrayList<>();
    public List<MainMenuData> getMenuItems(){
        if(list.size() == 0){
            addData();
        }
        return list;
    }

    private void addData() {
        for(int i = 0; i < menuImgs.length; i++){
            MainMenuData data = new MainMenuData();
            data.setImgId(menuImgs[i]);
            data.setTxt(menuNames[i]);
            list.add(data);
        }
    }

    private final int[] pages = {
            R.layout.page_top_menu,
            R.layout.page_live,
            R.layout.page_score,
            R.layout.page_data,
            R.layout.page_hupu,
            R.layout.page_other
    };

    private List<View> myPages = new ArrayList<>();
    public List<View> getPages() {
        if(myPages.size() == 0){
            for(int i = 0; i < pages.length; i++){
                View v= LayoutInflater.from(AppUtils.getSuperContext()).inflate(pages[i],null);
                myPages.add(v);
            }
        }

        return myPages;
    }
}
