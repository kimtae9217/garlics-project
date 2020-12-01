package com.example.myapplication;

import android.os.Build;
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

public class Timer extends Fragment {
    Chronometer chronometer;

    ImageButton btStart, btStop, btPause;
    ImageView maneul2;
    Animation run_maneul;
    private boolean isResume;
    Handler handler;
    long tMilliSec, tStart, tBuff, tUpadate = 0L;
    int sec, min, milliSec;
    ViewGroup viewGroup;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.timer, container, false);
        chronometer = (Chronometer) viewGroup.findViewById(R.id.chronometer);
        btStart = (ImageButton) viewGroup.findViewById(R.id.bt_start);
        btPause = (ImageButton) viewGroup.findViewById(R.id.bt_pause);
        btStop = (ImageButton) viewGroup.findViewById(R.id.bt_stop);
        maneul2 =(ImageView) viewGroup.findViewById(R.id.timer_maneul2);

        run_maneul = AnimationUtils.loadAnimation(getActivity(), R.anim.run_maneul);
        handler = new Handler();

        btStart.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                btPause.setVisibility(View.INVISIBLE);

                maneul2.startAnimation(run_maneul); //Animation Start

                if (!isResume) {
                    tStart = SystemClock.uptimeMillis();
                    handler.postDelayed(runnable, 0);
                    chronometer.start();
                    isResume = true;
                    btStop.setVisibility(View.GONE);
                    btPause.setVisibility(View.GONE);

                    btStart.setImageDrawable(getResources().getDrawable(
                            R.drawable.ic_pause
                    ));

                    btStart.setImageDrawable(getResources().getDrawable(R.drawable.ic_pause, null));

                } else {
//                    icanchor.getAnimation().cancel();
                    tBuff += tMilliSec;
                    handler.removeCallbacks(runnable);
                    chronometer.stop();
                    isResume = false;
                    btStop.setVisibility(View.VISIBLE);
                    btStart.setImageDrawable(getResources().getDrawable(R.drawable.ic_play, null));
                }
            }
        });

        btStop.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                maneul2.clearAnimation(); //Animation Stop
                if (!isResume) {

                    btStart.setImageDrawable(getResources().getDrawable(
                            R.drawable.ic_play
                    ));

                    btStart.setImageDrawable(getResources().getDrawable(R.drawable.ic_play, null));
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

    public Runnable runnable = new Runnable() {
        @Override
        public void run() {
            tMilliSec = SystemClock.uptimeMillis() - tStart;
            tUpadate = tBuff + tMilliSec;
            sec = (int) (tUpadate / 1000);
            min = sec / 60;
            sec = sec % 60;
            milliSec = (int) (tUpadate % 100);
            chronometer.setText(String.format("%02d", min) + ":" + String.format("%02d", sec) + ":" + String.format("%02d", milliSec));
            handler.postDelayed(this, 60);
        }
    };
}
