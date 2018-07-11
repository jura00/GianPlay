package com.hima.skizb.gianplay;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private Map<Integer, ChannelPlayer> channVid;

    private int section = 1;
    private int subSet = 1;

    private boolean isVideoStopped = false;

    private Button one;
    private Button two;
    private Button three;
    private Button four;
    private Button five;
    private Button six;
    private Button seven;
    private Button resumePauseBut;

    MediaRouterForCast mrfc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mrfc = new MediaRouterForCast(this);
        mrfc.initMediaRouter();

        getChannels(section,subSet);


        startChannels();
        topButtonPanleStart();
        one.setTextColor(getResources().getColor(R.color.black));
        buttomButtonPanelStart();
        resumePauseBut.setTextColor(getResources().getColor(R.color.colorAccent));

    }

    private void getChannels(int section, int subSet){
        if(section==1){
            if(subSet==1){
                channVid = new HashMap<Integer, ChannelPlayer>();
                channVid.put(1, new ChannelPlayer(this, mrfc, R.string.h1_button, R.string.h1, R.id.video1, R.id.textView1));
                channVid.put(2, new ChannelPlayer(this, mrfc, R.string.armenia_button, R.string.armenia, R.id.video2, R.id.textView2));
                channVid.put(3, new ChannelPlayer(this, mrfc, R.string.atv_button, R.string.atv, R.id.video3, R.id.textView3));
                channVid.put(4, new ChannelPlayer(this, mrfc, R.string.armnews_button ,R.string.armnews, R.id.video4, R.id.textView4));
                setAllVisible();
            }else if(subSet==2){
                channVid = new HashMap<Integer, ChannelPlayer>();
                channVid.put(1, new ChannelPlayer(this, mrfc, R.string.h2_button, R.string.h2, R.id.video1, R.id.textView1));
                channVid.put(2, new ChannelPlayer(this, mrfc, R.string.h3_button, R.string.h3, R.id.video2, R.id.textView2));
                channVid.put(3, new ChannelPlayer(this, mrfc, R.string.kentron_button, R.string.kentron, R.id.video3, R.id.textView3));
                channVid.put(4, new ChannelPlayer(this, mrfc, R.string.erkir_button ,R.string.erkir, R.id.video4, R.id.textView4));
                setAllVisible();
            }else if(subSet==3){
                setVisibilityFalse(channVid.get(4));
                channVid = new HashMap<Integer, ChannelPlayer>();
                channVid.put(1, new ChannelPlayer(this, mrfc, R.string.ararat_button, R.string.ararat, R.id.video1, R.id.textView1));
                channVid.put(2, new ChannelPlayer(this, mrfc, R.string.ar_button, R.string.ar, R.id.video2, R.id.textView2));
                channVid.put(3, new ChannelPlayer(this, mrfc, R.string.dar_button, R.string.dar, R.id.video3, R.id.textView3));
                setAllVisible();
            }
        }else if(section==2){
            if (subSet==1){
                channVid = new HashMap<Integer, ChannelPlayer>();
                channVid.put(1, new ChannelPlayer(this, mrfc, R.string.xit_button, R.string.xit, R.id.video1, R.id.textView1));
                channVid.put(2, new ChannelPlayer(this, mrfc, R.string.mix_button, R.string.mix, R.id.video2, R.id.textView2));
                channVid.put(3, new ChannelPlayer(this, mrfc, R.string.komedia_button, R.string.komedia, R.id.video3, R.id.textView3));
                channVid.put(4, new ChannelPlayer(this, mrfc, R.string.men_button ,R.string.men, R.id.video4, R.id.textView4));
                setAllVisible();
            }else if(subSet==2){
                channVid = new HashMap<Integer, ChannelPlayer>();
                channVid.put(1, new ChannelPlayer(this, mrfc, R.string.hazar_button, R.string.hazar, R.id.video1, R.id.textView1));
                channVid.put(2, new ChannelPlayer(this, mrfc, R.string.kinop1_button, R.string.kinop1, R.id.video2, R.id.textView2));
                channVid.put(3, new ChannelPlayer(this, mrfc, R.string.kinop2_button, R.string.kinop2, R.id.video3, R.id.textView3));
                channVid.put(4, new ChannelPlayer(this, mrfc, R.string.tnt_button ,R.string.tnt, R.id.video4, R.id.textView4));
                setAllVisible();
            }
        }else if(section==3){
            if(subSet==1){
                channVid = new HashMap<Integer, ChannelPlayer>();
                channVid.put(1, new ChannelPlayer(this, mrfc, R.string.bbc_button, R.string.bbc, R.id.video1, R.id.textView1));
                channVid.put(2, new ChannelPlayer(this, mrfc, R.string.cnbc_button, R.string.cnbc, R.id.video2, R.id.textView2));
                channVid.put(3, new ChannelPlayer(this, mrfc, R.string.eunews_button, R.string.eunews, R.id.video3, R.id.textView3));
                channVid.put(4, new ChannelPlayer(this, mrfc, R.string.ru24_button, R.string.ru24, R.id.video4, R.id.textView4));
                setAllVisible();
            }
        }else if(section==4){
            if(subSet==1){
                channVid = new HashMap<Integer, ChannelPlayer>();
                channVid.put(1, new ChannelPlayer(this, mrfc, R.string.mtv_button, R.string.mtv, R.id.video1, R.id.textView1));
                channVid.put(2, new ChannelPlayer(this, mrfc, R.string.MTVDance_button, R.string.MTVDance, R.id.video2, R.id.textView2));
                channVid.put(3, new ChannelPlayer(this, mrfc, R.string.BRIDGETV_button, R.string.BRIDGETV, R.id.video3, R.id.textView3));
                channVid.put(4, new ChannelPlayer(this, mrfc, R.string.Mezzo_button ,R.string.Mezzo, R.id.video4, R.id.textView4));
                setAllVisible();

            }
        }else if(section==5){
            if (subSet==1){
                channVid = new HashMap<Integer, ChannelPlayer>();
                channVid.put(1, new ChannelPlayer(this, mrfc, R.string.natgeo_button, R.string.natgeo, R.id.video1, R.id.textView1));
                channVid.put(2, new ChannelPlayer(this, mrfc, R.string.disch_button, R.string.disch, R.id.video2, R.id.textView2));
                channVid.put(3, new ChannelPlayer(this, mrfc, R.string.natgeograph_button, R.string.geographic, R.id.video3, R.id.textView3));
                channVid.put(4, new ChannelPlayer(this, mrfc, R.string.Science_button, R.string.Science, R.id.video4, R.id.textView4));
                setAllVisible();
            }
        }else if(section==6){
            if(subSet==1) {
                channVid = new HashMap<Integer, ChannelPlayer>();
                channVid.put(1, new ChannelPlayer(this, mrfc, R.string.match_button, R.string.match, R.id.video1, R.id.textView1));
                channVid.put(2, new ChannelPlayer(this, mrfc, R.string.match2_button, R.string.match2, R.id.video2, R.id.textView2));
                channVid.put(3, new ChannelPlayer(this, mrfc, R.string.match3_button, R.string.match3, R.id.video3, R.id.textView3));
                channVid.put(4, new ChannelPlayer(this, mrfc, R.string.match4_button, R.string.match4, R.id.video4, R.id.textView4));
                setAllVisible();
            }else if(subSet==2){
                setVisibilityFalse(channVid.get(4));
                channVid = new HashMap<Integer, ChannelPlayer>();
                channVid.put(1, new ChannelPlayer(this, mrfc, R.string.eurosport_button, R.string.eurosport, R.id.video1, R.id.textView1));
                channVid.put(2, new ChannelPlayer(this, mrfc, R.string.footy1_button, R.string.footy1, R.id.video2, R.id.textView2));
                channVid.put(3, new ChannelPlayer(this, mrfc, R.string.footy2_button, R.string.footy2, R.id.video3, R.id.textView3));
//                channVid.put(4, new ChannelPlayer(this, mrfc, R.string.match4_button, R.string.match4, R.id.video4, R.id.textView4));
                setAllVisible();
            }
        }else if(section==7){

        }
    }

    private void setVisibilityFalse(ChannelPlayer cp){
        if(cp!=null && cp.isVisible){
            cp.setVisibility(false);
        }
    }

    private void setAllVisible(){
        for (Integer i:channVid.keySet())
            channVid.get(i).setVisibility(true);
    }

    private void startChannels(){
        for(Integer s : channVid.keySet()) {
            if(s<5) {
//                new Thread(channVid.get(s)).start();
            }
        }
    }

    private void stopChannels(){
        for(Integer s : channVid.keySet()) {
            if(s<5) {
                channVid.get(s).destruct();
            }
        }
    }

    public void buttomButtonPanelStart(){
        resumePauseBut = (Button) findViewById(R.id.buttonStopStart);
        resumePauseBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (Integer s: channVid.keySet()) {
                    if(!isVideoStopped) {
                        channVid.get(s).stopVideo();
                        resumePauseBut.setText("Resume");
                        resumePauseBut.setTextColor(getResources().getColor(R.color.colorPrimary));
                    }
                    else {
                        channVid.get(s).startVideo();
                        resumePauseBut.setText("Pause");
                        resumePauseBut.setTextColor(getResources().getColor(R.color.colorAccent));

                    }
                }
                isVideoStopped =! isVideoStopped;
            }
        });

        Button nextButton = (Button) findViewById(R.id.buttonNext);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopChannels();
                if(subSet<3) subSet++;
                getChannels(section,subSet);
                resumePauseBut.setText("Pause");
                resumePauseBut.setTextColor(getResources().getColor(R.color.colorAccent));
                isVideoStopped = false;
                allVideoTextToDefault();

