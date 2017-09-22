package com.dyy.nba.commonlibs.util;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.dyy.nba.commonlibs.R;

/**
 * Created by 段钰莹 on 2017/9/8.
 */

public class ItemAnimUtil {
    private  int mLastPosition = -1;

    public  void showAnimDown(final View view, final int position) {
        if (position > mLastPosition) {
            Animation animation = AnimationUtils.loadAnimation(view.getContext(), R.anim.item_bottom_in);
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    view.setAlpha(1);
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }
            });
            view.startAnimation(animation);
            mLastPosition = position;
        }
    }
}
