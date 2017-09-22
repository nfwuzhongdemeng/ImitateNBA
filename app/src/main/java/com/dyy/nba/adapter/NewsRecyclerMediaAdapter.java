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
import com.dyy.nba.commonlibs.util.ItemAnimUtil;
import com.dyy.nba.model.NewsItemBean;
import com.dyy.nba.util.FrescoUtils;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by 段钰莹 on 2017/9/8.
 */

public class NewsRecyclerMediaAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<NewsItemBean> listData;
    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position, NewsItemBean data);
    }

    public NewsRecyclerMediaAdapter(List<NewsItemBean> listData) {
        this.listData = listData;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder;
        if(viewType == VIDEO){
            holder = new VideoViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_top_new_video, parent,
                    false));
        }else{
            holder = new PictureViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_top_new_picture, parent,
                    false));
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        NewsItemBean item = listData.get(position);
        holder.itemView.setTag(position);
        if(getItemViewType(position) == VIDEO){
            if(!(holder instanceof VideoViewHolder)){
                return;
            }
            VideoViewHolder videoViewHolder = (VideoViewHolder) holder;
            String title = item.title;
            String imgUrl = item.imgurl;
            String url = item.url;
            String realUrl = item.realUrl;

            videoViewHolder.txtTitle.setText(item.title);
            videoViewHolder.ivWatchWeb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = (int) v.getTag();
                    String url = listData.get(pos).url;
                    //开网页
                }
            });
            videoViewHolder.videoPlayer.setUp("", item.title);
            if(realUrl == null || realUrl.length() == 0){
                //重新获取url
            }else{
                videoViewHolder.videoPlayer.setUp(item.realUrl, item.title);
            }

            videoViewHolder.videoPlayer.thumbImageView.setController(FrescoUtils.getController(item.imgurl, videoViewHolder.videoPlayer.thumbImageView));
            ViewGroup.LayoutParams params = videoViewHolder.videoPlayer.getLayoutParams();
            params.height = AppUtils.getScreenWidth() / 2;
            params.width = ViewGroup.LayoutParams.MATCH_PARENT;
            videoViewHolder.videoPlayer.setLayoutParams(params);

        }else{
            if(!(holder instanceof PictureViewHolder)){
                return;
            }

            PictureViewHolder pictureViewHolder = (PictureViewHolder) holder;
            String title = item.title;
            String imgUrl = item.imgurl;
            String pub_time = item.pub_time;

            pictureViewHolder.txtTitle.setText(title);
            pictureViewHolder.txtTime.setText(pub_time);

            if (pictureViewHolder.draweeView != null) { // @bugreport NullPointerException
                pictureViewHolder.draweeView.setController(FrescoUtils.getController(item.imgurl, pictureViewHolder.draweeView));
                ViewGroup.LayoutParams params = pictureViewHolder.draweeView.getLayoutParams();
                params.height = AppUtils.getScreenWidth() / 2;
                params.width = ViewGroup.LayoutParams.MATCH_PARENT;
                pictureViewHolder.draweeView.setLayoutParams(params);
            }

        }

        animUtil.showAnimDown(holder.itemView,position);
    }
    ItemAnimUtil animUtil = new ItemAnimUtil();


    @Override
    public int getItemCount() {
        return listData == null ? 0 : listData.size();
    }

    class PictureViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView draweeView;
        TextView txtTitle;
        TextView txtTime;

        public PictureViewHolder(final View view) {
            super(view);
            draweeView = (SimpleDraweeView) view.findViewById(R.id.item_top_new_drawee);
            txtTitle = (TextView) view.findViewById(R.id.item_top_new_txt_title);
            txtTime = (TextView) view.findViewById(R.id.item_top_new_txt_time);

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
                                int pos = (int) v.getTag();
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

    class VideoViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle;
        ImageView ivWatchWeb;
        JCVideoPlayerStandard videoPlayer;

        public VideoViewHolder(final View view) {
            super(view);
            txtTitle = (TextView) view.findViewById(R.id.item_top_new_txt_title);
            ivWatchWeb = (ImageView) view.findViewById(R.id.temp_new_img_out_watch);
            videoPlayer = (JCVideoPlayerStandard) view.findViewById(R.id.temp_new_video_content);
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


    private static final int PICTURE = 1;
    private static final int VIDEO = 2;
    @Override
    public int getItemViewType(int position) {
        return Integer.parseInt(listData.get(position).atype);
    }
}