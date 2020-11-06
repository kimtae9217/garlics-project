package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Timer extends Fragment {
    Chronometer chronometer;

    ImageButton btStart, btStop, btPause;
    ImageView icanchor;
    Animation roundingalone;
    private boolean isResume;
    Handler handler;
    long tMilliSec, tStart, tBuff, tUpadate = 0L;
    int sec, min, milliSec;
    ViewGroup viewGroup;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.timer, container, false);
        chronometer = (Chronometer)viewGroup.findViewById(R.id.chronometer);
        btStart = (ImageButton) viewGroup.findViewById(R.id.bt_start);
        btPause = (ImageButton)viewGroup.findViewById(R.id.bt_pause);
        btStop = (ImageButton)viewGroup.findViewById(R.id.bt_stop);
        icanchor = (ImageView)viewGroup.findViewById(R.id.icanchor);

        roundingalone = AnimationUtils.loadAnimation(getActivity(), R.anim.roundingalone);

        handler = new Handler();

        btStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btPause.setVisibility(View.INVISIBLE);
                icanchor.startAnimation(roundingalone); //Animation Start

                if(!isResume){
                    tStart = SystemClock.uptimeMillis();
                    handler.postDelayed(runnable, 0);
                    chronometer.start();
                    isResume = true;
                    btStop.setVisibility(View.GONE);
                    btPause.setVisibility(View.GONE);
                    btStart.setImageDrawable(getResources().getDrawable(
                            R.drawable.ic_pause
                    ));
                }else {
//                    icanchor.getAnimation().cancel();
                    tBuff += tMilliSec;
                    handler.removeCallbacks(runnable);
                    chronometer.stop();
                    isResume = false;
                    btStop.setVisibility(View.VISIBLE);
                    btStart.setImageDrawable(getResources().getDrawable(
                            R.drawable.ic_play
                    ));
                }
            }
        });

        btStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                icanchor.clearAnimation(); //Animation Stop
                if (!isResume){
                    btStart.setImageDrawable(getResources().getDrawable(
                            R.drawable.ic_play
                    ));
                    tMilliSec = 0L;
                    tStart = 0L;
                    tBuff = 0L;
                    tUpadate = 0L;
                    sec = 0;
                    min = 0;
                    milliSec = 0;
                    chronometer.setText("00:00:00");

                }
            }
        });
        return viewGroup;
    }
