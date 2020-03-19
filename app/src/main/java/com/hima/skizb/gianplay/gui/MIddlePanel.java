package com.hima.skizb.gianplay.gui;

import android.widget.TextView;

import com.hima.skizb.gianplay.logic.ChannelPlayer;
import com.hima.skizb.gianplay.MainActivity;
import com.hima.skizb.gianplay.R;

import java.util.ArrayList;
import java.util.List;

public class MIddlePanel {
    private final MainActivity context;


    public MIddlePanel(MainActivity context){
        this.context = context;
    }

    public List<TextView> makePageNumbers(){
        List<TextView> pageNums = new ArrayList<TextView>();
        pageNums.add((TextView)context.findViewById(R.id.sctextView1));
        pageNums.add((TextView)context.findViewById(R.id.sctextView2));
        pageNums.add((TextView)context.findViewById(R.id.sctextView3));
        pageNums.add((TextView)context.findViewById(R.id.sctextView4));
        return pageNums;
    }

    public void allPageNumsColorToBlack(List<TextView> pageNums) {
        for (TextView t : pageNums){
            t.setTextColor(context.getResources().getColor(R.color.black));
        }
    }

    public void makeNumOfPagesVisible(int i) {
        for (TextView t: context.pageNums){
            t.setVisibility(TextView.GONE);
        }
        for(int j = 0; j<i; j++){
            context.pageNums.get(j).setVisibility(TextView.VISIBLE);
        }
    }


    public void setVisibilityFalse(ChannelPlayer cp, boolean all){
        if(cp!=null ){
//            if(cp.isVisible) {
            cp.setVisibility(false, all);
//            }
        }
    }




    public void setAllVisible(){
        for (Integer i:context.channVid.keySet())
            context.channVid.get(i).setVisibility(true, false);
    }

    public void startChannels(){
        for(Integer s : context.channVid.keySet()) {
            if(s<5) {
//                new Thread(channVid.get(s)).start();
            }
        }
    }

    public void stopChannels(){
        for(Integer s : context.channVid.keySet()) {
            if(s<5) {
                context.channVid.get(s).stopVideo();
            }
        }
    }

    public void setVideoSizeToDefault(){
        for(Integer s : context.channVid.keySet()) {
            if(s<5) {
                context.channVid.get(s).vidSizeToDefault();
            }
        }
    }
    public void allVideoTextToDefault(){
        for (Integer i : context.channVid.keySet())
            context.channVid.get(i).setTextViewColorToDefault();
    }
    public void currentChannelsToDefault(){context.getChannels(context.section,context.subSet);}

}
