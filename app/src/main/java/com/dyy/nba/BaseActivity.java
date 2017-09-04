package com.dyy.nba;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;

import com.dyy.nba.commonlibs.manager.ActivityManager;
import com.dyy.nba.commonlibs.util.ShareUtils;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        boolean startNew = ShareUtils.getInstance().resetShare("settings").getBoolean("startNew");

         if (startNew && isRightAnim())
            overridePendingTransition(R.anim.slide_right_in, R.anim.slide_no);
        else if(!startNew){
             ShareUtils.getInstance().resetShare("settings").set("startNew", false).commit();
         }
        super.onCreate(savedInstanceState);
        setContentView(getChildView());
        ActivityManager.getInstance().addActivity(this);
    }

    protected boolean isRightAnim() {
        return true;
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        ShareUtils.getInstance().set("startNew", true).resetShare("settings").commit();
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        ShareUtils.getInstance().set("startNew", true).resetShare("settings").commit();
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
