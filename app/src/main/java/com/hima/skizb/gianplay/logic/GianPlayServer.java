package com.hima.skizb.gianplay.logic;

import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.cast.MediaStatus;
import com.google.android.gms.cast.RemoteMediaPlayer;
import com.google.android.gms.common.api.ResultCallback;
import com.hima.skizb.gianplay.R;

import com.hima.skizb.gianplay.MainActivity;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static com.google.android.gms.internal.zzahf.runOnUiThread;

public class GianPlayServer {
    private final MainActivity context;
    private final MediaRouterForCast MRFC;
    private String serverAddress = "http://10.0.0.34:7645";
    private final String requestAddress = "/LIO/";
    private SeekBar seekBar;
//    private String lastAddress = "";

    public GianPlayServer(MainActivity mainActivity, MediaRouterForCast mrfc) {
        this.context = mainActivity;
        this.MRFC = mrfc;
        fillUIWithItems();
    }


    public void set_server_address(String address){
        serverAddress = "http://"+address;
    }

    public void fillUIWithItems(){
        //LinearLayout folderView = (LinearLayout) this.context.findViewById(R.id.folderView);
        //LinearLayout fileView = (LinearLayout) this.context.findViewById(R.id.fileView);
//        Log.e("FinalResponse", getfromURL(this.serverAddress+this.requestAddress));
        set_container_visibilities();
        make_folders_from_server(this.serverAddress+this.requestAddress+"1");
        seekBar = (SeekBar) context.findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
//                MRFC.mRemoteMediaPlayer.getStreamDuration();
                if(b) {
                    seek(i);
                    seekBar.setProgress(i);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void set_container_visibilities() {
        ConstraintLayout gianPlaymainConstante = (ConstraintLayout) this.context.findViewById(R.id.gianPlayMain);
        ConstraintLayout gianPlayServerConstante = (ConstraintLayout) this.context.findViewById(R.id.gianPlayServer);
        gianPlaymainConstante.setVisibility(View.GONE);
        gianPlayServerConstante.setVisibility(View.VISIBLE);
    }

    private void make_folders_from_server(final String urlstring){
//        final String[] response = {null};
        final GianPlayServer self = this;
        new Thread(new Runnable() {
            @Override
            public void run() {
                URL url = null;
                try {
                    Log.e("URL", urlstring);
                    url = new URL(urlstring);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                HttpURLConnection conn = null;
                int responsecode = 0;
                try {
                    conn = (HttpURLConnection)url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.connect();
                    responsecode = conn.getResponseCode();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if(responsecode != 200) {
                    Log.e("HttpResponseCode ", responsecode+"");
                }else
                {
                    Scanner sc = null;
                    try {
                        sc = new Scanner(url.openStream());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    String inline="";
                    while(sc.hasNext())
                    {
                        inline+=sc.nextLine();
                        Log.e("Server While", inline);
                    }
//            Log.e("Server","\nJSON data in string format");
//            Log.e("Server",inline);
                    if (inline.length()>0) {
//                        self.lastAddress = urlstring;
                        self.displayFolders(inline);
                    }
                    sc.close();
                }
            }
        }).start();
    }

    private void displayFolders(String response) {
        Log.e("Server Response", response);
        final List<List<String>> content = convert_response_to_list(response);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                populateViewsWithContent(content);
            }
        });
//        Log.e("Cleans", content.toString());

    }

    private void populateViewsWithContent(List<List<String>> content) {
        final GianPlayServer self = this;
        LinearLayout folderView = (LinearLayout) this.context.findViewById(R.id.serverFileBrowser);
        folderView.removeAllViews();
        for (final List<String> item : content){
            Button but = new Button(this.context);
            but.setText(item.get(1));
            but.setBackgroundColor(Color.WHITE);
            set_listener_to_button(but, item.get(0), item.get(1));
            folderView.addView(but);
        }

    }

    private void set_listener_to_button(Button but, final String link,final String item) {
        final GianPlayServer self = this;
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                final String temp_lastAddress = lastAddress;
                String format = "";
                if (item.length()>3) {
                    format = item.substring(item.length() - 3);
//                Log.e("GPS", format);
                }
//                    Log.e("GPS", formats[formats.length-1]);
                if(format.equals("mp4")){
                    Log.e("GPS", "Found Video");
                    castTheLink(self.serverAddress+self.requestAddress+link, item);

                } else {
//                    lastAddress = lastAddress+'/'+item;
                    make_folders_from_server(self.serverAddress+self.requestAddress+link);
                }

                Button backBut = (Button) self.context.findViewById(R.id.backbutton);
                backBut.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        make_folders_from_server(temp_lastAddress);
                    }
                });
            }
        });
    }

