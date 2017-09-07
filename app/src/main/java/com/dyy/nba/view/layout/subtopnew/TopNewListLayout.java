package com.dyy.nba.view.layout.subtopnew;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.TextView;

import com.dyy.nba.R;
import com.dyy.nba.d.container.TopNewListIndicator;
import com.dyy.nba.p.container.TopNewListPresenter;
import com.dyy.nba.v.container.ITopNewBaseView;
import com.dyy.nba.view.layout.BaseLinearContainer;

/**
 * Created by 段钰莹 on 2017/9/6.
 */

public class TopNewListLayout extends BaseLinearContainer<ITopNewBaseView,TopNewListIndicator,TopNewListPresenter> implements ITopNewBaseView {
    public TopNewListLayout(Context context) {
        super(context);
    }

    public TopNewListLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TopNewListLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TopNewListLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected TopNewListPresenter getPresenter() {
        if(presenter == null)
            presenter = new TopNewListPresenter(this);
        return presenter;
    }

    public void setText(String txt){
        TextView txtView = (TextView) getViewById(R.id.top_new_txt_sub);
        txtView.setText(txt);
    }
}
