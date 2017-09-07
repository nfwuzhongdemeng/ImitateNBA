package com.dyy.nba.view.layout;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by 段钰莹 on 2017/9/5.
 */

public class OtherLayout extends LinearLayout {
    public OtherLayout(Context context) {
        super(context);
    }

    public OtherLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public OtherLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public OtherLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