//    private void update_seekBar(){
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                MediaStatus mediaStatus = MRFC.mRemoteMediaPlayer.getMediaStatus();
//                boolean mIsPlaying = mediaStatus.getPlayerState() == MediaStatus.PLAYER_STATE_PLAYING;
//                while (mIsPlaying){
//                    int prog = (int) ((100.0 * MRFC.mRemoteMediaPlayer.getApproximateStreamPosition()) / MRFC.mRemoteMediaPlayer.getStreamDuration());
//                    seekBar.setProgress(prog);
//                    Log.e("PROG", prog+"");
//                    mIsPlaying = mediaStatus.getPlayerState() == MediaStatus.PLAYER_STATE_PLAYING;
//                }
//            }
//        }).start();
//    }

    private void castTheLink(final String videoUrl, final String name) {
//        update_seekBar();
        seekBar.setProgress(0);
//        GianPlayServer self = this;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    MediaMetadata mediaMetadata = new MediaMetadata(MediaMetadata.MEDIA_TYPE_MOVIE);
                    mediaMetadata.putString(MediaMetadata.KEY_TITLE, name);

                    MediaInfo mediaInfo = new MediaInfo.Builder(videoUrl)
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
//                                        update_seekBar();
                                    }
                                }
                            });

                } catch (Exception e) {
                }
//                Toast.makeText(context,context.getString(name), Toast.LENGTH_SHORT).show();
            }
        }).start();
    }

    private void seek(final int progress){
        long newTime = 0;
        try {
            newTime = (long) ((progress / 100.0) * MRFC.mRemoteMediaPlayer.getStreamDuration());
            Log.e("SEKBAR", newTime + "");
        }catch (Exception e){
            Log.e("up_seekBar", e.toString());
        }

        final long finalNewTime = newTime;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    MRFC.mRemoteMediaPlayer.seek(MRFC.mApiClient, finalNewTime,  RemoteMediaPlayer.RESUME_STATE_UNCHANGED).
                            setResultCallback(new ResultCallback<RemoteMediaPlayer.MediaChannelResult>() {

                                @Override
                                public void onResult(RemoteMediaPlayer.MediaChannelResult result) {
                                    if (!result.getStatus().isSuccess()) {
//                                        onFailed(R.string.ccl_failed_seek, result.getStatus().getStatusCode());
                                    }
                                }

                            });;
                }catch (Exception e){
                    Log.e("SEEK", e.toString());
                }

            }
        }).start();
    }

    private List<List<String>> convert_response_to_list(String response) {
//        Log.e("GPS", response);
        String[] removabels = {"[", "]", "\""};
        for (String toRemove : removabels){
            response = response.replace(toRemove, "");
        }

        List<String> l = Arrays.asList(response.split(","));
        List<List<String>> clean = new ArrayList<List<String>>();
        Log.e("GPA", l.toString());
        for (int i=2; i<l.size(); i++ ){
            List<String> pair = new ArrayList<String>();
            pair.add(l.get(i));
            if (l.size()-3>i) {
                pair.add(l.get(i + 3));
            }
            i+=3;
            clean.add(pair);
        }
        Log.e("GPA_clean", clean.toString());
        return clean;
    }
}