//                startChannels();
            }
        });

        Button prevButton = (Button) findViewById(R.id.buttonPrev);
        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopChannels();
                if(subSet>1)subSet--;
                getChannels(section,subSet);
                resumePauseBut.setText("Pause");
                resumePauseBut.setTextColor(getResources().getColor(R.color.colorAccent));
                isVideoStopped = false;
                allVideoTextToDefault();
            }
        });

        Button updateButton = (Button) findViewById(R.id.buttonUpdate);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopChannels();
                resumePauseBut.setText("Resume");
                resumePauseBut.setTextColor(getResources().getColor(R.color.colorPrimary));
                isVideoStopped = true;
//                allVideoTextToDefault();
                startsUpdateIntent();

            }
        });
    }

    public void startsUpdateIntent(){
        startActivity(new Intent(this, update.class));

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
                resumePauseBut.setText("Pause");
                resumePauseBut.setTextColor(getResources().getColor(R.color.colorAccent));
                isVideoStopped = false;
                allVideoTextToDefault();
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
                resumePauseBut.setText("Pause");
                resumePauseBut.setTextColor(getResources().getColor(R.color.colorAccent));
                isVideoStopped = false;
                allVideoTextToDefault();
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
                resumePauseBut.setText("Pause");
                resumePauseBut.setTextColor(getResources().getColor(R.color.colorAccent));
                isVideoStopped = false;
                allVideoTextToDefault();
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
                resumePauseBut.setText("Pause");
                resumePauseBut.setTextColor(getResources().getColor(R.color.colorAccent));
                isVideoStopped = false;
                allVideoTextToDefault();
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
                resumePauseBut.setText("Pause");
                resumePauseBut.setTextColor(getResources().getColor(R.color.colorAccent));
                isVideoStopped = false;
                allVideoTextToDefault();
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
                resumePauseBut.setText("Pause");
                resumePauseBut.setTextColor(getResources().getColor(R.color.colorAccent));
                isVideoStopped = false;
                allVideoTextToDefault();
            }
        });

//        seven = (Button) findViewById(R.id.button7);
//        seven.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                stopChannels();
//                section=7;
//                subSet=1;
//                getChannels(section,subSet);
//                allButtonsColorToDefaultOnTopPanel();
//                seven.setTextColor(getResources().getColor(R.color.colorPrimary));
//                resumePauseBut.setText("Pause");
//                resumePauseBut.setTextColor(getResources().getColor(R.color.colorAccent));
//                isVideoStopped = false;
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
//        seven.setTextColor(getResources().getColor(R.color.black));

    }

    public void allVideoTextToDefault(){
        for (Integer i : channVid.keySet())
            channVid.get(i).setTextViewColorToDefault();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mrfc.onResume();
        resumePauseBut.setText("Pause");
        resumePauseBut.setTextColor(getResources().getColor(R.color.colorAccent));
        isVideoStopped = false;
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
        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mrfc.onDestroy();
    }
}
