package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    Fragment1 fragment1;
    Fragment2 fragment2;
    Fragment3 fragment3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        //프래그먼트 생성
        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();

        //제일 처음 띄워줄 뷰를 세팅해줍니다. commit();까지 해줘야 합니다.
        getSupportFragmentManager().beginTransaction().replace(R.id.main_layout,fragment1).commitAllowingStateLoss();

        //bottomnavigationview의 아이콘을 선택 했을때 원하는 프래그먼트가 띄워질 수 있도록 리스너를 추가합니다.
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    //menu_bottom.xml에서 지정해줬던 아이디 값을 받아와서 각 아이디값마다 다른 이벤트를 발생시킵니다.
                    case R.id.tab1:{ getSupportFragmentManager().beginTransaction()
                            .replace(R.id.main_layout,fragment1).commitAllowingStateLoss();
                    return true; }
                    case R.id.tab2:{ getSupportFragmentManager().beginTransaction()
                            .replace(R.id.main_layout,fragment2).commitAllowingStateLoss();
                    return true; }
                    case R.id.tab3:{ getSupportFragmentManager().beginTransaction()
                            .replace(R.id.main_layout,fragment3).commitAllowingStateLoss();
                    return true; }
                    default: return false;
                }
            }
        });
    }
}




