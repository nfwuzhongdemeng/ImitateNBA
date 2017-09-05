package com.dyy.nba;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.dyy.nba.commonlibs.AppUtils;
import com.dyy.nba.p.BasePresenter;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SplashActivity extends BaseActivity {

    @BindView(R.id.splash_image_load)
    ImageView splashImageLoad;
    @BindView(R.id.splash_txt_second)
    TextView splashTxtSecond;

    private int[]pics= {
            R.drawable.bryant,
            R.drawable.curry,
            R.drawable.harden,
            R.drawable.irving,
            R.drawable.james
    };
    @Override
    protected int getChildView() {
        return R.layout.activity_splash;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //设置去除ActionBar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initChildView(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        Random random = new Random();
        int num = random.nextInt(5);

        splashImageLoad.setImageResource(pics[num]);

        startTimer();
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected int getAnimState() {
        return ANIM_SPLASH;
    }

    private CountDownTimer timer;
    private void startTimer() {
         timer = new CountDownTimer(3500, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                int second = (int) (millisUntilFinished / 1000);
                String str = AppUtils.getResources().getString(R.string.splash_second_txt,second);
                splashTxtSecond.setText(str);
            }

            @Override
            public void onFinish() {
                String str = AppUtils.getResources().getString(R.string.splash_second_txt,0);
                splashTxtSecond.setText(str);
                startMain();
            }
        };
        timer.start();
    }

    private void startMain(){
        Intent intent = new Intent(SplashActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.splash_txt_second)
    public void onViewClicked() {
        if(timer != null){
            timer.cancel();
        }
        startMain();
    }
}
