package com.hima.skizb.gianplay;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private String TAG = "MainActivity";
    private MainActivity me = this;
    private Map<Integer, ChannelPlayer> channVid;

    private int section = 1;
    private int subSet = 1;

    public boolean isVideoStopped = false;

    private Button one;
    private Button two;
    private Button three;
    private Button four;
    private Button five;
    private Button six;
    private Button seven;
    private Button eight;

    private Button resumePauseBut;

    MediaRouterForCast mrfc;
    private pcSocketClient pcsc;
    private LinearLayout gianpcPanel;
    private Button connectBut;
    private EditText iptext;
    private List<TextView> pageNums;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gianpcPanel = (LinearLayout)findViewById(R.id.gian_panel);
        mrfc = new MediaRouterForCast(this);
        mrfc.initMediaRouter();
        makePageNumbers();
        getChannels(section,subSet);


//        startChannels();
        topButtonPanleStart();
        one.setTextColor(getResources().getColor(R.color.black));
        pageNums.get(0).setTextColor(getResources().getColor(R.color.colorPrimary));
        bottomButtonPanelStart();
//        resumePauseBut.setTextColor(getResources().getColor(R.color.colorAccent));
//        resumePauseButPressed();
        isVideoStopped = false;
        resumePauseButPressed();

    }

    private void makePageNumbers(){
        pageNums = new ArrayList<TextView>();
        pageNums.add((TextView)findViewById(R.id.sctextView1));
        pageNums.add((TextView)findViewById(R.id.sctextView2));
        pageNums.add((TextView)findViewById(R.id.sctextView3));
        pageNums.add((TextView)findViewById(R.id.sctextView4));
    }

    public void currentChannelsToDefault(){getChannels(section,subSet);}

    private void getChannels(int section, int subSet){
        if(section==1){
            if(subSet==1){
                channVid = new HashMap<Integer, ChannelPlayer>();
                channVid.put(1, new ChannelPlayer(this, mrfc, R.string.h1_button, R.string.h1, R.id.video1, R.id.textView1, R.id.castbut1));
                channVid.put(2, new ChannelPlayer(this, mrfc, R.string.armenia_button, R.string.armenia, R.id.video2, R.id.textView2,R.id.castbut2));
                channVid.put(3, new ChannelPlayer(this, mrfc, R.string.atv_button, R.string.atv, R.id.video3, R.id.textView3, R.id.castbut3));
                channVid.put(4, new ChannelPlayer(this, mrfc, R.string.armnews_button ,R.string.armnews, R.id.video4, R.id.textView4, R.id.castbut4));
                setAllVisible();
            }else if(subSet==2){
                channVid = new HashMap<Integer, ChannelPlayer>();
                channVid.put(1, new ChannelPlayer(this, mrfc, R.string.h2_button, R.string.h2, R.id.video1, R.id.textView1, R.id.castbut1));
                channVid.put(2, new ChannelPlayer(this, mrfc, R.string.h3_button, R.string.h3, R.id.video2, R.id.textView2,R.id.castbut2));
                channVid.put(3, new ChannelPlayer(this, mrfc, R.string.kentron_button, R.string.kentron, R.id.video3, R.id.textView3, R.id.castbut3));
                channVid.put(4, new ChannelPlayer(this, mrfc, R.string.erkir_button ,R.string.erkir, R.id.video4, R.id.textView4, R.id.castbut4));
                setAllVisible();
            }else if(subSet==3){
                setVisibilityFalse(channVid.get(4), true);
                channVid = new HashMap<Integer, ChannelPlayer>();
                channVid.put(1, new ChannelPlayer(this, mrfc, R.string.ararat_button, R.string.ararat, R.id.video1, R.id.textView1, R.id.castbut1));
                channVid.put(2, new ChannelPlayer(this, mrfc, R.string.ar_button, R.string.ar, R.id.video2, R.id.textView2, R.id.castbut2));
                channVid.put(3, new ChannelPlayer(this, mrfc, R.string.dar_button, R.string.dar, R.id.video3, R.id.textView3, R.id.castbut3));
                setAllVisible();
            }
        }else if(section==2){
            if (subSet==1){
                channVid = new HashMap<Integer, ChannelPlayer>();
                channVid.put(1, new ChannelPlayer(this, mrfc, R.string.xit_button, R.string.xit, R.id.video1, R.id.textView1, R.id.castbut1));
                channVid.put(2, new ChannelPlayer(this, mrfc, R.string.mix_button, R.string.mix, R.id.video2, R.id.textView2, R.id.castbut2));
                channVid.put(3, new ChannelPlayer(this, mrfc, R.string.komedia_button, R.string.komedia, R.id.video3, R.id.textView3, R.id.castbut3));
                channVid.put(4, new ChannelPlayer(this, mrfc, R.string.men_button ,R.string.men, R.id.video4, R.id.textView4, R.id.castbut4));
                setAllVisible();
            }else if(subSet==2){
                channVid = new HashMap<Integer, ChannelPlayer>();
                channVid.put(1, new ChannelPlayer(this, mrfc, R.string.hazar_button, R.string.hazar, R.id.video1, R.id.textView1, R.id.castbut1));
                channVid.put(2, new ChannelPlayer(this, mrfc, R.string.kinop1_button, R.string.kinop1, R.id.video2, R.id.textView2, R.id.castbut2));
                channVid.put(3, new ChannelPlayer(this, mrfc, R.string.kinop2_button, R.string.kinop2, R.id.video3, R.id.textView3, R.id.castbut3));
                channVid.put(4, new ChannelPlayer(this, mrfc, R.string.tnt_button ,R.string.tnt, R.id.video4, R.id.textView4, R.id.castbut4));
                setAllVisible();
            }
        }else if(section==3){
            if(subSet==1){
                channVid = new HashMap<Integer, ChannelPlayer>();
                channVid.put(1, new ChannelPlayer(this, mrfc, R.string.bbc_button, R.string.bbc, R.id.video1, R.id.textView1, R.id.castbut1));
                channVid.put(2, new ChannelPlayer(this, mrfc, R.string.cnbc_button, R.string.cnbc, R.id.video2, R.id.textView2, R.id.castbut2));
                channVid.put(3, new ChannelPlayer(this, mrfc, R.string.eunews_button, R.string.eunews, R.id.video3, R.id.textView3, R.id.castbut3));
                channVid.put(4, new ChannelPlayer(this, mrfc, R.string.ru24_button, R.string.ru24, R.id.video4, R.id.textView4, R.id.castbut4));
                setAllVisible();
            }
        }else if(section==4){
            if(subSet==1){
                channVid = new HashMap<Integer, ChannelPlayer>();
                channVid.put(1, new ChannelPlayer(this, mrfc, R.string.mtv_button, R.string.mtv, R.id.video1, R.id.textView1, R.id.castbut1));
                channVid.put(2, new ChannelPlayer(this, mrfc, R.string.MTVDance_button, R.string.MTVDance, R.id.video2, R.id.textView2, R.id.castbut2));
                channVid.put(3, new ChannelPlayer(this, mrfc, R.string.BRIDGETV_button, R.string.BRIDGETV, R.id.video3, R.id.textView3, R.id.castbut3));
                channVid.put(4, new ChannelPlayer(this, mrfc, R.string.Mezzo_button ,R.string.Mezzo, R.id.video4, R.id.textView4, R.id.castbut4));
                setAllVisible();

            }
        }else if(section==5){
            if (subSet==1){
                channVid = new HashMap<Integer, ChannelPlayer>();
                channVid.put(1, new ChannelPlayer(this, mrfc, R.string.natgeo_button, R.string.natgeo, R.id.video1, R.id.textView1, R.id.castbut1));
                channVid.put(2, new ChannelPlayer(this, mrfc, R.string.disch_button, R.string.disch, R.id.video2, R.id.textView2, R.id.castbut2));
                channVid.put(3, new ChannelPlayer(this, mrfc, R.string.natgeograph_button, R.string.geographic, R.id.video3, R.id.textView3, R.id.castbut3));
                channVid.put(4, new ChannelPlayer(this, mrfc, R.string.Science_button, R.string.Science, R.id.video4, R.id.textView4, R.id.castbut4));
                setAllVisible();
            }
        }else if(section==6){
            if(subSet==1) {
                channVid = new HashMap<Integer, ChannelPlayer>();
                channVid.put(1, new ChannelPlayer(this, mrfc, R.string.match_button, R.string.match, R.id.video1, R.id.textView1, R.id.castbut1));
                channVid.put(2, new ChannelPlayer(this, mrfc, R.string.match2_button, R.string.match2, R.id.video2, R.id.textView2, R.id.castbut2));
                channVid.put(3, new ChannelPlayer(this, mrfc, R.string.match3_button, R.string.match3, R.id.video3, R.id.textView3, R.id.castbut3));
                channVid.put(4, new ChannelPlayer(this, mrfc, R.string.match4_button, R.string.match4, R.id.video4, R.id.textView4, R.id.castbut4));
                setAllVisible();
            }else if(subSet==2){
                setVisibilityFalse(channVid.get(4), true);
                channVid = new HashMap<Integer, ChannelPlayer>();
                channVid.put(1, new ChannelPlayer(this, mrfc, R.string.eurosport_button, R.string.eurosport, R.id.video1, R.id.textView1, R.id.castbut1));
                channVid.put(2, new ChannelPlayer(this, mrfc, R.string.footy1_button, R.string.footy1, R.id.video2, R.id.textView2, R.id.castbut2));
                channVid.put(3, new ChannelPlayer(this, mrfc, R.string.footy2_button, R.string.footy2, R.id.video3, R.id.textView3, R.id.castbut3));
//                channVid.put(4, new ChannelPlayer(this, mrfc, R.string.match4_button, R.string.match4, R.id.video4, R.id.textView4));
                setAllVisible();
            }
        }else if(section==7) {
            if (subSet == 1) {
                channVid = new HashMap<Integer, ChannelPlayer>();
                channVid.put(1, new ChannelPlayer(this, mrfc, R.string.nbo_button, R.string.nbo, R.id.video1, R.id.textView1, R.id.castbut1));
                channVid.put(2, new ChannelPlayer(this, mrfc, R.string.nbo_signature_button, R.string.nbo_signature, R.id.video2, R.id.textView2, R.id.castbut2));
                channVid.put(3, new ChannelPlayer(this, mrfc, R.string.nbo_comedy_button, R.string.nbo_comedy, R.id.video3, R.id.textView3, R.id.castbut3));
                channVid.put(4, new ChannelPlayer(this, mrfc, R.string.historyUS_button, R.string.historyUS, R.id.video4, R.id.textView4, R.id.castbut4));
                setAllVisible();
            } else if (subSet == 2) {
                channVid = new HashMap<Integer, ChannelPlayer>();
                channVid.put(1, new ChannelPlayer(this, mrfc, R.string.showtime2_button, R.string.showtime2, R.id.video1, R.id.textView1, R.id.castbut1));
                channVid.put(2, new ChannelPlayer(this, mrfc, R.string.starz_button, R.string.starz, R.id.video2, R.id.textView2, R.id.castbut2));
                channVid.put(3, new ChannelPlayer(this, mrfc, R.string.tlc_button, R.string.tlc, R.id.video3, R.id.textView3, R.id.castbut3));
                channVid.put(4, new ChannelPlayer(this, mrfc, R.string.cbs_button, R.string.cbs, R.id.video4, R.id.textView4, R.id.castbut4));
                setAllVisible();
            } else if (subSet == 3) {
                setVisibilityFalse(channVid.get(4), true);
                channVid = new HashMap<Integer, ChannelPlayer>();
                channVid.put(1, new ChannelPlayer(this, mrfc, R.string.showtime_button, R.string.showtime, R.id.video1, R.id.textView1, R.id.castbut1));
                channVid.put(2, new ChannelPlayer(this, mrfc, R.string.amc_button, R.string.amc, R.id.video2, R.id.textView2, R.id.castbut2));
                channVid.put(3, new ChannelPlayer(this, mrfc, R.string.syfy_us_button, R.string.syfy_us, R.id.video3, R.id.textView3, R.id.castbut3));
//                    channVid.put(4, new ChannelPlayer(this, mrfc, R.string.ru24_button, R.string.ru24, R.id.video4, R.id.textView4));
                setAllVisible();
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

    private void setVisibilityFalse(ChannelPlayer cp, boolean all){
        if(cp!=null ){
//            if(cp.isVisible) {
                cp.setVisibility(false, all);
//            }
        }
    }

    private void setAllVisible(){
        for (Integer i:channVid.keySet())
            channVid.get(i).setVisibility(true, false);
    }

    private void startChannels(){
        for(Integer s : channVid.keySet()) {
            if(s<5) {
//                new Thread(channVid.get(s)).start();
            }
        }
    }

    public void stopChannels(){
        for(Integer s : channVid.keySet()) {
            if(s<5) {
                channVid.get(s).stopVideo();
            }
        }
    }

    public void setVideoSizeToDefault(){
        for(Integer s : channVid.keySet()) {
            if(s<5) {
                channVid.get(s).vidSizeToDefault();
            }
        }
    }

    public void resumePauseButPressed(){
//        Toast.makeText(this,channVid.keySet().size()+"",Toast.LENGTH_SHORT).show();
        for (Integer s: channVid.keySet()) {
            if(!isVideoStopped) {
                channVid.get(s).stopVideo();
                resumePauseBut.setText("Play");
                setVisibilityFalse(channVid.get(s), false);
                resumePauseBut.setTextColor(getResources().getColor(R.color.colorPrimary));
            }else {
                channVid.get(s).resumeVideo();
                resumePauseBut.setText("Pause");
                setAllVisible();
                resumePauseBut.setTextColor(getResources().getColor(R.color.colorAccent));

            }
        }
        isVideoStopped =! isVideoStopped;
    }

    public void bottomButtonPanelStart(){
        resumePauseBut = (Button) findViewById(R.id.buttonStopStart);
        resumePauseBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               resumePauseButPressed();
            }
        });

        final boolean[] nextStop = {false};

        final Button nextButton = (Button) findViewById(R.id.buttonNext);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopChannels();
                if(subSet<3) subSet++;
                getChannels(section,subSet);
//                resumePauseBut.setText("Pause");
//                resumePauseBut.setTextColor(getResources().getColor(R.color.colorAccent));
                isVideoStopped = false;
                resumePauseButPressed();
                allVideoTextToDefault();
//                startChannels();
                allPageNumsColorToBlack();
                pageNums.get(subSet-1).setTextColor(getResources().getColor(R.color.colorPrimary));
            }
        });

        Button prevButton = (Button) findViewById(R.id.buttonPrev);
        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopChannels();
                if(subSet>1)subSet--;
                getChannels(section,subSet);
