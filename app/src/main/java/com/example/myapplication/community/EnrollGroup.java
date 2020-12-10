package com.example.myapplication.community;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.PreferenceManager;
import com.example.myapplication.R;

public class EnrollGroup extends AppCompatActivity {

    EditText groupName,groupNumbers,groupContents,groupPassword;
    RadioGroup rb;
    RadioButton radio;
    Button btn_finish;
    Context mcontext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enroll_group);
        mcontext = this;

        groupName = (EditText)findViewById(R.id.enroll_group_name);
        groupNumbers = (EditText)findViewById(R.id.enroll_group_numbers);
        groupContents = (EditText)findViewById(R.id.enroll_group_contents);
        rb = (RadioGroup)findViewById(R.id.rb);
        groupPassword =(EditText) findViewById(R.id.group_password);
        radio = (RadioButton)findViewById(R.id.radio2);

        btn_finish = (Button)findViewById(R.id.enroll_group_finish);

        //데이터를 입력받는다.
        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();

                intent.putExtra("groupName",groupName.getText().toString());
                intent.putExtra("groupNumbers",groupNumbers.getText().toString());
                intent.putExtra("groupContents",groupContents.getText().toString());


                setResult(RESULT_OK,intent);
                finish();

            }
        });


    }

}

