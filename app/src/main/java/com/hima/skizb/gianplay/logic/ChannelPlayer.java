package com.hima.skizb.gianplay.logic;

import android.annotation.SuppressLint;
//import android.media.MediaMetadata;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.cast.RemoteMediaPlayer;
import com.google.android.gms.common.api.ResultCallback;
import com.hima.skizb.gianplay.MainActivity;
import com.hima.skizb.gianplay.R;
import com.hima.skizb.gianplay.gui.FullScreenActivity;

/**
 * Created by root on 6/10/18.
 */


public class ChannelPlayer{
    private String sname;
    private String svideoUrl;
    private Button castButton;
    private VideoView vv;
    private MainActivity context;
    private MediaRouterForCast MRFC;
    private Button chButton;
    private MediaPlayer mp;

    private int videoUrl;
    private int videoViewID;
    private int name;

    private pcSocketClient pcsc = null;

    boolean isVisible = true;


    public ChannelPlayer(final MainActivity con, MediaRouterForCast MRFCm, int nameM, int urlm, int videoViewID, int textId, int castbuttId){
        vv=null;
        this.context = con;
        this.videoUrl = urlm;
        this.videoViewID = videoViewID;
        this.MRFC =MRFCm;
        this.name = nameM;

        chButton = (Button) context.findViewById(textId);
        chButton.setText(name);

        castButton = (Button) context.findViewById(castbuttId);

//        setVisibility(false);


        try {
            startVideo(0);
        }catch (Exception e){Log.d("ChannelPlayer: Run: ",e+"");}

        makeVideoFullScreen();

    }

    public ChannelPlayer(final MainActivity con, MediaRouterForCast MRFCm, String nameM, String urlm, int videoViewID, int textId, int castbuttId){
        vv=null;
        this.context = con;
        this.svideoUrl = urlm;
        this.videoViewID = videoViewID;
        this.MRFC =MRFCm;
        this.sname = nameM;

        chButton = (Button) context.findViewById(textId);
        if (this.sname == null) this.sname = "Unknown";
        chButton.setText(sname);

        castButton = (Button) context.findViewById(castbuttId);

//        setVisibility(false);


        try {
            startVideo(0);
        }catch (Exception e){Log.d("ChannelPlayer: Run: ",e+"");}

        makeVideoFullScreen();

    }

    private void makeVideoFullScreen(){
        if(context.section!=8) {
            chButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "Loading On a Full Screen", Toast.LENGTH_SHORT).show();
//                context.currentChannelsToDefault();
//                context.stopChannels();
//                context.setVideoSizeToDefault();
                    context.isVideoStopped = false;
                    context.bp.resumePauseButPressed();

                    Intent fullScreen = new Intent(context, FullScreenActivity.class);
                    Bundle args = new Bundle();
                    args.putInt("link", videoUrl);
                    fullScreen.putExtras(args);
                    context.startActivity(fullScreen);
                }
            });
        }else {
            chButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "Loading On a Full Screen", Toast.LENGTH_SHORT).show();
//                context.currentChannelsToDefault();
//                context.stopChannels();
//                context.setVideoSizeToDefault();
                    context.isVideoStopped = false;
                    context.bp.resumePauseButPressed();

                    Intent fullScreen = new Intent(context, FullScreenActivity.class);
                    Bundle args = new Bundle();
                    if(context.section!=8) {
                        args.putInt("option", 1);
                        args.putInt("link", videoUrl);
                    }else {
                        args.putInt("option", 2);
                        args.putString("slink", svideoUrl);
                    }
                    fullScreen.putExtras(args);
                    context.startActivity(fullScreen);
                }
            });
        }
    }


//    @SuppressLint("ClickableViewAccessibility")
    private void castVideo(){
        if(context.section!=8) {
            castButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                MediaMetadata mediaMetadata = new MediaMetadata(MediaMetadata.MEDIA_TYPE_MOVIE);
                                mediaMetadata.putString(MediaMetadata.KEY_TITLE, context.getString(name));

                                MediaInfo mediaInfo = new MediaInfo.Builder(context.getString(videoUrl))
                                        .setContentType("@string/content_type_mp4")
                                        .setStreamType(MediaInfo.STREAM_TYPE_BUFFERED)
                                        .setMetadata(mediaMetadata)
                                        .build();

                                MRFC.mRemoteMediaPlayer.load(MRFC.mApiClient, mediaInfo, true)
                                        .setResultCallback(new ResultCallback<RemoteMediaPlayer.MediaChannelResult>() {
                                            @Override
                                            public void onResult(RemoteMediaPlayer.MediaChannelResult mediaChannelResult) {
                                                if (mediaChannelResult.getStatus().isSuccess()) {
//                                context.mVideoIsLoaded = true;
                                                }
                                            }
                                        });
                            } catch (Exception e) {
                            }
//                Toast.makeText(context,context.getString(name), Toast.LENGTH_SHORT).show();
                        }
                    }).start();

                    castToPC(context.getString(videoUrl));

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    context.mp.allVideoTextToDefault();
                    chButton.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                }
            });
        }else{
            castButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                MediaMetadata mediaMetadata = new MediaMetadata(MediaMetadata.MEDIA_TYPE_MOVIE);
                                mediaMetadata.putString(MediaMetadata.KEY_TITLE, sname);

                                MediaInfo mediaInfo = new MediaInfo.Builder(svideoUrl)
                                        .setContentType("@string/content_type_mp4")
                                        .setStreamType(MediaInfo.STREAM_TYPE_BUFFERED)
                                        .setMetadata(mediaMetadata)
                                        .build();

                                MRFC.mRemoteMediaPlayer.load(MRFC.mApiClient, mediaInfo, true)
                                        .setResultCallback(new ResultCallback<RemoteMediaPlayer.MediaChannelResult>() {
                                            @Override
                                            public void onResult(RemoteMediaPlayer.MediaChannelResult mediaChannelResult) {
                                                if (mediaChannelResult.getStatus().isSuccess()) {
//                                context.mVideoIsLoaded = true;
                                                }
                                            }
                                        });
                            } catch (Exception e) {
                            }
