package com.example.myapplication.community;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.example.myapplication.PreferenceManager;
import com.example.myapplication.R;
import com.example.myapplication.SharedViewModel;

import org.w3c.dom.Text;

public class CommunityGroup extends AppCompatActivity {



    FrameLayout container;
    TextView title,time,name,number;
    Context mcontext;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        container = (FrameLayout)findViewById(R.id.main_layout);

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.community_group,container, true);


        mcontext = this;


        title = (TextView)findViewById(R.id.community_group_title);
        time = (TextView)findViewById(R.id.community_group_time);
        name = (TextView)findViewById(R.id.community_group_nickname);
        number = (TextView)findViewById(R.id.community_group_number);




        title.setText(""+PreferenceManager.getString(mcontext,"rebuild"));
        time.setText(""+PreferenceManager.getString(mcontext,"time"));
        name.setText(""+PreferenceManager.getString(mcontext,"name"));
        number.setText(" 멤버 인원 :  " + PreferenceManager.getString(mcontext,"number"));









    }
}
