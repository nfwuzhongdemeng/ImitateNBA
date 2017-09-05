package com.dyy.nba;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;

import com.dyy.nba.commonlibs.AppUtils;
import com.dyy.nba.commonlibs.manager.ActivityManager;
import com.dyy.nba.d.IInteractor;
import com.dyy.nba.p.BasePresenter;
import com.dyy.nba.v.IView;

/**
 * 注意所有子类必须实现泛型B替换的接口
 * @param <B>   mvp的v层
 * @param <T>   mvp的p层
 */
public abstract class BaseActivity<B extends IView, D extends IInteractor,T extends BasePresenter<B,D>> extends AppCompatActivity {
    protected T presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

         if (getAnimState()==ANIM_OHTER)
            overridePendingTransition(R.anim.slide_right_in, R.anim.slide_no);
        else if(getAnimState() == ANIM_MAIN)
             overridePendingTransition(R.anim.slide_no_time, R.anim.slide_no_time);

        super.onCreate(savedInstanceState);
        setContentView(getChildView());
        
        initChildView(savedInstanceState);
        
        presenter = getPresenter();

        if(presenter!=null)
            presenter.initData();
        ActivityManager.getInstance().addActivity(this);
    }

    protected abstract void initChildView(Bundle savedInstanceState);

    protected abstract T getPresenter() ;

    @Override
    protected void onResume() {
        super.onResume();
    }

    protected static final int ANIM_SPLASH = 1;
    protected static final int ANIM_MAIN = 2;
    protected static final int ANIM_OHTER = 3;
    protected int getAnimState() {
        return ANIM_OHTER;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            overridePendingTransition(R.anim.slide_no, R.anim.slide_right_out);
            ActivityManager.getInstance().removeActivity(this);
        }
        return false;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        overridePendingTransition(R.anim.slide_no, R.anim.slide_right_out);
        ActivityManager.getInstance().removeActivity(this);
    }

    protected abstract int getChildView();

    protected void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.common_toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            toolbar.setTitle(getToolbarName());//设置Toolbar标题
            toolbar.setTitleTextColor(getToolbarColor()); //设置标题颜色
        }
    }

    protected int getToolbarColor() {
        return Color.parseColor("#ffffff");
    }

    protected String getToolbarName(){
        return AppUtils.getResources().getString(R.string.base_title_default);
    }
}