//                resumePauseBut.setText("Pause");
//                resumePauseBut.setTextColor(getResources().getColor(R.color.colorAccent));
                isVideoStopped = false;
                resumePauseButPressed();
                allVideoTextToDefault();
                allPageNumsColorToBlack();
                pageNums.get(subSet-1).setTextColor(getResources().getColor(R.color.colorPrimary));
            }
        });


    }

    private void allPageNumsColorToBlack() {
        for (TextView t : pageNums){
           t.setTextColor(getResources().getColor(R.color.black));
        }
        pageNums.get(0).setTextColor(getResources().getColor(R.color.colorPrimary));
    }

    public Intent startsUpdateIntent(){
//        startActivity(new Intent(this, update.class));
        return new Intent(this, update.class);

    }

    public void topButtonPanleStart(){
        one = (Button) findViewById(R.id.button1);
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopChannels();
                section=1;
                subSet=1;
                getChannels(section,subSet);
                allButtonsColorToDefaultOnTopPanel();
                one.setTextColor(getResources().getColor(R.color.colorPrimary));
                one.setBackgroundColor(getResources().getColor(R.color.white));
//                resumePauseBut.setText("Pause");
//                resumePauseBut.setTextColor(getResources().getColor(R.color.colorAccent));
                isVideoStopped = false;
                resumePauseButPressed();
                allVideoTextToDefault();
                allPageNumsColorToBlack();
            }
        });

        two = (Button) findViewById(R.id.button2);
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopChannels();
                section=2;
                subSet=1;
                getChannels(section,subSet);
                allButtonsColorToDefaultOnTopPanel();
                two.setTextColor(getResources().getColor(R.color.colorPrimary));
                two.setBackgroundColor(getResources().getColor(R.color.white));
