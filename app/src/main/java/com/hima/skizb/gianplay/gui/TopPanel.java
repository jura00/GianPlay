package com.hima.skizb.gianplay.gui;

import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hima.skizb.gianplay.MainActivity;
import com.hima.skizb.gianplay.R;
import com.hima.skizb.gianplay.logic.GianPlayServer;
import com.hima.skizb.gianplay.logic.pcSocketClient;

public class TopPanel {
    private final MainActivity context;
    public Button buts[] = new Button[9];
    public int butsNumOfPages[] = {2,1,1,1,1,1,2,0,0};

    public TopPanel(MainActivity context){
        this.context = context;
    }

    public void setupGianPC(final GianPlayServer gps){
        if(!context.gianpcPanel.isShown()) {
            context.gianpcPanel.setVisibility(View.VISIBLE);

            context.connectBut = (Button) context.findViewById(R.id.connectButton);
            context.iptext = (EditText) context.findViewById(R.id.ipText);
            try {
                context.connectBut.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final String ip = context.iptext.getText().toString();
                        if (!ip.equals("")) {
//                            String[] ips = ip.split(".");
//                            for (String i : ips) {
//                                Integer.parseInt(i);
//                            }
//                            new Thread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    context.pcsc = new pcSocketClient(context);
//                                    context.pcsc.makeClient(ip, 8321);
//                                }
//                            }).start();
                            gps.set_server_address(ip);
                            context.gianpcPanel.setVisibility(View.GONE);
                            gps.fillUIWithItems();
                            context.connectBut.setInputType(0);
                            context.connectBut.onEditorAction(EditorInfo.IME_ACTION_DONE);
                        } else Toast.makeText(context, "No IP is given", Toast.LENGTH_SHORT).show();
                    }
                });
            }catch (Exception e){Toast.makeText(context, "No a valid IP", Toast.LENGTH_SHORT).show();}
        }else context.gianpcPanel.setVisibility(View.GONE);
    }

    public void topButtonPanleStart(){
        buts[0] = (Button) context.findViewById(R.id.button1);

        buts[1] = (Button) context.findViewById(R.id.button2);

        buts[2] = (Button) context.findViewById(R.id.button3);

        buts[3] = (Button) context.findViewById(R.id.button4);

        buts[4] = (Button) context.findViewById(R.id.button5);

        buts[5] = (Button) context.findViewById(R.id.button6);

        buts[6] = (Button) context.findViewById(R.id.button7);

        buts[7] = (Button) context.findViewById(R.id.button8);

        buts[8] = (Button) context.findViewById(R.id.button9);

        addListenersToButtons();
    }

    private void addListenersToButtons(){
        for (int i = 0; i< buts.length;i++){
            final int finalI = i;
            buts[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.mp.stopChannels();
                    context.section= finalI +1;
                    context.subSet=1;
                    context.sectionNumOfPages = butsNumOfPages[finalI];
                    context.getChannels(context.section,context.subSet);
                    allButtonsColorToDefaultOnTopPanel();
                    buts[finalI].setTextColor(context.getResources().getColor(R.color.colorPrimary));
                    buts[finalI].setBackgroundColor(context.getResources().getColor(R.color.white));
                    context.isVideoStopped = false;
                    context.bp.resumePauseButPressed();
                    context.mp.allVideoTextToDefault();
                    context.mp.allPageNumsColorToBlack(context.pageNums);
                    context.pageNums.get(0).setTextColor(context.getResources().getColor(R.color.colorPrimary));
                }
            });
        }
    }

    public void allButtonsColorToDefaultOnTopPanel(){
        for(Button b: buts) {
            b.setTextColor(context.getResources().getColor(R.color.black));
            b.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
        }
    }


}
