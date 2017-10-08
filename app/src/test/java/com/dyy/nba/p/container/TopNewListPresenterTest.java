package com.dyy.nba.p.container;

import com.dyy.nba.http.enumate.NewsType;
import com.dyy.nba.model.NewsItemBean;
import com.dyy.nba.v.container.ITopNewBaseView;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
/**
 * Created by 段钰莹 on 2017/10/8.
 */
public class TopNewListPresenterTest implements ITopNewBaseView {
    private TopNewListPresenter presenter;
    @Before
    public void setUp() throws Exception {
        presenter = new TopNewListPresenter(this);
    }


    @Test
    public void loadIndex() throws Exception {
        testNews();
        testBanner();
        testVideo();
        testDepth();
        testHighLight();
    }

    private void testHighLight() {
        newsType = NewsType.HIGHLIGHT;
        presenter.loadIndex(false);
    }

    private void testDepth() {
        newsType = NewsType.DEPTH;
        presenter.loadIndex(false);
    }

    private void testVideo() {
        newsType = NewsType.VIDEO;
        presenter.loadIndex(false);
    }

    private void testBanner() {
        newsType = NewsType.BANNER;
        presenter.loadIndex(false);
    }

    private void testNews() {
        newsType = NewsType.NEWS;
        presenter.loadIndex(false);
    }

    @Override
    public void initView() {

    }

    private NewsType newsType = NewsType.NEWS;
    @Override
    public NewsType getNewsType() {
        return newsType;
    }

    private List<NewsItemBean> newsItemBeans = new ArrayList<>();
    @Override
    public List<NewsItemBean> getNewsList() {
        return newsItemBeans;
    }

    @Override
    public void refresh() {
        for(int i = 0; i < newsItemBeans.size(); i++){
            NewsItemBean bean = newsItemBeans.get(i);
            assertFalse(bean.title.equals(""));
            assertFalse(bean.pub_time.equals(""));
        }
    }
}