//                resumePauseBut.setText("Pause");
//                resumePauseBut.setTextColor(getResources().getColor(R.color.colorAccent));
                isVideoStopped = false;
                resumePauseButPressed();
                allVideoTextToDefault();
                allPageNumsColorToBlack();
            }
        });

        three = (Button) findViewById(R.id.button3);
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopChannels();
                section=3;
                subSet=1;
                getChannels(section,subSet);
                allButtonsColorToDefaultOnTopPanel();
                three.setTextColor(getResources().getColor(R.color.colorPrimary));
                three.setBackgroundColor(getResources().getColor(R.color.white));
//                resumePauseBut.setText("Pause");
//                resumePauseBut.setTextColor(getResources().getColor(R.color.colorAccent));
                isVideoStopped = false;
                resumePauseButPressed();
                allVideoTextToDefault();
                allPageNumsColorToBlack();
            }
        });

        four = (Button) findViewById(R.id.button4);
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopChannels();
                section=4;
                subSet=1;
                getChannels(section,subSet);
                allButtonsColorToDefaultOnTopPanel();
                four.setTextColor(getResources().getColor(R.color.colorPrimary));
                four.setBackgroundColor(getResources().getColor(R.color.white));
//                resumePauseBut.setText("Pause");
//                resumePauseBut.setTextColor(getResources().getColor(R.color.colorAccent));
                isVideoStopped = false;
                resumePauseButPressed();
                allVideoTextToDefault();
                allPageNumsColorToBlack();
            }
        });

        five = (Button) findViewById(R.id.button5);
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopChannels();
                section=5;
                subSet=1;
                getChannels(section,subSet);
                allButtonsColorToDefaultOnTopPanel();
                five.setTextColor(getResources().getColor(R.color.colorPrimary));
                five.setBackgroundColor(getResources().getColor(R.color.white));
