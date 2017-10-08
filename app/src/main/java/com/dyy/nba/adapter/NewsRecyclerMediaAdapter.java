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
import com.dyy.nba.commonlibs.util.LogUtil;
import com.dyy.nba.http.RequestCallback;
import com.dyy.nba.http.tencent.TopNewsService;
import com.dyy.nba.model.NewsItemBean;
import com.dyy.nba.model.VideoInfo;
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
        final NewsItemBean item = listData.get(position);
        holder.itemView.setTag(position);

        if(getItemViewType(position) == VIDEO){
            setVideoLineData(holder,item);

        }else{
            setPictureLineData(holder,item);

        }

        animUtil.showAnimDown(holder.itemView,position);
    }

    private void setVideoLineData(final RecyclerView.ViewHolder holder, final NewsItemBean item) {
        if(!(holder instanceof VideoViewHolder)){
            return;
        }

        VideoViewHolder videoViewHolder = (VideoViewHolder) holder;

        videoViewHolder.txtTitle.setText(item.title);
        videoViewHolder.ivWatchWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = (int) v.getTag();
                if(pos == (int)(holder.itemView.getTag())){
                    openVideoInBrowser(item);
                }
            }
        });

        initVideoUrl(videoViewHolder.videoPlayer,item);
        videoViewHolder.videoPlayer.initFitSize(ViewGroup.LayoutParams.MATCH_PARENT,AppUtils.getScreenWidth() / 2);
        videoViewHolder.videoPlayer.thumbImageView.setController(FrescoUtils.getController(item.imgurl, videoViewHolder.videoPlayer.thumbImageView));
    }

    private void setPictureLineData(RecyclerView.ViewHolder holder, NewsItemBean item) {
        if(!(holder instanceof PictureViewHolder)){
            return;
        }

        PictureViewHolder pictureViewHolder = (PictureViewHolder) holder;
        pictureViewHolder.txtTitle.setText(item.title);
        pictureViewHolder.txtTime.setText(item.pub_time);

        if (pictureViewHolder.draweeView != null) { // @bugreport NullPointerException
            pictureViewHolder.draweeView.setController(FrescoUtils.getController(item.imgurl, pictureViewHolder.draweeView));
            initPictureMeausure(pictureViewHolder.draweeView);
        }
    }

    private void initPictureMeausure(SimpleDraweeView showPictureView) {
        ViewGroup.LayoutParams params = showPictureView.getLayoutParams();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = AppUtils.getScreenWidth() / 2;

        showPictureView.setLayoutParams(params);
    }

    private void openVideoInBrowser(NewsItemBean item) {
    }

    private void initVideoUrl(JCVideoPlayerStandard videoPlayer, NewsItemBean item) {
        videoPlayer.setUp("", item.title);
        if(item.realUrl == null || item.realUrl.length() == 0){
            initPlayUrlByRedirect(videoPlayer,item);
        }else{
            videoPlayer.setUp(item.realUrl, item.title);
        }
    }

    private void initPlayUrlByRedirect(final JCVideoPlayerStandard videoPlayer, final NewsItemBean item) {
        TopNewsService.getVideoRealUrls(item.vid, new RequestCallback<VideoInfo>() {
            @Override
            public void onSuccess(VideoInfo videoRedirectInfo) {
                if (hasRedirectUrl(videoRedirectInfo)) {
                    String redirectUrl = getRedirectUrl(videoRedirectInfo);

                    item.realUrl = redirectUrl;

                    setPlayUrlByRedirect(videoPlayer,redirectUrl,item.title);
                }
            }

            @Override
            public void onFailure(String message) {
                LogUtil.i("real-url：" + message);
            }
        });
    }

    private String getRedirectUrl(VideoInfo videoRedirectInfo) {
        String vid = videoRedirectInfo.vl.vi.get(0).vid;
        String vkey = videoRedirectInfo.vl.vi.get(0).fvkey;
        return videoRedirectInfo.vl.vi.get(0).ul.ui.get(0).url + vid + ".mp4?vkey=" + vkey;
    }

    private void setPlayUrlByRedirect(JCVideoPlayerStandard videoPlayer, String redirectUrl,String title) {
            videoPlayer.setUp(redirectUrl, title);

            LogUtil.i("title：" + title);
            LogUtil.i("real-url：" + redirectUrl);
    }

    private boolean hasRedirectUrl(VideoInfo videoRedirectInfo) {
        return videoRedirectInfo.vl.vi != null && videoRedirectInfo.vl.vi.size() > 0;
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