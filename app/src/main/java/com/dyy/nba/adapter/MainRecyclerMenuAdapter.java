package com.dyy.nba.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dyy.nba.R;
import com.dyy.nba.commonlibs.AppUtils;
import com.dyy.nba.model.listdata.MainMenuData;

import java.util.List;

/**
 * Created by 段钰莹 on 2017/9/5.
 */

public class MainRecyclerMenuAdapter extends RecyclerView.Adapter<MainRecyclerMenuAdapter.MyViewHolder> {
    private List<MainMenuData> listData;
    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position, MainMenuData data);
    }

    public MainRecyclerMenuAdapter(List<MainMenuData> listData) {
        this.listData = listData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_menu, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        MainMenuData data = listData.get(position);
        holder.tv.setText(data.getTxt());
        holder.img.setImageResource(data.getImgId());
    }

    @Override
    public int getItemCount() {
        return listData == null ? 0 : listData.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView tv;

        public MyViewHolder(final View view) {
            super(view);
            tv = (TextView) view.findViewById(R.id.item_main_menu_tv_menu_txt);
            img = (ImageView) view.findViewById(R.id.item_main_menu_iv_left_icon);
            view.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN: {
                            view.setBackgroundColor(AppUtils.getResources().getColor(R.color.tranGray));
                            break;
                        }
                        case MotionEvent.ACTION_UP:
                            if (listener != null) {
                                int pos = getLayoutPosition();
                                listener.onItemClick(pos, listData.get(pos));
                            }
                        case MotionEvent.ACTION_CANCEL:
                            view.setBackgroundColor(AppUtils.getResources().getColor(R.color.white));
                            break;
                    }

                    return true;
                }
            });
        }
    }

}
