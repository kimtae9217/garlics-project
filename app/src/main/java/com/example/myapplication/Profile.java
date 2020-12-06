package com.example.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Profile extends Fragment {

    private static final String URL = "http://61.245.248.173/garlic/insertUserDB.php";
    public static final int USER_PROFILE = 0;
    public static final int MORNING = 0;
    ImageView userProfile, morning, lunch, dinner, snack;
    ViewGroup viewGroup;
    Bitmap bm;
    TextView tx;
    EditText userHeight, userWeight, userStateMessage;
    Button sbmt;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.profile, container, false);
        userProfile = (ImageView) viewGroup.findViewById(R.id.profile_userPicture);
        morning = (ImageView) viewGroup.findViewById(R.id.profile_morning_image);
        lunch = (ImageView) viewGroup.findViewById(R.id.profile_lunch_image);
        dinner = (ImageView) viewGroup.findViewById(R.id.profile_dinner_Image);
        snack = (ImageView) viewGroup.findViewById(R.id.profile_snack_image);
        sbmt = (Button) viewGroup.findViewById(R.id.submit); //키, 몸무게, 상태메세지 전송 버튼

        sbmt.setOnClickListener(new View.OnClickListener() { // 저장 버튼 누를시 발생 이벤트
            @Override
            public void onClick(View view) {
                insertdata();
            }
        });



        userProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                {
                    doTakeAlbumAction(USER_PROFILE);
                }
            }
        });

        morning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Enroll_photo.class);
                startActivityForResult(intent, MORNING);

            }
        });

        lunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Enroll_photo.class);
                startActivity(intent);

            }
        });

        dinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Enroll_photo.class);
                startActivity(intent);
            }
        });

        snack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Enroll_photo.class);
                startActivity(intent);
            }
        });
        return viewGroup;
    }

    private void insertdata() { // 키, 몸무게, 상태 데이터베이스에 저장
        userHeight=(EditText)viewGroup.findViewById(R.id.userHeight);
        userWeight=(EditText)viewGroup.findViewById(R.id.userWeight);
        userStateMessage=(EditText)viewGroup.findViewById(R.id.userStateMessage);

        final String height = userHeight.getText().toString().trim();
        final String weight = userWeight.getText().toString().trim();
        final String state = userStateMessage.getText().toString().trim();

        StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                userHeight.setText("");
//                userWeight.setText("");
//                userStateMessage.setText("");
                Toast.makeText(getActivity(), response.toString(), Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> param = new HashMap<String, String>();
                param.put("height",height);
                param.put("weight",weight);
                param.put("state",state);
                return param;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(Objects.requireNonNull(getActivity()));
        queue.add(request);
    }


    public void doTakeAlbumAction(int num) {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, num);
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == USER_PROFILE  )
        {
            InputStream is = null;
            try {
                is = getActivity().getContentResolver().openInputStream(data.getData());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            bm = BitmapFactory.decodeStream(is);
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            userProfile.setImageBitmap(bm);

        }else if(requestCode == MORNING){
            Bundle bundle = getArguments();
            morning.setImageBitmap(bundle.getParcelable("image"));
        }

    }

}