//                Toast.makeText(context,context.getString(name), Toast.LENGTH_SHORT).show();
                        }
                    }).start();

                    castToPC(svideoUrl);

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    context.mp.allVideoTextToDefault();
                    chButton.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                }
            });
        }
    }

    private void castToPC(String link) {
        if(pcsc==null)pcsc=context.getpcSocket();

        if(pcsc!=null){
            pcsc.sendLink(link);
        }
    }

    public void vidSizeToDefault(){
//        DisplayMetrics metrics = new DisplayMetrics(); context.getWindowManager().getDefaultDisplay().getMetrics(metrics);
//        android.widget.LinearLayout.LayoutParams params = (android.widget.LinearLayout.LayoutParams) vv.getLayoutParams();
        DisplayMetrics metrics = new DisplayMetrics(); context.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        android.widget.LinearLayout.LayoutParams params = (android.widget.LinearLayout.LayoutParams) vv.getLayoutParams();
        params.width =  metrics.widthPixels;
        params.height = metrics.heightPixels/3;
        params.setLayoutDirection(ViewGroup.LayoutParams.MATCH_PARENT);
//        params.leftMargin = (int) ((metrics.widthPixels/2)-(metrics.widthPixels/4));
//        params.rightMargin= params.getMarginEnd();
        vv.setLayoutParams(params);
    }

    private void startVideo(final int vol){
        if(context.section!=8) {
            Uri url = Uri.parse(context.getString(videoUrl));
            vv = (VideoView) context.findViewById(videoViewID);
            vidSizeToDefault();
            vv.setVideoURI(url);
            castVideo();
//        vv.setOnTouchListener(OnTouchListener);

            vv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @SuppressLint("ClickableViewAccessibility")
                @Override
                public void onPrepared(final MediaPlayer mediaPlayer) {
                    mp = mediaPlayer;
                    mp.setVolume(vol, vol);
                    mp.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                        @Override
                        public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
                            return true;
                        }
                    });
                    mediaPlayer.setLooping(true);
                    vv.start();


//                    setVisibility(true);
                }
            });
        }else {
            Uri url = Uri.parse(svideoUrl);
            vv = (VideoView) context.findViewById(videoViewID);
            vidSizeToDefault();
            vv.setVideoURI(url);
            castVideo();
//        vv.setOnTouchListener(OnTouchListener);

            vv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @SuppressLint("ClickableViewAccessibility")
                @Override
                public void onPrepared(final MediaPlayer mediaPlayer) {
                    mp = mediaPlayer;
                    mp.setVolume(vol, vol);
                    mp.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                        @Override
                        public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
                            return true;
                        }
                    });
                    mediaPlayer.setLooping(true);
                    vv.start();


//                    setVisibility(true);
                }
            });
        }

    }

    public void resumeVideo(){
        vv.resume();
    }

    public void stopVideo(){
        vv.stopPlayback();
    }

    public void destruct(){
//        OnTouchListener = null;
//        vv.suspend();
//        this.destruct();
    }

    public void setTextViewColorToDefault(){
        chButton.setTextColor(context.getResources().getColor(R.color.black));
    }

    public void setVisibility(boolean visibility, boolean all){
        if(visibility){
            resumeVideo();
//            if(all) {
            chButton.setVisibility(View.VISIBLE);
            castButton.setVisibility(View.VISIBLE);
//            }
            vv.setVisibility(VideoView.VISIBLE);
            isVisible=true;
        }else {
            isVisible=false;
            stopVideo();
            if(all) {
                chButton.setVisibility(View.GONE);
                castButton.setVisibility(View.GONE);
            }
            vv.setVisibility(VideoView.GONE);
        }
    }
}