//                resumePauseBut.setText("Pause");
//                resumePauseBut.setTextColor(getResources().getColor(R.color.colorAccent));
                isVideoStopped = false;
                resumePauseButPressed();
                allVideoTextToDefault();
                allPageNumsColorToBlack();
            }
        });

        six = (Button) findViewById(R.id.button6);
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopChannels();
                section=6;
                subSet=1;
                getChannels(section,subSet);
                allButtonsColorToDefaultOnTopPanel();
                six.setTextColor(getResources().getColor(R.color.colorPrimary));
                six.setBackgroundColor(getResources().getColor(R.color.white));
//                resumePauseBut.setText("Pause");
//                resumePauseBut.setTextColor(getResources().getColor(R.color.colorAccent));
                isVideoStopped = false;
                resumePauseButPressed();
                allVideoTextToDefault();
                allPageNumsColorToBlack();
            }
        });

        seven = (Button) findViewById(R.id.button7);
        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopChannels();
                section=7;
                subSet=1;
                getChannels(section,subSet);
                allButtonsColorToDefaultOnTopPanel();
                seven.setTextColor(getResources().getColor(R.color.colorPrimary));
                seven.setBackgroundColor(getResources().getColor(R.color.white));
//                resumePauseBut.setText("Pause");
//                resumePauseBut.setTextColor(getResources().getColor(R.color.colorAccent));
                isVideoStopped = false;
                resumePauseButPressed();
                allVideoTextToDefault();
                allPageNumsColorToBlack();
            }
        });

