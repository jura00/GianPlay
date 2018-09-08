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
                channVid.put(2, new ChannelPlayer(this, mrfc, R.string.armenia_button, R.string.armenia, R.id.video2, R.id.textView2,R.id.castbut2));
                channVid.put(3, new ChannelPlayer(this, mrfc, R.string.atv_button, R.string.atv, R.id.video3, R.id.textView3, R.id.castbut3));
                channVid.put(4, new ChannelPlayer(this, mrfc, R.string.armnews_button ,R.string.armnews, R.id.video4, R.id.textView4, R.id.castbut4));
                mp.setAllVisible();
            }else if(subSet==2){
                channVid = new HashMap<Integer, ChannelPlayer>();
                channVid.put(1, new ChannelPlayer(this, mrfc, R.string.h2_button, R.string.h2, R.id.video1, R.id.textView1, R.id.castbut1));
                channVid.put(2, new ChannelPlayer(this, mrfc, R.string.h3_button, R.string.h3, R.id.video2, R.id.textView2,R.id.castbut2));
                channVid.put(3, new ChannelPlayer(this, mrfc, R.string.kentron_button, R.string.kentron, R.id.video3, R.id.textView3, R.id.castbut3));
                channVid.put(4, new ChannelPlayer(this, mrfc, R.string.erkir_button ,R.string.erkir, R.id.video4, R.id.textView4, R.id.castbut4));
                mp.setAllVisible();
            }else if(subSet==3){
                mp.setVisibilityFalse(channVid.get(4), true);
                channVid = new HashMap<Integer, ChannelPlayer>();
                channVid.put(1, new ChannelPlayer(this, mrfc, R.string.ararat_button, R.string.ararat, R.id.video1, R.id.textView1, R.id.castbut1));
                channVid.put(2, new ChannelPlayer(this, mrfc, R.string.ar_button, R.string.ar, R.id.video2, R.id.textView2, R.id.castbut2));
                channVid.put(3, new ChannelPlayer(this, mrfc, R.string.dar_button, R.string.dar, R.id.video3, R.id.textView3, R.id.castbut3));
                mp.setAllVisible();
            }
        }else if(section==2){
            if (subSet==1){
                channVid = new HashMap<Integer, ChannelPlayer>();
                channVid.put(1, new ChannelPlayer(this, mrfc, R.string.xit_button, R.string.xit, R.id.video1, R.id.textView1, R.id.castbut1));
                channVid.put(2, new ChannelPlayer(this, mrfc, R.string.mix_button, R.string.mix, R.id.video2, R.id.textView2, R.id.castbut2));
                channVid.put(3, new ChannelPlayer(this, mrfc, R.string.komedia_button, R.string.komedia, R.id.video3, R.id.textView3, R.id.castbut3));
                channVid.put(4, new ChannelPlayer(this, mrfc, R.string.men_button ,R.string.men, R.id.video4, R.id.textView4, R.id.castbut4));
                mp.setAllVisible();
            }else if(subSet==2){
                channVid = new HashMap<Integer, ChannelPlayer>();
                channVid.put(1, new ChannelPlayer(this, mrfc, R.string.hazar_button, R.string.hazar, R.id.video1, R.id.textView1, R.id.castbut1));
                channVid.put(2, new ChannelPlayer(this, mrfc, R.string.kinop1_button, R.string.kinop1, R.id.video2, R.id.textView2, R.id.castbut2));
                channVid.put(3, new ChannelPlayer(this, mrfc, R.string.kinop2_button, R.string.kinop2, R.id.video3, R.id.textView3, R.id.castbut3));
                channVid.put(4, new ChannelPlayer(this, mrfc, R.string.tnt_button ,R.string.tnt, R.id.video4, R.id.textView4, R.id.castbut4));
                mp.setAllVisible();
            }
        }else if(section==3){
            if(subSet==1){
                channVid = new HashMap<Integer, ChannelPlayer>();
                channVid.put(1, new ChannelPlayer(this, mrfc, R.string.bbc_button, R.string.bbc, R.id.video1, R.id.textView1, R.id.castbut1));
                channVid.put(2, new ChannelPlayer(this, mrfc, R.string.cnbc_button, R.string.cnbc, R.id.video2, R.id.textView2, R.id.castbut2));
                channVid.put(3, new ChannelPlayer(this, mrfc, R.string.eunews_button, R.string.eunews, R.id.video3, R.id.textView3, R.id.castbut3));
                channVid.put(4, new ChannelPlayer(this, mrfc, R.string.ru24_button, R.string.ru24, R.id.video4, R.id.textView4, R.id.castbut4));
                mp.setAllVisible();
            }
        }else if(section==4){
            if(subSet==1){
                channVid = new HashMap<Integer, ChannelPlayer>();
                channVid.put(1, new ChannelPlayer(this, mrfc, R.string.mtv_button, R.string.mtv, R.id.video1, R.id.textView1, R.id.castbut1));
                channVid.put(2, new ChannelPlayer(this, mrfc, R.string.MTVDance_button, R.string.MTVDance, R.id.video2, R.id.textView2, R.id.castbut2));
                channVid.put(3, new ChannelPlayer(this, mrfc, R.string.BRIDGETV_button, R.string.BRIDGETV, R.id.video3, R.id.textView3, R.id.castbut3));
                channVid.put(4, new ChannelPlayer(this, mrfc, R.string.Mezzo_button ,R.string.Mezzo, R.id.video4, R.id.textView4, R.id.castbut4));
                mp.setAllVisible();

            }
        }else if(section==5){
            if (subSet==1){
                channVid = new HashMap<Integer, ChannelPlayer>();
                channVid.put(1, new ChannelPlayer(this, mrfc, R.string.natgeo_button, R.string.natgeo, R.id.video1, R.id.textView1, R.id.castbut1));
                channVid.put(2, new ChannelPlayer(this, mrfc, R.string.disch_button, R.string.disch, R.id.video2, R.id.textView2, R.id.castbut2));
                channVid.put(3, new ChannelPlayer(this, mrfc, R.string.natgeograph_button, R.string.geographic, R.id.video3, R.id.textView3, R.id.castbut3));
                channVid.put(4, new ChannelPlayer(this, mrfc, R.string.Science_button, R.string.Science, R.id.video4, R.id.textView4, R.id.castbut4));
                mp.setAllVisible();
            }
        }else if(section==6){
            if(subSet==1) {
                channVid = new HashMap<Integer, ChannelPlayer>();
                channVid.put(1, new ChannelPlayer(this, mrfc, R.string.match_button, R.string.match, R.id.video1, R.id.textView1, R.id.castbut1));
                channVid.put(2, new ChannelPlayer(this, mrfc, R.string.match2_button, R.string.match2, R.id.video2, R.id.textView2, R.id.castbut2));
                channVid.put(3, new ChannelPlayer(this, mrfc, R.string.match3_button, R.string.match3, R.id.video3, R.id.textView3, R.id.castbut3));
                channVid.put(4, new ChannelPlayer(this, mrfc, R.string.match4_button, R.string.match4, R.id.video4, R.id.textView4, R.id.castbut4));
                mp.setAllVisible();
            }else if(subSet==2){
                mp.setVisibilityFalse(channVid.get(4), true);
                channVid = new HashMap<Integer, ChannelPlayer>();
                channVid.put(1, new ChannelPlayer(this, mrfc, R.string.eurosport_button, R.string.eurosport, R.id.video1, R.id.textView1, R.id.castbut1));
                channVid.put(2, new ChannelPlayer(this, mrfc, R.string.footy1_button, R.string.footy1, R.id.video2, R.id.textView2, R.id.castbut2));
                channVid.put(3, new ChannelPlayer(this, mrfc, R.string.footy2_button, R.string.footy2, R.id.video3, R.id.textView3, R.id.castbut3));
//                channVid.put(4, new ChannelPlayer(this, mrfc, R.string.match4_button, R.string.match4, R.id.video4, R.id.textView4));
                mp.setAllVisible();
            }
        }else if(section==7) {
            if (subSet == 1) {
                channVid = new HashMap<Integer, ChannelPlayer>();
                channVid.put(1, new ChannelPlayer(this, mrfc, R.string.nbo_button, R.string.nbo, R.id.video1, R.id.textView1, R.id.castbut1));
                channVid.put(2, new ChannelPlayer(this, mrfc, R.string.nbo_signature_button, R.string.nbo_signature, R.id.video2, R.id.textView2, R.id.castbut2));
                channVid.put(3, new ChannelPlayer(this, mrfc, R.string.nbo_comedy_button, R.string.nbo_comedy, R.id.video3, R.id.textView3, R.id.castbut3));
                channVid.put(4, new ChannelPlayer(this, mrfc, R.string.historyUS_button, R.string.historyUS, R.id.video4, R.id.textView4, R.id.castbut4));
                mp.setAllVisible();
            } else if (subSet == 2) {
                channVid = new HashMap<Integer, ChannelPlayer>();
                channVid.put(1, new ChannelPlayer(this, mrfc, R.string.showtime2_button, R.string.showtime2, R.id.video1, R.id.textView1, R.id.castbut1));
                channVid.put(2, new ChannelPlayer(this, mrfc, R.string.starz_button, R.string.starz, R.id.video2, R.id.textView2, R.id.castbut2));
                channVid.put(3, new ChannelPlayer(this, mrfc, R.string.tlc_button, R.string.tlc, R.id.video3, R.id.textView3, R.id.castbut3));
                channVid.put(4, new ChannelPlayer(this, mrfc, R.string.cbs_button, R.string.cbs, R.id.video4, R.id.textView4, R.id.castbut4));
                mp.setAllVisible();
            } else if (subSet == 3) {
                mp.setVisibilityFalse(channVid.get(4), true);
                channVid = new HashMap<Integer, ChannelPlayer>();
                channVid.put(1, new ChannelPlayer(this, mrfc, R.string.showtime_button, R.string.showtime, R.id.video1, R.id.textView1, R.id.castbut1));
                channVid.put(2, new ChannelPlayer(this, mrfc, R.string.amc_button, R.string.amc, R.id.video2, R.id.textView2, R.id.castbut2));
                channVid.put(3, new ChannelPlayer(this, mrfc, R.string.syfy_us_button, R.string.syfy_us, R.id.video3, R.id.textView3, R.id.castbut3));
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
//        mrfc.onPause();
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
