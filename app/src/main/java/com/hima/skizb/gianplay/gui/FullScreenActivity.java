package com.hima.skizb.gianplay.gui;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.hima.skizb.gianplay.R;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;

public class FullScreenActivity extends AppCompatActivity {
    private VideoView vv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen);
//        vv = (VideoView) findViewById(R.id.FullScreenVideoView);
        Bundle args = getIntent().getExtras();
        int videoUrl =args.getInt("link");
        Uri url = Uri.parse(getString(videoUrl));
        vv = (VideoView) findViewById(R.id.FullScreenVideoView);
        vv.setVideoURI(url);
        stretchScreenToEdges();
        vv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public void onPrepared(final MediaPlayer mediaPlayer) {
//                mp=mediaPlayer;
                mediaPlayer.setVolume(10,10);
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

        MediaController mc = new MediaController(this);
        mc.setAnchorView(vv);
        vv.setMediaController(mc);
    }
//    makes video to fit to screen
    private void stretchScreenToEdges(){
        DisplayMetrics metrics = new DisplayMetrics(); getWindowManager().getDefaultDisplay().getMetrics(metrics);
        android.widget.LinearLayout.LayoutParams params = (android.widget.LinearLayout.LayoutParams) vv.getLayoutParams();
        params.width =  metrics.widthPixels;
        params.height = metrics.heightPixels;
        params.leftMargin = 0;
//        params.rightMargin=10;
//        Toast.makeText(this,params.isMarginRelative()+"",Toast.LENGTH_SHORT).show();
        vv.setLayoutParams(params);
    }

    @Override
    public void onBackPressed() {
        this.finish();
    }
}


