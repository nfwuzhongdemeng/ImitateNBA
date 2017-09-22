package com.dyy.nba.p.container;

import android.text.TextUtils;

import com.dyy.nba.commonlibs.util.LogUtil;
import com.dyy.nba.d.container.TopNewListIndicator;
import com.dyy.nba.http.RequestCallback;
import com.dyy.nba.http.tencent.TopNewsService;
import com.dyy.nba.model.IndexBean;
import com.dyy.nba.model.NewsIndex;
import com.dyy.nba.model.NewsItem;
import com.dyy.nba.p.BasePresenter;
import com.dyy.nba.v.container.ITopNewBaseView;

import java.util.ArrayList;
import java.util.List;

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
        view.initView();
    }

    private List<String> indexs = new ArrayList<>();
    private int num = 10;//每页条数
    private int start = 0; // 查询数据起始位置

    public void loadIndex(final boolean isRefresh) {
        TopNewsService.getNewsIndex(view.getNewsType(), isRefresh, new RequestCallback<NewsIndex>() {
            @Override
            public void onSuccess(NewsIndex newsIndex) {
                indexs.clear();
                start = 0;
                for (IndexBean bean : newsIndex.data) {
                    indexs.add(bean.id);
                }
                String arcIds = parseIds();
                requestNews(arcIds, isRefresh, false);
            }

            @Override
            public void onFailure(String message) {
                LogUtil.i(message);
            }
        });
    }

    private void requestNews(String arcIds, final boolean isRefresh, boolean isLoadMore) {
        TopNewsService.getNewsItem(view.getNewsType(), arcIds, isRefresh, new RequestCallback<NewsItem>() {
            @Override
            public void onSuccess(NewsItem newsItem) {
                if (isRefresh)
                    view.getNewsList().clear();
                view.getNewsList().addAll(newsItem.data);
                view.refresh();
            }

            @Override
            public void onFailure(String message) {
            }
        });
    }

    private String parseIds() {
        int size = indexs.size();
        String articleIds = "";
        for (int i = start, j = 0; i < size && j < num; i++, j++, start++) {
            articleIds += indexs.get(i) + ",";
        }
        if (!TextUtils.isEmpty(articleIds))
            articleIds = articleIds.substring(0, articleIds.length() - 1);
        LogUtil.i("articleIds = " + articleIds);
        return articleIds;
    }

}
