package com.hima.skizb.gianplay;

import android.annotation.SuppressLint;
import android.media.AudioAttributes;
//import android.media.MediaMetadata;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.cast.RemoteMediaPlayer;
import com.google.android.gms.common.api.ResultCallback;

import java.net.URL;

/**
 * Created by root on 6/10/18.
 */


public class ChannelPlayer{
    private VideoView vv;
    private MainActivity context;
    private MediaRouterForCast MRFC;
    private TextView textView;

    private int videoUrl;
    private int videoViewID;
    private int name;

    public boolean isVisible = true;

    private View.OnTouchListener OnTouchListener;




    public ChannelPlayer(final MainActivity con, MediaRouterForCast MRFCm, int nameM, int urlm, int videoViewID, int textId){
        vv=null;
        this.context = con;
        this.videoUrl = urlm;
        this.videoViewID = videoViewID;
        this.MRFC =MRFCm;
        this.name = nameM;

        textView = (TextView) context.findViewById(textId);
        textView.setText(name);
//        setVisibility(false);


        OnTouchListener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
//                    Toast.makeText(context,"yeah", Toast.LENGTH_SHORT).show();
            try {
                MediaMetadata mediaMetadata = new MediaMetadata( MediaMetadata.MEDIA_TYPE_MOVIE );
                mediaMetadata.putString( MediaMetadata.KEY_TITLE, context.getString(name) );

                MediaInfo mediaInfo = new MediaInfo.Builder( context.getString(videoUrl))
                        .setContentType("@string/content_type_mp4")
                        .setStreamType( MediaInfo.STREAM_TYPE_BUFFERED )
                        .setMetadata( mediaMetadata )
                        .build();

                    MRFC.mRemoteMediaPlayer.load( MRFC.mApiClient, mediaInfo, true )
                            .setResultCallback( new ResultCallback<RemoteMediaPlayer.MediaChannelResult>() {
                                @Override
                                public void onResult( RemoteMediaPlayer.MediaChannelResult mediaChannelResult ) {
                                    if( mediaChannelResult.getStatus().isSuccess() ) {
//                                context.mVideoIsLoaded = true;
                                    }
                                }
                            } );
                } catch( Exception e ) {
                }
//                Toast.makeText(context,context.getString(name), Toast.LENGTH_SHORT).show();
                context.allVideoTextToDefault();
                textView.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                return true;
            }
        };

        try {
            Uri url = Uri.parse(context.getString(videoUrl));
            vv = (VideoView) context.findViewById(videoViewID);
            vv.setVideoURI(url);

            vv.setOnTouchListener(OnTouchListener);

            vv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mediaPlayer.setVolume(0,0);
                    mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
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

        }catch (Exception e){Log.d("ChannelPlayer: Run: ",e+"");}
    }


    public void startVideo(){
        vv.resume();
    }

    public void stopVideo(){
        vv.stopPlayback();
    }

    public void destruct(){
        OnTouchListener = null;
//        vv.suspend();
//        this.destruct();
    }

    public void setTextViewColorToDefault(){
        textView.setTextColor(context.getResources().getColor(R.color.black));
    }

    public void setVisibility(boolean visibility){
        if(visibility){
            startVideo();
            textView.setVisibility(TextView.VISIBLE);
            vv.setVisibility(VideoView.VISIBLE);
            isVisible=true;
        }else {
            isVisible=false;
            stopVideo();
            textView.setVisibility(TextView.INVISIBLE);
            vv.setVisibility(VideoView.INVISIBLE);
        }
    }
}
