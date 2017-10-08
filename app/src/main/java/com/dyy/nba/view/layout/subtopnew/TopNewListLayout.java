package com.dyy.nba.view.layout.subtopnew;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.dyy.nba.R;
import com.dyy.nba.adapter.NewsRecyclerMediaAdapter;
import com.dyy.nba.d.container.TopNewListIndicator;
import com.dyy.nba.http.enumate.NewsType;
import com.dyy.nba.model.NewsItemBean;
import com.dyy.nba.p.container.TopNewListPresenter;
import com.dyy.nba.v.container.ITopNewBaseView;
import com.dyy.nba.view.layout.BaseLinearContainer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 段钰莹 on 2017/9/6.
 * R.layout.page_top_new_sub_list
 */

public class TopNewListLayout extends BaseLinearContainer<ITopNewBaseView,TopNewListIndicator,TopNewListPresenter> implements ITopNewBaseView {
    public TopNewListLayout(Context context) {
        super(context);
    }

    public TopNewListLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TopNewListLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TopNewListLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected TopNewListPresenter getPresenter() {
        if(presenter == null)
            presenter = new TopNewListPresenter(this);
        return presenter;
    }

    private NewsType type;
//    BANNER("banner"),      //头条
//    NEWS("news"),          //新闻
//    VIDEO("videos"),     //视频集锦
//    DEPTH("depth"),        //十佳球/五佳球
//    HIGHLIGHT("highlight");//赛场花絮
    public void setTag(int tag){
        switch (tag){
            case 0:
                type = NewsType.BANNER;
                break;
            case 1:
                type = NewsType.NEWS;
                break;
            case 2:
                type = NewsType.VIDEO;
                break;
            case 3:
                type = NewsType.DEPTH;
                break;
            case 4:
                type = NewsType.HIGHLIGHT;
                break;
        }
        presenter.loadIndex(false);
    }

    private NewsRecyclerMediaAdapter adapter;
    private List<NewsItemBean> list = new ArrayList<>();

    @Override
    public void initView() {
        adapter = new NewsRecyclerMediaAdapter(list);
        RecyclerView recyclerView = (RecyclerView) getViewById(R.id.page_top_new_sub_list_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


    }

    @Override
    public NewsType getNewsType() {
        if(type == null)
            type = NewsType.BANNER;
        return type;
    }

    @Override
    public List<NewsItemBean> getNewsList() {
        if(list == null)
            list = new ArrayList<>();
        return list;
    }

    @Override
    public void refresh() {
        if(adapter != null)
            adapter.notifyDataSetChanged();
    }
}
