package com.hima.skizb.gianplay.gui;

import android.view.View;
import android.widget.Button;

import com.hima.skizb.gianplay.MainActivity;
import com.hima.skizb.gianplay.R;

public class BottomPanel {

    private MainActivity context;
    public Button resumePauseBut;

    public BottomPanel(MainActivity context){
        this.context = context;
    }

    public void resumePauseButPressed(){
//        Toast.makeText(this,channVid.keySet().size()+"",Toast.LENGTH_SHORT).show();
        for (Integer s: context.channVid.keySet()) {
            if(!context.isVideoStopped) {
                context.channVid.get(s).stopVideo();
                resumePauseBut.setText("Play");
                context.mp.setVisibilityFalse(context.channVid.get(s), false);
                resumePauseBut.setTextColor(context.getResources().getColor(R.color.colorPrimary));
            }else {
                context.channVid.get(s).resumeVideo();
                resumePauseBut.setText("Pause");
                context.mp.setAllVisible();
                resumePauseBut.setTextColor(context.getResources().getColor(R.color.colorAccent));

            }
        }
        context.isVideoStopped =! context.isVideoStopped;
        context.mp.makeNumOfPagesVisible(context.sectionNumOfPages);
    }

    public void bottomButtonPanelStart(){
        resumePauseBut = (Button) context.findViewById(R.id.buttonStopStart);
        resumePauseBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resumePauseButPressed();
            }
        });

        final boolean[] nextStop = {false};

        final Button nextButton = (Button) context.findViewById(R.id.buttonNext);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.mp.stopChannels();
                if(context.subSet<context.sectionNumOfPages) context.subSet++;
                context.getChannels(context.section, context.subSet);
//                resumePauseBut.setText("Pause");
//                resumePauseBut.setTextColor(getResources().getColor(R.color.colorAccent));
                context.isVideoStopped = false;
                resumePauseButPressed();
                context.mp.allVideoTextToDefault();
//                startChannels();
                context.mp.allPageNumsColorToBlack(context.pageNums);
                context.pageNums.get(context.subSet-1).setTextColor(context.getResources().getColor(R.color.colorPrimary));
            }
        });

        Button prevButton = (Button) context.findViewById(R.id.buttonPrev);
        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.mp.stopChannels();
                if(context.subSet>1)context.subSet--;
                context.getChannels(context.section,context.subSet);
//                resumePauseBut.setText("Pause");
//                resumePauseBut.setTextColor(getResources().getColor(R.color.colorAccent));
                context.isVideoStopped = false;
                resumePauseButPressed();
                context.mp.allVideoTextToDefault();
                context.mp.allPageNumsColorToBlack(context.pageNums);
                context.pageNums.get(context.subSet-1).setTextColor(context.getResources().getColor(R.color.colorPrimary));
            }
        });


    }
}
