package com.dyy.nba.commonlibs.view;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dyy.nba.commonlibs.R;
import com.dyy.nba.commonlibs.util.ColorUtil;


/**
 * Created by Administrator on 2017/7/6.
 */

public class TabLayout extends HorizontalScrollView {
    private int noSelectWordColor = 0x00000000;
    private int selectWordColor = 0x00000000;
    private int dividerColor = 0x00000000;
    private float dividerTopBottom;
    private int allColor = 0x00000000;
    private Drawable underlineDrawable;
    private int underlineColor = 0x00000000;
    private float underlineLeftRight;
    private float dividerWidth;
    private float underlineHeight;

    public TabLayout(Context context) {
        super(context);
    }

    public TabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public TabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public TabLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init(AttributeSet attrs) {
        TypedArray t = getContext().obtainStyledAttributes(attrs, R.styleable.TabLayout);
        allColor = t.getColor(R.styleable.TabLayout_allColor, allColor);
        if (allColor != 0x00000000) {
            dividerColor = allColor;
        }
        noSelectWordColor = t.getColor(R.styleable.TabLayout_noSelectWordColor, noSelectWordColor);
        selectWordColor = t.getColor(R.styleable.TabLayout_selectWordColor, selectWordColor);
        dividerColor = t.getColor(R.styleable.TabLayout_dividerColor, dividerColor);
        dividerTopBottom = t.getDimension(R.styleable.TabLayout_dividerTopBottom, dividerTopBottom);
        underlineDrawable = t.getDrawable(R.styleable.TabLayout_underlineDrawable);
        if (underlineDrawable == null) {
            underlineColor = t.getColor(R.styleable.TabLayout_underlineDrawable, underlineColor);
            if (underlineColor == 0x00000000) {
                underlineColor = allColor;
            }
        }
        underlineLeftRight = t.getDimension(R.styleable.TabLayout_underlineLeftRight, underlineLeftRight);
        dividerWidth = t.getDimension(R.styleable.TabLayout_dividerWidth, dividerWidth);
        underlineHeight = t.getDimension(R.styleable.TabLayout_underlineHeight, underlineHeight);
    }

    private int selectedPos = 0;
    private int width = 0;
    private int ceilCount = 0;
    private LinearLayout container;


    private float followOffset = 0;
    private boolean fromTab =false;
    public void followPos(int position, float positionOffset) {
        followPos = position;


        if(fromTab){
            int moved = 0;
            if(selectedPos!=container.getChildCount()-1){
                int leftNext = container.getChildAt(selectedPos+1).getLeft();
                int leftNow = container.getChildAt(selectedPos).getLeft();
                 moved = (int) ((leftNext - leftNow)*positionOffset);

            }else{
                int leftNow = container.getChildAt(selectedPos).getLeft();
                int leftLast = container.getChildAt(selectedPos-1).getLeft();
                moved = (int) ((leftNow - leftLast)*positionOffset);
            }

            int newLeft = container.getChildAt(newPos).getLeft();
            int oldLeft = container.getChildAt(selectedPos).getLeft();
            int posLeft = container.getChildAt(position).getLeft();
            int moveDistance = posLeft - oldLeft + moved;

            int allDistance = newLeft-oldLeft;
            if(allDistance == 0){
                followOffset = 0;
            }else
                followOffset = moveDistance*1.0f / allDistance;

            if(followOffset == 1) {
                followOffset = 0;
                selectedPos = newPos;
            }

            int realLeft = posLeft + moved;
            int realWidth = container.getChildAt(selectedPos).getWidth();
            int scrollPos = (int) (realLeft - (getWidth() - realWidth) / 2);
            scrollTo(scrollPos, 0);

        }else{
            if(positionOffset != 0)
                newPos = followPos +  1;
            else
                newPos = followPos;
            followOffset = positionOffset;

            int posLeft = container.getChildAt(position).getLeft();
            int moved = (int) (container.getChildAt(position).getWidth() * followOffset);
            int realLeft = posLeft + moved;
            int realWidth = container.getChildAt(selectedPos).getWidth();
            int scrollPos = (int) (realLeft - (getWidth() - realWidth) / 2);
            scrollTo(scrollPos, 0);
        }

        invalidate();
    }


