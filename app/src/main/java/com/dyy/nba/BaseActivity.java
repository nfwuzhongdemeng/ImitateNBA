package com.dyy.nba;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;

import com.dyy.nba.commonlibs.manager.ActivityManager;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

         if (getAnimState()==ANIM_OHTER)
            overridePendingTransition(R.anim.slide_right_in, R.anim.slide_no);
        else if(getAnimState() == ANIM_MAIN)
             overridePendingTransition(R.anim.slide_no_time, R.anim.slide_no_time);

        super.onCreate(savedInstanceState);
        setContentView(getChildView());
        ActivityManager.getInstance().addActivity(this);
    }

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
    public void startActivity(Intent intent) {
        super.startActivity(intent);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
    }

    protected abstract int getChildView();

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

    protected void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.common_toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
}
