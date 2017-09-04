package com.dyy.nba;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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

    public void startNewThis(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

}
