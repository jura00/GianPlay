package com.hima.skizb.gianplay;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hima.skizb.gianplay.gui.BottomPanel;
import com.hima.skizb.gianplay.gui.MIddlePanel;
import com.hima.skizb.gianplay.gui.TopPanel;
import com.hima.skizb.gianplay.logic.ChannelPlayer;
import com.hima.skizb.gianplay.logic.MediaRouterForCast;
import com.hima.skizb.gianplay.logic.pcSocketClient;
import com.hima.skizb.gianplay.logic.update;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private String TAG = "MainActivity";
    private MainActivity me = this;
    public Map<Integer, ChannelPlayer> channVid;

    public int section = 1;
    public int subSet = 1;

    public boolean isVideoStopped = false;
    private Button resumePauseBut;

    MediaRouterForCast mrfc;
    public pcSocketClient pcsc;
    public LinearLayout gianpcPanel;
    public Button connectBut;
    public EditText iptext;
    public List<TextView> pageNums;
    public int sectionNumOfPages = 3;
    public MIddlePanel mp;
    public BottomPanel bp;
    public TopPanel tp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gianpcPanel = (LinearLayout)findViewById(R.id.gian_panel);
        mrfc = new MediaRouterForCast(this);
        mrfc.initMediaRouter();
        mp = new MIddlePanel(this);
        pageNums = mp.makePageNumbers();
        getChannels(section,subSet);


//        startChannels();
        tp = new TopPanel(this);
        tp.topButtonPanleStart();
        tp.buts[0].setTextColor(getResources().getColor(R.color.black));

        pageNums.get(0).setTextColor(getResources().getColor(R.color.colorPrimary));
        bp = new BottomPanel(this);
        bp.bottomButtonPanelStart();