//        eight = (Button) findViewById(R.id.button8);
//        eight.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                stopChannels();
//                section=8;
//                subSet=1;
//                getChannels(section,subSet);
//                allButtonsColorToDefaultOnTopPanel();
//                eight.setTextColor(getResources().getColor(R.color.colorPrimary));
//                eight.setBackgroundColor(getResources().getColor(R.color.white));
////                resumePauseBut.setText("Pause");
////                resumePauseBut.setTextColor(getResources().getColor(R.color.colorAccent));
//                isVideoStopped = false;
//                resumePauseButPressed();
//                allVideoTextToDefault();
//            }
//        });
    }

    public void allButtonsColorToDefaultOnTopPanel(){
        one.setTextColor(getResources().getColor(R.color.black));
        one.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        two.setTextColor(getResources().getColor(R.color.black));
        two.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        three.setTextColor(getResources().getColor(R.color.black));
        three.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        four.setTextColor(getResources().getColor(R.color.black));
        four.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        five.setTextColor(getResources().getColor(R.color.black));
        five.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        six.setTextColor(getResources().getColor(R.color.black));
        six.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        seven.setTextColor(getResources().getColor(R.color.black));
        seven.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//        eight.setTextColor(getResources().getColor(R.color.black));
//        eight.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

    }

    public void allVideoTextToDefault(){
        for (Integer i : channVid.keySet())
            channVid.get(i).setTextViewColorToDefault();
    }

    public void setupGianPC(){
        if(!gianpcPanel.isShown()) {
            gianpcPanel.setVisibility(View.VISIBLE);

            connectBut = (Button) findViewById(R.id.connectButton);
            iptext = (EditText) findViewById(R.id.ipText);
            try {
                connectBut.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final String ip = iptext.getText().toString();
                        if (!ip.equals("")) {
                            String[] ips = ip.split(".");
                            for (String i : ips) {
                                Integer.parseInt(i);
                            }
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    pcsc = new pcSocketClient(me);
                                    pcsc.makeClient(ip, 8321);
                                }
                            }).start();
                            gianpcPanel.setVisibility(View.GONE);
                        } else Toast.makeText(me, "No IP is given", Toast.LENGTH_SHORT).show();
                    }
                });
            }catch (Exception e){Toast.makeText(me, "No a valid IP", Toast.LENGTH_SHORT).show();}
        }else gianpcPanel.setVisibility(View.GONE);
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
        resumePauseButPressed();
    }

    @Override
    protected void onPause() {
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
                setupGianPC();
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
