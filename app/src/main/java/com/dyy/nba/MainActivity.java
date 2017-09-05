package com.dyy.nba;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.dyy.nba.adapter.MainRecyclerMenuAdapter;
import com.dyy.nba.adapter.ViewPagerAdapter;
import com.dyy.nba.d.MainInteractor;
import com.dyy.nba.model.listdata.MainMenuData;
import com.dyy.nba.p.MainPresenter;
import com.dyy.nba.v.IMainView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends BaseActivity<IMainView, MainInteractor, MainPresenter> implements IMainView {

    @BindView(R.id.common_toolbar)
    Toolbar commonToolbar;
    @BindView(R.id.activity_main_viewpager)
    ViewPager mViewPager;
    @BindView(R.id.activity_main_linear_menu)
    LinearLayout mainLinearMenu;
    @BindView(R.id.activity_main_drawer)
    DrawerLayout mainDrawer;
    @BindView(R.id.activity_main_recycler)
    RecyclerView mRecyclerView;

    @Override
    protected int getChildView() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //这部分只写去标题，其他放在initChildView,为了presenter初始化数据前，控件初始化完毕

        super.onCreate(savedInstanceState);
    }


    @Override
    protected void initChildView(Bundle savedInstanceState) {
        initToolbar();
        ButterKnife.bind(this);

        if (savedInstanceState != null) {
            initSaveData(savedInstanceState);
        }
        initToolbarListener();
    }

    /**
     * 显示overflower菜单图标
     */
    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
//        if (featureId == Window.FEATURE_ACTION_BAR && menu != null) {
//            if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
//                try {
//                    Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
//                    m.setAccessible(true);
//                    m.invoke(menu, true);
//                } catch (Exception e) {
//                }
//            }
//        }
        return super.onMenuOpened(featureId, menu);
    }

    @Override
    protected MainPresenter getPresenter() {
        if (presenter == null)
            presenter = new MainPresenter(this);
        return presenter;
    }


    private void initSaveData(Bundle outState) {
        toggleOpen = outState.getBoolean("toggle");
        menuPos = outState.getInt("menuPos");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("toggle", toggleOpen);
        outState.putInt("menuPos",menuPos);
    }

    private boolean toggleOpen = false;

    private ActionBarDrawerToggle mDrawerToggle;

    private ActionBarDrawerToggle getToggle() {
        if (mDrawerToggle == null) {
            mDrawerToggle = new ActionBarDrawerToggle(this, mainDrawer, commonToolbar, R.string.open, R.string.close) {
                @Override
                public void onDrawerOpened(View drawerView) {
                    super.onDrawerOpened(drawerView);
                    toggleOpen = true;
                }

                @Override
                public void onDrawerClosed(View drawerView) {
                    super.onDrawerClosed(drawerView);
                    toggleOpen = false;
                }
            };
        }
        return mDrawerToggle;
    }

    private void initDrawerToggle() {
        getToggle().syncState();
        mainDrawer.addDrawerListener(getToggle());
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setTitle(getPresenter().getTitleByPos(0));
        getToggle().syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        getToggle().onConfigurationChanged(newConfig);
    }

    private void initToolbarListener() {
        initDrawerToggle();
    }

    private void toggle() {
        if (toggleOpen) {
            closeMenu();
        } else {
            openMenu();
        }
    }

    private void closeMenu() {
        if (toggleOpen) {
            mainDrawer.closeDrawer(Gravity.LEFT);
        }
    }

    private void openMenu() {
        if (!toggleOpen) {
            mainDrawer.openDrawer(Gravity.LEFT);
        }
    }

    @Override
    protected int getAnimState() {
        return ANIM_MAIN;
    }

    private MainRecyclerMenuAdapter menuAdapter;

    /**
     * 相关：
     * #MainPresenter initData()-> initLeftMenu(List<MainMenuData> leftMenuData)
     * #BaseActivity onCreate(Bundle onSaveInstanceState()->presenter.initData()
     * #MainActivity onCreate(Bundle)->super.onCreate()
     * 思路，基类初始化->子类控件初始化->presenter->interactor->子类数据初始化
     *
     * @param leftMenuData
     */
    @Override
    public void initLeftMenu(List<MainMenuData> leftMenuData) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        menuAdapter = new MainRecyclerMenuAdapter(leftMenuData);
        mRecyclerView.setAdapter(menuAdapter);
        menuAdapter.setOnItemClickListener(new MainRecyclerMenuAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, MainMenuData data) {
                toggle();
                if(menuPos == position)
                    return;
                menuPos = position;

                mViewPager.setCurrentItem(position,false);
                setTitle(data.getTxt());
                invalidateOptionsMenu();
            }
        });
    }

    private void setTitle(String name){
        getSupportActionBar().setTitle(name);
    }

    @Override
    public void initViewPager(List<View> pages) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(pages);
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(0);
        adapter.notifyDataSetChanged();
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(menuPos == position)
                    return;
                menuPos = position;
                setTitle(getPresenter().getTitleByPos(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private int menuPos = 0;



    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.clear();
        MenuInflater inflater = this.getMenuInflater();
        switch (menuPos) {
            case 1:
                inflater.inflate(R.menu.menu_schedule, menu);
                break;
            default:
                //inflater.inflate(R.menu.menu_home, menu);
                break;
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_schedule, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (getToggle().onOptionsItemSelected(item)) {
            return true;
        }

        switch (item.getItemId()) {
            case R.id.action_calendar:
                break;
            case R.id.action_live:
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