    public interface OnTabSelectedListener {
        void onSelect(int position);
    }

    private OnTabSelectedListener listener;

    public void setOnTabSelectedListener(OnTabSelectedListener listener) {
        this.listener = listener;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        if (getChildCount() == 0)
            return;
        initData();
    }

    private void initData() {
        container = (LinearLayout) getChildAt(0);

        for (int i = 0; i < container.getChildCount(); i++) {
            TextView view = (TextView) container.getChildAt(i);
            view.setTag(i);
            if (i == 0)
                view.setTextColor(selectWordColor);
            else
                view.setTextColor(noSelectWordColor);
            view.setOnClickListener(onClickListener);
        }
        invalidate();
    }


    private int newPos = -1;

    private int progress = 0;

    private OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {

            newPos = (int) v.getTag();
            if(selectedPos == newPos)
                return;
            if(selectedPos > newPos){
                followOffset = 1.0f - followOffset;
            }
            select(newPos);
        }
    };

    private float ceils[];

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //去除滚动条和半弧背景
        setHorizontalScrollBarEnabled(false);
        setHorizontalFadingEdgeEnabled(false);
        setOverScrollMode(OVER_SCROLL_NEVER);
        if (isInEditMode()) {
            return;
        }
        if (width == 0) {
            width = container.getWidth();
            ceilCount = container.getChildCount();
            ceils = new float[ceilCount];
            if (container.getChildCount() != 0) {
                for (int i = 0; i < container.getChildCount(); i++) {
                    ceils[i] = container.getChildAt(i).getWidth();
                }
            }
        }

        if (dividerWidth != 0) {
            drawDivider(canvas);
        }

        if (underlineHeight != 0) {
            drawUnderline(canvas);
        }

    }


    private Paint underlinePaint = null;

    private void drawUnderline(Canvas canvas) {
        if (underlinePaint == null) {
            underlinePaint = new Paint();
            underlinePaint.setAntiAlias(true);
            underlinePaint.setStyle(Paint.Style.FILL);
            underlinePaint.setColor(underlineColor);
        }

        if (container.getChildCount() > 0 ) {
                drawFollowMode(canvas);
        }
    }



    private void drawFollowMode(Canvas canvas) {
        if(ceils == null || ceils.length == 0)
            return;

        int nowIndex = 0;
        int nextIndex = 0;
        nextIndex = newPos;
        if(fromTab){
            nowIndex = selectedPos;

        }else{
            nowIndex = followPos;
        }

        float posLeft =  container.getChildAt(nowIndex).getLeft();
        float aidLeft = container.getChildAt(nextIndex).getLeft();
        float realLeft = (aidLeft - posLeft) * followOffset + posLeft;

        float postWidth =ceils[nowIndex];
        float aidWidth = ceils[nextIndex];
        float realWidth = (aidWidth - postWidth) * followOffset + postWidth;

        float left = realLeft + underlineLeftRight;
        float top = getHeight() - underlineHeight;
        float right = realLeft + realWidth - underlineLeftRight;
        float bottom = getHeight();

        //下滑线/背景
        if (underlineDrawable == null) {
            canvas.drawRect(left, top, right, bottom, underlinePaint);
        } else {
            underlineDrawable.setBounds((int) left, (int) top, (int) right, (int) bottom);
            underlineDrawable.draw(canvas);
        }


        if(followOffset < 0){
            followOffset = 0;
        }
        if(followOffset > 1)
            followOffset = 1;
        //渐变颜色计算
        int color = ColorUtil.caculateColor(selectWordColor, noSelectWordColor, followOffset);
        int color2 = ColorUtil.caculateColor(noSelectWordColor, selectWordColor, followOffset);

        TextView tv = (TextView) container.getChildAt(nowIndex);
        TextView tv2 = (TextView) container.getChildAt(nextIndex);


        tv2.setTextColor(color2);
        tv.setTextColor(color);
        if(fromTab && followPos == newPos && followOffset == 0){
            //数据还原，模拟viewpager滑动时数据
            fromTab = false;
            selectedPos = newPos;

            int scrollPos = (int) (realLeft - (getWidth() - realWidth) / 2);
            if(scrollPos < 0)
                scrollPos = 0;
            if(scrollPos > getWidth())
                scrollPos = getWidth();
            smoothScrollTo(scrollPos, 0);
            resetColor();

        }else if(followOffset == 0 && !fromTab) {
            if(selectedPos == followPos)
                return;
            final int scrollPos = (int) (realLeft - (getWidth() - realWidth) / 2);
            smoothScrollTo(scrollPos, 0);
            selectedPos = followPos;
            resetColor();
        }
    }

    private void resetColor() {
        for(int i = 0; i < container.getChildCount(); i++){
            //颜色残留时重置
            if(i != newPos){
                TextView temp = (TextView) container.getChildAt(i);
                temp.setTextColor(Color.WHITE);
            }
        }
    }

    private int followPos = 0;

    private void select(int selectedPos) {
        if (listener != null) {
            listener.onSelect(selectedPos);
        }

        if (outListener != null)
            outListener.onSelect(selectedPos);
    }

    private boolean end = true;

    private Paint dividerPaint = null;

    private void drawDivider(Canvas canvas) {
        if (dividerPaint == null) {
            dividerPaint = new Paint();
            dividerPaint.setAntiAlias(true);
            dividerPaint.setStyle(Paint.Style.FILL);
            dividerPaint.setColor(dividerColor);
        }

        float left = ceils[0] - dividerWidth / 2;
        float top = dividerTopBottom;
        float right = left + dividerWidth;
        float bottom = getHeight() - dividerTopBottom - underlineHeight;

        for (int i = 0; i < ceilCount - 1; i++) {
            canvas.drawRect(left, top, right, bottom, dividerPaint);
            left = left + ceils[i + 1];
            right = left + dividerWidth;
        }

    }

    /**
     * 添加标签
     * @param tabs                  标签列表
     * @param paddingLeftRight    左右间距
     * @param paddingTopBottom    上下间距
     * @param textSize              字号
     */
    public void addTabs(String[] tabs, int paddingLeftRight, int paddingTopBottom, int textSize) {
        container = new LinearLayout(getContext().getApplicationContext());
        container.setOrientation(LinearLayout.HORIZONTAL);
        addView(container);

        for (int i = 0; i < tabs.length; i++) {
            String name = tabs[i];
            TextView tv = new TextView(getContext().getApplicationContext());
            tv.setText(name);
            tv.setTextSize(textSize);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
            tv.setLayoutParams(params);
            tv.setGravity(Gravity.CENTER);
            tv.setPadding(paddingLeftRight, paddingTopBottom, paddingLeftRight, paddingTopBottom);
            container.addView(tv);
        }
        initData();
    }

    private SelectTabListener outListener;//注意内部切换上门页卡，然后下面响应

    public void setOnSelectTabListener(SelectTabListener outListener) {
        this.outListener = outListener;
    }

    public interface SelectTabListener {
        void onSelect(int position);
    }

    public void addTabsMatch(String[] tabs, int padding) {
        container = new LinearLayout(getContext().getApplicationContext());
        int width = getScreenWidth((Activity) getContext());
        int ceilWidth = width / tabs.length;
        container.setOrientation(LinearLayout.HORIZONTAL);
        addView(container);

        for (int i = 0; i < tabs.length; i++) {
            String name = tabs[i];
            TextView tv = new TextView(getContext().getApplicationContext());
            tv.setText(name);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ceilWidth, ViewGroup.LayoutParams.MATCH_PARENT);
            tv.setLayoutParams(params);
            tv.setGravity(Gravity.CENTER);
            tv.setLongClickable(false);
            tv.setPadding(padding, padding, padding, padding);
            container.addView(tv);
        }
        initData();
    }

    private int getScreenWidth(Activity activity) {
        WindowManager wm = activity.getWindowManager();
        int height = wm.getDefaultDisplay().getHeight();
        int width = wm.getDefaultDisplay().getWidth();
        return height < width ? height : width;
    }

    private final String TAG = "TabLayout";
    public void setTab(int position){
        if(ceils == null || ceils.length == 0)
            return;
        fromTab = true;
        if(container!=null && container.getChildCount()>position){
            container.getChildAt(position).performClick();
        }
    }

}
