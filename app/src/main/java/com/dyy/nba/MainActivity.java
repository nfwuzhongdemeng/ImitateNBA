package com.dyy.nba;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.dyy.nba.commonlibs.NothingActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected int getChildView() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolbar();
    }

    @Override
    protected int getAnimState() {
        return ANIM_MAIN;
    }



    public void startNewThis(View view){
        Intent intent = new Intent(this,NothingActivity.class);
        startActivity(intent);
    }

}
