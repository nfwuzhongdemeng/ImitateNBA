package com.dyy.nba.commonlibs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public abstract class CommonBaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_base);
    }

    @Override
    protected  final void onRestart() {
        super.onRestart();
    }

    @Override
    protected final void onStart() {
        super.onStart();
    }

    @Override
    protected final void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected final void onPause() {
        super.onPause();
    }

    @Override
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        onAttachToDecorView();
    }

    /**
     * 绑定decorview
     */
    public abstract void onAttachToDecorView();

    /**
     * 解绑decorview
     */
    public abstract void onDetachToDecorView();
    @Override
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        onDetachToDecorView();
    }

    @Override
    public final void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            onStartDataDimen();
            //num = getIntent().getExtra("num",0);
        }else{
            onPauseDataDimen();
            //getIntent();putExtra("num",num);
        }
    }

    /**
     * 代替start，用于初始化数据，尺寸
     */
    public abstract void onStartDataDimen();

    /**
     * 代替pause，用于保存数据
     */
    public abstract void onPauseDataDimen();
}
