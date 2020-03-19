package com.hima.skizb.gianplay;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hima.skizb.gianplay.gui.BottomPanel;
import com.hima.skizb.gianplay.gui.MIddlePanel;
import com.hima.skizb.gianplay.gui.TopPanel;
import com.hima.skizb.gianplay.logic.CSVReader;
import com.hima.skizb.gianplay.logic.ChannelPlayer;
import com.hima.skizb.gianplay.logic.GianPlayServer;
import com.hima.skizb.gianplay.logic.MediaRouterForCast;
import com.hima.skizb.gianplay.logic.pcSocketClient;
import com.hima.skizb.gianplay.logic.update;

import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    public int sectionNumOfPages = 2;
    public MIddlePanel mp;
    public BottomPanel bp;
    public TopPanel tp;
    private int vID = R.id.video1-1, tvID= R.id.textView1-1, cID =  R.id.castbut1-1; // always subtract 1 from original values
    public Map<String, String> allChannels;
    private Set<String> channelSet;
    public int s8ch =0;
    private GianPlayServer gps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CSVReader csvReader = new CSVReader();
        InputStream is = getResources().openRawResource(R.raw.channels);
        allChannels = csvReader.getContent(is);
//        channelSet = listToSet(sortPairs(allChannels.keySet()));
//        Log.e(TAG, channelSet.size()+"");

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

    private List<String> sortPairs(Set<String> keySet) {
        List<String> sl = new LinkedList<String>(keySet);
        Collections.sort(sl);
        return sl;
    }

    private Set<String> listToSet(List<String> l){
        return new HashSet<String>(l);
    }
    private void set_container_visibilities() {
        ConstraintLayout gianPlaymainConstante = (ConstraintLayout) findViewById(R.id.gianPlayMain);
        ConstraintLayout gianPlayServerConstante = (ConstraintLayout) findViewById(R.id.gianPlayServer);
        gianPlaymainConstante.setVisibility(View.VISIBLE);
        gianPlayServerConstante.setVisibility(View.GONE);
    }
    public void getChannels(int section, int subSet){
        Log.e("Main", "getting channels");
        set_container_visibilities();
        if(section==1){
            mp.makeNumOfPagesVisible(2);
            if(subSet==1){
                channVid = new HashMap<Integer, ChannelPlayer>();
                channVid.put(1, new ChannelPlayer(this, mrfc, R.string.h1_button, R.string.h1, R.id.video1, R.id.textView1, R.id.castbut1));
                channVid.put(2, new ChannelPlayer(this, mrfc, R.string.h2_button, R.string.h2, R.id.video2, R.id.textView2,R.id.castbut2));
                channVid.put(3, new ChannelPlayer(this, mrfc, R.string.h3_button, R.string.h3, R.id.video3, R.id.textView3, R.id.castbut3));
                channVid.put(4, new ChannelPlayer(this, mrfc, R.string.h4_button,R.string.h4, R.id.video4, R.id.textView4, R.id.castbut4));
                channVid.put(5, new ChannelPlayer(this, mrfc, R.string.h5_button, R.string.h5, R.id.video5, R.id.textView5, R.id.castbut5));
                channVid.put(6, new ChannelPlayer(this, mrfc, R.string.h6_button, R.string.h6, R.id.video6, R.id.textView6,R.id.castbut6));
                channVid.put(7, new ChannelPlayer(this, mrfc, R.string.h7_button, R.string.h7, R.id.video7, R.id.textView7, R.id.castbut7));
                channVid.put(8, new ChannelPlayer(this, mrfc, R.string.h8_button,R.string.h8, R.id.video8, R.id.textView8, R.id.castbut8));
                mp.setAllVisible();
            }else if(subSet==2){
                mp.setVisibilityFalse(channVid.get(4), true);
                mp.setVisibilityFalse(channVid.get(5), true);
                mp.setVisibilityFalse(channVid.get(6), true);
                mp.setVisibilityFalse(channVid.get(7), true);
                mp.setVisibilityFalse(channVid.get(8), true);
                channVid = new HashMap<Integer, ChannelPlayer>();
                channVid.put(1, new ChannelPlayer(this, mrfc, R.string.h9_button, R.string.h9, R.id.video1, R.id.textView1, R.id.castbut1));
                channVid.put(2, new ChannelPlayer(this, mrfc, R.string.h10_button, R.string.h10, R.id.video2, R.id.textView2, R.id.castbut2));
                channVid.put(3, new ChannelPlayer(this, mrfc, R.string.h11_button, R.string.h11, R.id.video3, R.id.textView3, R.id.castbut3));
//                channVid.put(4, new ChannelPlayer(this, mrfc, "server", "http://10.0.0.138:7645/GIO/server/media_lib/videos/Futurama/S01/Futurama.S02E01.A.Flight.to.Remember.720p.HULU.WEB-DL.x265-HETeam.mkv", R.id.video4, R.id.textView4, R.id.castbut4));

            }
        }else if(section==2){
            mp.makeNumOfPagesVisible(1);
            if (subSet==1){
                channVid = new HashMap<Integer, ChannelPlayer>();
                channVid.put(1, new ChannelPlayer(this, mrfc, R.string.kino1_button, R.string.kino1, R.id.video1, R.id.textView1, R.id.castbut1));
                channVid.put(2, new ChannelPlayer(this, mrfc, R.string.kino2_button, R.string.kino2, R.id.video2, R.id.textView2, R.id.castbut2));
                channVid.put(3, new ChannelPlayer(this, mrfc, R.string.kino3_button, R.string.kino3, R.id.video3, R.id.textView3, R.id.castbut3));
                channVid.put(4, new ChannelPlayer(this, mrfc, R.string.kino4_button,R.string.kino4, R.id.video4, R.id.textView4, R.id.castbut4));
                channVid.put(5, new ChannelPlayer(this, mrfc, R.string.kino5_button, R.string.kino5, R.id.video5, R.id.textView5, R.id.castbut5));
                channVid.put(6, new ChannelPlayer(this, mrfc, R.string.kino6_button, R.string.kino6, R.id.video6, R.id.textView6, R.id.castbut6));
                channVid.put(7, new ChannelPlayer(this, mrfc, R.string.kino7_button, R.string.kino7, R.id.video7, R.id.textView7, R.id.castbut7));
                channVid.put(8, new ChannelPlayer(this, mrfc, R.string.r1_button,R.string.r1, R.id.video8, R.id.textView8, R.id.castbut8));
                mp.setAllVisible();
            }
        }else if(section==3){
            mp.makeNumOfPagesVisible(1);
            if(subSet==1){
//                mp.setVisibilityFalse(channVid.get(4), true);
                mp.setVisibilityFalse(channVid.get(5), true);
                mp.setVisibilityFalse(channVid.get(6), true);
                mp.setVisibilityFalse(channVid.get(7), true);
                mp.setVisibilityFalse(channVid.get(8), true);
                channVid = new HashMap<Integer, ChannelPlayer>();
                channVid.put(1, new ChannelPlayer(this, mrfc, R.string.news1_button, R.string.news1, R.id.video1, R.id.textView1, R.id.castbut1));
                channVid.put(2, new ChannelPlayer(this, mrfc, R.string.news2_button, R.string.news2, R.id.video2, R.id.textView2, R.id.castbut2));
                channVid.put(3, new ChannelPlayer(this, mrfc, R.string.news3_button, R.string.news3, R.id.video3, R.id.textView3, R.id.castbut3));
                channVid.put(4, new ChannelPlayer(this, mrfc, R.string.news4_button, R.string.news4, R.id.video4, R.id.textView4, R.id.castbut4));
                mp.setAllVisible();
            }
        }else if(section==4){
            mp.makeNumOfPagesVisible(1);
            if(subSet==1){
//                mp.setVisibilityFalse(channVid.get(4), true);
                mp.setVisibilityFalse(channVid.get(5), true);
                mp.setVisibilityFalse(channVid.get(6), true);
                mp.setVisibilityFalse(channVid.get(7), true);
                mp.setVisibilityFalse(channVid.get(8), true);
                channVid = new HashMap<Integer, ChannelPlayer>();
                channVid.put(1, new ChannelPlayer(this, mrfc, R.string.music1_button, R.string.music1, R.id.video1, R.id.textView1, R.id.castbut1));
                channVid.put(2, new ChannelPlayer(this, mrfc, R.string.music2_button, R.string.music2, R.id.video2, R.id.textView2, R.id.castbut2));
                channVid.put(3, new ChannelPlayer(this, mrfc, R.string.music3_button, R.string.music3, R.id.video3, R.id.textView3, R.id.castbut3));
                channVid.put(4, new ChannelPlayer(this, mrfc, R.string.music4_button,R.string.music4, R.id.video4, R.id.textView4, R.id.castbut4));
                mp.setAllVisible();

            }
        }else if(section==5){
            mp.makeNumOfPagesVisible(1);
            if (subSet==1){
//                mp.setVisibilityFalse(channVid.get(4), true);
                mp.setVisibilityFalse(channVid.get(5), true);
                mp.setVisibilityFalse(channVid.get(6), true);
                mp.setVisibilityFalse(channVid.get(7), true);
                mp.setVisibilityFalse(channVid.get(8), true);
                channVid = new HashMap<Integer, ChannelPlayer>();
                channVid.put(1, new ChannelPlayer(this, mrfc, R.string.science1_button, R.string.science1, R.id.video1, R.id.textView1, R.id.castbut1));
                channVid.put(2, new ChannelPlayer(this, mrfc, R.string.science2_button, R.string.science2, R.id.video2, R.id.textView2, R.id.castbut2));
                channVid.put(3, new ChannelPlayer(this, mrfc, R.string.science3_button, R.string.science3, R.id.video3, R.id.textView3, R.id.castbut3));
                channVid.put(4, new ChannelPlayer(this, mrfc, R.string.science4_button, R.string.science4, R.id.video4, R.id.textView4, R.id.castbut4));
                mp.setAllVisible();
            }
        }else if(section==6){
            mp.makeNumOfPagesVisible(1);
            if(subSet==1) {
//                mp.setVisibilityFalse(channVid.get(5), true);
//                mp.setVisibilityFalse(channVid.get(6), true);
//                mp.setVisibilityFalse(channVid.get(7), true);
                mp.setVisibilityFalse(channVid.get(8), true);
                channVid = new HashMap<Integer, ChannelPlayer>();
                channVid.put(1, new ChannelPlayer(this, mrfc, R.string.sport1_button, R.string.sport1, R.id.video1, R.id.textView1, R.id.castbut1));
                channVid.put(2, new ChannelPlayer(this, mrfc, R.string.sport2_button, R.string.sport2, R.id.video2, R.id.textView2, R.id.castbut2));
                channVid.put(3, new ChannelPlayer(this, mrfc, R.string.sport3_button, R.string.sport3, R.id.video3, R.id.textView3, R.id.castbut3));
                channVid.put(4, new ChannelPlayer(this, mrfc, R.string.sport4_button, R.string.sport4, R.id.video4, R.id.textView4, R.id.castbut4));
                channVid.put(5, new ChannelPlayer(this, mrfc, R.string.sport5_button, R.string.sport5, R.id.video5, R.id.textView5, R.id.castbut5));
                channVid.put(6, new ChannelPlayer(this, mrfc, R.string.sport6_button, R.string.sport6, R.id.video6, R.id.textView6, R.id.castbut6));
                channVid.put(7, new ChannelPlayer(this, mrfc, R.string.sport7_button, R.string.sport7, R.id.video7, R.id.textView7, R.id.castbut7));
                mp.setAllVisible();
            }
        }else if(section==7) {
            Log.e(TAG, "V\t TV\t CB\t\n");
            String s = R.id.video1+"\t"+R.id.textView1+"\t"+R.id.castbut1+"\n"+
                    R.id.video2+"\t"+R.id.textView2+"\t"+R.id.castbut2+"\n"+
                    R.id.video3+"\t"+R.id.textView3+"\t"+R.id.castbut3+"\n"+
                    R.id.video4+"\t"+R.id.textView4+"\t"+R.id.castbut4+"\n"+
                    R.id.video5+"\t"+R.id.textView5+"\t"+R.id.castbut5+"\n"+
                    R.id.video6+"\t"+R.id.textView6+"\t"+R.id.castbut6+"\n"+
                    R.id.video7+"\t"+R.id.textView7+"\t"+R.id.castbut7+"\n"+
                    R.id.video8+"\t"+R.id.textView8+"\t"+R.id.castbut8+"\n";
            Log.e(TAG, s);
            mp.makeNumOfPagesVisible(2);
            if (subSet == 1) {
                channVid = new HashMap<Integer, ChannelPlayer>();
                channVid.put(1, new ChannelPlayer(this, mrfc, R.string.us1_button, R.string.us1, R.id.video1, R.id.textView1, R.id.castbut1));
                channVid.put(2, new ChannelPlayer(this, mrfc, R.string.us2_button, R.string.us2, R.id.video2, R.id.textView2, R.id.castbut2));
                channVid.put(3, new ChannelPlayer(this, mrfc, R.string.us3_button, R.string.us3, R.id.video3, R.id.textView3, R.id.castbut3));
                channVid.put(4, new ChannelPlayer(this, mrfc, R.string.us4_button, R.string.us4, R.id.video4, R.id.textView4, R.id.castbut4));
                channVid.put(5, new ChannelPlayer(this, mrfc, R.string.us5_button, R.string.us5, R.id.video5, R.id.textView5, R.id.castbut5));
                channVid.put(6, new ChannelPlayer(this, mrfc, R.string.us6_button, R.string.us6, R.id.video6, R.id.textView6, R.id.castbut6));
                channVid.put(7, new ChannelPlayer(this, mrfc, R.string.us7_button, R.string.us7, R.id.video7, R.id.textView7, R.id.castbut7));
                channVid.put(8, new ChannelPlayer(this, mrfc, R.string.us8_button, R.string.us8, R.id.video8, R.id.textView8, R.id.castbut8));
                mp.setAllVisible();
            } else if (subSet == 2) {
                mp.setVisibilityFalse(channVid.get(4), true);
                mp.setVisibilityFalse(channVid.get(5), true);
                mp.setVisibilityFalse(channVid.get(6), true);
                mp.setVisibilityFalse(channVid.get(7), true);
                mp.setVisibilityFalse(channVid.get(8), true);
                channVid = new HashMap<Integer, ChannelPlayer>();
                channVid.put(1, new ChannelPlayer(this, mrfc, R.string.us9_button, R.string.us9, R.id.video1, R.id.textView1, R.id.castbut1));
                channVid.put(2, new ChannelPlayer(this, mrfc, R.string.us10_button, R.string.us10, R.id.video2, R.id.textView2, R.id.castbut2));
                channVid.put(3, new ChannelPlayer(this, mrfc, R.string.us11_button, R.string.us11, R.id.video3, R.id.textView3, R.id.castbut3));
//                    channVid.put(4, new ChannelPlayer(this, mrfc, R.string.ru24_button, R.string.ru24, R.id.video4, R.id.textView4));
                mp.setAllVisible();
            }
        }
        else if(section==8) {
//            mp.makeNumOfPagesVisible(0);
            if (subSet == 1) {
                List<String> channNum = sortPairs(allChannels.keySet());
                int chOrder = s8ch*8;
                channVid = new HashMap<Integer, ChannelPlayer>();
                for (int i =1; i<=8; i++){
//                    Log.e("Main s8 for", (tvID+i)+"");
                    channVid.put(i, new ChannelPlayer(this, mrfc,channNum.get(chOrder+i), allChannels.get(channNum.get(chOrder+i)), vID+i, tvID+i, cID+i));
                }
                mp.setAllVisible();
//                Log.e(TAG, "V\t TV\t CB\t\n");
//                String s = R.id.video1+"\t"+R.id.textView1+"\t"+R.id.castbut1+"\n"+
//                            R.id.video1+"\t"+R.id.textView1+"\t"+R.id.castbut1+"\n"+
//                            R.id.video1+"\t"+R.id.textView1+"\t"+R.id.castbut1+"\n"+
//                            R.id.video1+"\t"+R.id.textView1+"\t"+R.id.castbut1+"\n"+
//                            R.id.video1+"\t"+R.id.textView1+"\t"+R.id.castbut1+"\n"+
//                            R.id.video1+"\t"+R.id.textView1+"\t"+R.id.castbut1+"\n"+
//                            R.id.video1+"\t"+R.id.textView1+"\t"+R.id.castbut1+"\n"+
//                            R.id.video1+"\t"+R.id.textView1+"\t"+R.id.castbut1+"\n";
//                Log.e(TAG, s);

            }
        }else if(section==9){
            mp.setVisibilityFalse(channVid.get(1), true);
            mp.setVisibilityFalse(channVid.get(2), true);
            mp.setVisibilityFalse(channVid.get(3), true);
            mp.setVisibilityFalse(channVid.get(4), true);
            mp.setVisibilityFalse(channVid.get(5), true);
            mp.setVisibilityFalse(channVid.get(6), true);
            mp.setVisibilityFalse(channVid.get(7), true);
            mp.setVisibilityFalse(channVid.get(8), true);
            if (gps != null) gps.fillUIWithItems();
            else gps = new GianPlayServer(this, mrfc);
        }
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
                if (section==9) {
                    tp.setupGianPC(gps);
                }
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