//        resumePauseBut.setTextColor(getResources().getColor(R.color.colorAccent));
//        resumePauseButPressed();
        isVideoStopped = false;
        bp.resumePauseButPressed();

    }

    public void getChannels(int section, int subSet){
        if(section==1){
            if(subSet==1){
                channVid = new HashMap<Integer, ChannelPlayer>();
                channVid.put(1, new ChannelPlayer(this, mrfc, R.string.h1_button, R.string.h1, R.id.video1, R.id.textView1, R.id.castbut1));
                channVid.put(2, new ChannelPlayer(this, mrfc, R.string.h2_button, R.string.h2, R.id.video2, R.id.textView2,R.id.castbut2));
                channVid.put(3, new ChannelPlayer(this, mrfc, R.string.h3_button, R.string.h3, R.id.video3, R.id.textView3, R.id.castbut3));
                channVid.put(4, new ChannelPlayer(this, mrfc, R.string.h4_button,R.string.h4, R.id.video4, R.id.textView4, R.id.castbut4));
                mp.setAllVisible();
            }else if(subSet==2){
                channVid = new HashMap<Integer, ChannelPlayer>();
                channVid.put(1, new ChannelPlayer(this, mrfc, R.string.h5_button, R.string.h5, R.id.video1, R.id.textView1, R.id.castbut1));
                channVid.put(2, new ChannelPlayer(this, mrfc, R.string.h6_button, R.string.h6, R.id.video2, R.id.textView2,R.id.castbut2));
                channVid.put(3, new ChannelPlayer(this, mrfc, R.string.h7_button, R.string.h7, R.id.video3, R.id.textView3, R.id.castbut3));
                channVid.put(4, new ChannelPlayer(this, mrfc, R.string.h8_button,R.string.h8, R.id.video4, R.id.textView4, R.id.castbut4));
                mp.setAllVisible();
            }else if(subSet==3){
                mp.setVisibilityFalse(channVid.get(4), true);
                channVid = new HashMap<Integer, ChannelPlayer>();
                channVid.put(1, new ChannelPlayer(this, mrfc, R.string.h9_button, R.string.h9, R.id.video1, R.id.textView1, R.id.castbut1));
                channVid.put(2, new ChannelPlayer(this, mrfc, R.string.h10_button, R.string.h10, R.id.video2, R.id.textView2, R.id.castbut2));
                channVid.put(3, new ChannelPlayer(this, mrfc, R.string.h11_button, R.string.h11, R.id.video3, R.id.textView3, R.id.castbut3));
                mp.setAllVisible();
            }
        }else if(section==2){
            if (subSet==1){
                channVid = new HashMap<Integer, ChannelPlayer>();
                channVid.put(1, new ChannelPlayer(this, mrfc, R.string.kino1_button, R.string.kino1, R.id.video1, R.id.textView1, R.id.castbut1));
                channVid.put(2, new ChannelPlayer(this, mrfc, R.string.kino2_button, R.string.kino2, R.id.video2, R.id.textView2, R.id.castbut2));
                channVid.put(3, new ChannelPlayer(this, mrfc, R.string.kino3_button, R.string.kino3, R.id.video3, R.id.textView3, R.id.castbut3));
                channVid.put(4, new ChannelPlayer(this, mrfc, R.string.kino4_button,R.string.kino4, R.id.video4, R.id.textView4, R.id.castbut4));
                mp.setAllVisible();
            }else if(subSet==2){
                channVid = new HashMap<Integer, ChannelPlayer>();
                channVid.put(1, new ChannelPlayer(this, mrfc, R.string.kino5_button, R.string.kino5, R.id.video1, R.id.textView1, R.id.castbut1));
                channVid.put(2, new ChannelPlayer(this, mrfc, R.string.kino6_button, R.string.kino6, R.id.video2, R.id.textView2, R.id.castbut2));
                channVid.put(3, new ChannelPlayer(this, mrfc, R.string.kino7_button, R.string.kino7, R.id.video3, R.id.textView3, R.id.castbut3));
                channVid.put(4, new ChannelPlayer(this, mrfc, R.string.r1_button,R.string.r1, R.id.video4, R.id.textView4, R.id.castbut4));
                mp.setAllVisible();
            }
        }else if(section==3){
            if(subSet==1){
                channVid = new HashMap<Integer, ChannelPlayer>();
                channVid.put(1, new ChannelPlayer(this, mrfc, R.string.news1_button, R.string.news1, R.id.video1, R.id.textView1, R.id.castbut1));
                channVid.put(2, new ChannelPlayer(this, mrfc, R.string.news2_button, R.string.news2, R.id.video2, R.id.textView2, R.id.castbut2));
                channVid.put(3, new ChannelPlayer(this, mrfc, R.string.news3_button, R.string.news3, R.id.video3, R.id.textView3, R.id.castbut3));
                channVid.put(4, new ChannelPlayer(this, mrfc, R.string.news4_button, R.string.news4, R.id.video4, R.id.textView4, R.id.castbut4));
                mp.setAllVisible();
            }
        }else if(section==4){
            if(subSet==1){
                channVid = new HashMap<Integer, ChannelPlayer>();
                channVid.put(1, new ChannelPlayer(this, mrfc, R.string.music1_button, R.string.music1, R.id.video1, R.id.textView1, R.id.castbut1));
                channVid.put(2, new ChannelPlayer(this, mrfc, R.string.music2_button, R.string.music2, R.id.video2, R.id.textView2, R.id.castbut2));
                channVid.put(3, new ChannelPlayer(this, mrfc, R.string.music3_button, R.string.music3, R.id.video3, R.id.textView3, R.id.castbut3));
                channVid.put(4, new ChannelPlayer(this, mrfc, R.string.music4_button,R.string.music4, R.id.video4, R.id.textView4, R.id.castbut4));
                mp.setAllVisible();

            }
        }else if(section==5){
            if (subSet==1){
                channVid = new HashMap<Integer, ChannelPlayer>();
                channVid.put(1, new ChannelPlayer(this, mrfc, R.string.science1_button, R.string.science1, R.id.video1, R.id.textView1, R.id.castbut1));
                channVid.put(2, new ChannelPlayer(this, mrfc, R.string.science2_button, R.string.science2, R.id.video2, R.id.textView2, R.id.castbut2));
                channVid.put(3, new ChannelPlayer(this, mrfc, R.string.science3_button, R.string.science3, R.id.video3, R.id.textView3, R.id.castbut3));
                channVid.put(4, new ChannelPlayer(this, mrfc, R.string.science4_button, R.string.science4, R.id.video4, R.id.textView4, R.id.castbut4));
                mp.setAllVisible();
            }
        }else if(section==6){
            if(subSet==1) {
                channVid = new HashMap<Integer, ChannelPlayer>();
                channVid.put(1, new ChannelPlayer(this, mrfc, R.string.sport1_button, R.string.sport1, R.id.video1, R.id.textView1, R.id.castbut1));
                channVid.put(2, new ChannelPlayer(this, mrfc, R.string.sport2_button, R.string.sport2, R.id.video2, R.id.textView2, R.id.castbut2));
                channVid.put(3, new ChannelPlayer(this, mrfc, R.string.sport3_button, R.string.sport3, R.id.video3, R.id.textView3, R.id.castbut3));
                channVid.put(4, new ChannelPlayer(this, mrfc, R.string.sport4_button, R.string.sport4, R.id.video4, R.id.textView4, R.id.castbut4));
                mp.setAllVisible();
            }else if(subSet==2){
                mp.setVisibilityFalse(channVid.get(4), true);
                channVid = new HashMap<Integer, ChannelPlayer>();
                channVid.put(1, new ChannelPlayer(this, mrfc, R.string.sport5_button, R.string.sport5, R.id.video1, R.id.textView1, R.id.castbut1));
                channVid.put(2, new ChannelPlayer(this, mrfc, R.string.sport6_button, R.string.sport6, R.id.video2, R.id.textView2, R.id.castbut2));
                channVid.put(3, new ChannelPlayer(this, mrfc, R.string.sport7_button, R.string.sport7, R.id.video3, R.id.textView3, R.id.castbut3));
//                channVid.put(4, new ChannelPlayer(this, mrfc, R.string.match4_button, R.string.match4, R.id.video4, R.id.textView4));
                mp.setAllVisible();
            }
        }else if(section==7) {
            if (subSet == 1) {
                channVid = new HashMap<Integer, ChannelPlayer>();
                channVid.put(1, new ChannelPlayer(this, mrfc, R.string.us1_button, R.string.us1, R.id.video1, R.id.textView1, R.id.castbut1));
                channVid.put(2, new ChannelPlayer(this, mrfc, R.string.us2_button, R.string.us2, R.id.video2, R.id.textView2, R.id.castbut2));
                channVid.put(3, new ChannelPlayer(this, mrfc, R.string.us3_button, R.string.us3, R.id.video3, R.id.textView3, R.id.castbut3));
                channVid.put(4, new ChannelPlayer(this, mrfc, R.string.us4_button, R.string.us4, R.id.video4, R.id.textView4, R.id.castbut4));
                mp.setAllVisible();
            } else if (subSet == 2) {
                channVid = new HashMap<Integer, ChannelPlayer>();
                channVid.put(1, new ChannelPlayer(this, mrfc, R.string.us5_button, R.string.us5, R.id.video1, R.id.textView1, R.id.castbut1));
                channVid.put(2, new ChannelPlayer(this, mrfc, R.string.us6_button, R.string.us6, R.id.video2, R.id.textView2, R.id.castbut2));
                channVid.put(3, new ChannelPlayer(this, mrfc, R.string.us7_button, R.string.us7, R.id.video3, R.id.textView3, R.id.castbut3));
                channVid.put(4, new ChannelPlayer(this, mrfc, R.string.us8_button, R.string.us8, R.id.video4, R.id.textView4, R.id.castbut4));
                mp.setAllVisible();
            } else if (subSet == 3) {
                mp.setVisibilityFalse(channVid.get(4), true);
                channVid = new HashMap<Integer, ChannelPlayer>();
                channVid.put(1, new ChannelPlayer(this, mrfc, R.string.us9_button, R.string.us9, R.id.video1, R.id.textView1, R.id.castbut1));
                channVid.put(2, new ChannelPlayer(this, mrfc, R.string.us10_button, R.string.us10, R.id.video2, R.id.textView2, R.id.castbut2));
                channVid.put(3, new ChannelPlayer(this, mrfc, R.string.us11_button, R.string.us11, R.id.video3, R.id.textView3, R.id.castbut3));
//                    channVid.put(4, new ChannelPlayer(this, mrfc, R.string.ru24_button, R.string.ru24, R.id.video4, R.id.textView4));
                mp.setAllVisible();
            }
        }
//        else if(section==8) {
//            if (subSet == 1) {
//                channVid = new HashMap<Integer, ChannelPlayer>();
//                channVid.put(1, new ChannelPlayer(this, mrfc, R.string.comedy_central_button, R.string.comedy_central, R.id.video1, R.id.textView1));
//                channVid.put(2, new ChannelPlayer(this, mrfc, R.string.discovery_uk_button, R.string.discovery_uk, R.id.video2, R.id.textView2));
//                channVid.put(3, new ChannelPlayer(this, mrfc, R.string.nbo_comedy_uk_button, R.string.nbo_comedy_uk, R.id.video3, R.id.textView3));
//                channVid.put(4, new ChannelPlayer(this, mrfc, R.string.one_button, R.string.one, R.id.video4, R.id.textView4));
//                setAllVisible();
//            } else if (subSet == 2) {
//                setVisibilityFalse(channVid.get(4));
//                channVid = new HashMap<Integer, ChannelPlayer>();
//                channVid.put(1, new ChannelPlayer(this, mrfc, R.string.rte_two_button, R.string.rte_two, R.id.video1, R.id.textView1));
//                channVid.put(2, new ChannelPlayer(this, mrfc, R.string.syfy_button, R.string.syfy, R.id.video2, R.id.textView2));
//                channVid.put(3, new ChannelPlayer(this, mrfc, R.string.thriller_button, R.string.thriller, R.id.video3, R.id.textView3));
////                channVid.put(4, new ChannelPlayer(this, mrfc, R.string.true_movies_button ,R.string.true_movies, R.id.video4, R.id.textView4));
//                setAllVisible();
//            }
//        }
    }

    public Intent startsUpdateIntent(){
//        startActivity(new Intent(this, update.class));
        return new Intent(this, update.class);

    }


    public pcSocketClient getpcSocket(){
        if(pcsc!=null)return pcsc;
        else return null;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mrfc.onResume();
        isVideoStopped = false;
        bp.resumePauseButPressed();
    }

    @Override
    public void onPause() {
        super.onPause();
        mrfc.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu( Menu menu ) {
        super.onCreateOptionsMenu( menu );
        mrfc.onCreateOptionsMenu(menu);
//        Button update = (Button) findViewById(R.id.update);
        MenuItem updateButton = menu.findItem(R.id.update);
        updateButton.setIntent(startsUpdateIntent());

        MenuItem gianpc = menu.findItem(R.id.gianpc);
        gianpc.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                tp.setupGianPC();
                return false;
            }
        });
        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mrfc.onDestroy();
    }
}
