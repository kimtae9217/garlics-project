package com.example.myapplication.main;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.setting.Setting;
import com.example.myapplication.Timer;
import com.example.myapplication.community.Community;
import com.example.myapplication.profile.Profile;
import com.google.android.material.bottomnavigation.BottomNavigationView;
public class MainActivity extends AppCompatActivity {
    public static Context context;
    BottomNavigationView bottomNavigationView;
    Profile profile;
    Timer timer;
    Community community;
    Setting setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        //프래그먼트 생성
        timer = new Timer();
        profile = new Profile();
        community = new Community();
        setting = new Setting();



        //제일 처음 띄워줄 뷰를 세팅해줍니다. commit();까지 해줘야 합니다.
        getSupportFragmentManager().beginTransaction().replace(R.id.main_layout, timer).commitAllowingStateLoss();

        //bottomnavigationview의 아이콘을 선택 했을때 원하는 프래그먼트가 띄워질 수 있도록 리스너를 추가합니다.
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    //menu_bottom.xml에서 지정해줬던 아이디 값을 받아와서 각 아이디값마다 다른 이벤트를 발생시킵니다.
                    case R.id.tab1:{ getSupportFragmentManager().beginTransaction()
                            .replace(R.id.main_layout, timer).commitAllowingStateLoss();
                    return true; }
                    case R.id.tab2:{ getSupportFragmentManager().beginTransaction()
                            .replace(R.id.main_layout,profile).commitAllowingStateLoss();
                    return true; }
                    case R.id.tab3:{ getSupportFragmentManager().beginTransaction()
                            .replace(R.id.main_layout, community).commitAllowingStateLoss();
                    return true; }
                    case R.id.tab4:{ getSupportFragmentManager().beginTransaction()
                            .replace(R.id.main_layout, setting).commitAllowingStateLoss();
                        return true; }
                    default: return false;
                }
            }
        });
    }
}




