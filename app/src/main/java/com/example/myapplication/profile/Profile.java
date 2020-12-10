package com.example.myapplication.profile;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.PreferenceManager;
import com.example.myapplication.R;
import com.example.myapplication.SharedViewModel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Profile extends Fragment {

    private SharedViewModel sharedViewModel1;

    private static final String URL = "http://61.245.248.173/garlic/insertUserDB.php";
    public static final int USER_PROFILE = 0;
    public static final int MORNING = 1;
    public static final int LUNCH = 2;
    public static final int DINNER = 3;
    public static final int SNACK = 4;
    ImageView userProfile, morning, lunch, dinner, snack;
    ViewGroup viewGroup;
    Bitmap bm;
    TextView userExerciseTime;
    EditText userHeight, userWeight, userStateMessage,userName;
    TextView morningFoodName,morningFoodCal,lunchFoodName,lunchFoodCal ,
    dinnerFoodName, dinnerFoodCal, snackFoodName, snackFoodCal,usedCal;
    Button sbmt;
    String test;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.profile, container, false);
        userProfile = (ImageView) viewGroup.findViewById(R.id.profile_userPicture);
        morning = (ImageView) viewGroup.findViewById(R.id.profile_morning_image);
        lunch = (ImageView) viewGroup.findViewById(R.id.profile_lunch_image);
        dinner = (ImageView) viewGroup.findViewById(R.id.profile_dinner_Image);
        snack = (ImageView) viewGroup.findViewById(R.id.profile_snack_image);
        morningFoodName = (TextView)viewGroup.findViewById(R.id.profile_morning_foodName);
        morningFoodCal = (TextView)viewGroup.findViewById(R.id.profile_morning_foodCal);
        lunchFoodName = (TextView)viewGroup.findViewById(R.id.profile_lunch_foodName);
        lunchFoodCal = (TextView)viewGroup.findViewById(R.id.profile_lunch_foodCal);
        dinnerFoodName = (TextView)viewGroup.findViewById(R.id.profile_dinner_foodName);
        dinnerFoodCal = (TextView)viewGroup.findViewById(R.id.profile_dinner_foodCal);
        snackFoodName = (TextView)viewGroup.findViewById(R.id.profile_snack_foodName);
        snackFoodCal = (TextView)viewGroup.findViewById(R.id.profile_snack_foodCal);
        userExerciseTime = (TextView)viewGroup.findViewById(R.id.userExerciseTime);
        userHeight = (EditText) viewGroup.findViewById(R.id.userHeight);
        userWeight = (EditText) viewGroup.findViewById(R.id.userWeight);
        userStateMessage = (EditText) viewGroup.findViewById(R.id.userStateMessage);
        usedCal = (TextView)viewGroup.findViewById(R.id.usedCal);
        userName = (EditText)viewGroup.findViewById(R.id.profile_userName);







        sbmt = (Button) viewGroup.findViewById(R.id.submit); //키, 몸무게, 상태메세지 전송 버튼


        sharedViewModel1 = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);


        sharedViewModel1.getLiveData().observe(getViewLifecycleOwner(), new Observer<String>() {
                    @Override
                    public void onChanged(String s) {
                        userExerciseTime.setText(s);
                        usedCal.setText(PreferenceManager.getString(getContext(),"USED KCAL"));

                    }
                });



        sbmt.setOnClickListener(new View.OnClickListener() { // 저장 버튼 누를시 발생 이벤트
            @Override
            public void onClick(View view) {
                insertdata();

                PreferenceManager.setString(getContext(),"name",userName.getText().toString());





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
                startActivityForResult(intent, LUNCH);
            }
        });

        dinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Enroll_photo.class);
                startActivityForResult(intent, DINNER);
            }
        });

        snack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Enroll_photo.class);
                startActivityForResult(intent, SNACK);
            }
        });
        return viewGroup;
    }



    private void insertdata() { // 키, 몸무게, 상태 데이터베이스에 저장


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
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<String, String>();
                param.put("height", height);
                param.put("weight", weight);
                param.put("state", state);
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

    // String으로 변환했던 비트맵을 다시 비트맵으로
    public static Bitmap StringToBitmap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case USER_PROFILE:
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
                break;

            case MORNING:
                 bm =StringToBitmap(data.getStringExtra("picture"));
                 morningFoodName.setText(data.getStringExtra("foodName"));
                 morningFoodCal.setText(data.getStringExtra("foodCal"));
                 morning.setImageBitmap(bm);
                 break;

            case LUNCH:
                bm =StringToBitmap(data.getStringExtra("picture"));
                lunchFoodName.setText(data.getStringExtra("foodName"));
                lunchFoodCal.setText(data.getStringExtra("foodCal"));
                lunch.setImageBitmap(bm);
                break;

            case DINNER:
                bm =StringToBitmap(data.getStringExtra("picture"));
                dinnerFoodName.setText(data.getStringExtra("foodName"));
                dinnerFoodCal.setText(data.getStringExtra("foodCal"));
                dinner.setImageBitmap(bm);
                break;

            case SNACK:
                bm =StringToBitmap(data.getStringExtra("picture"));
                snackFoodName.setText(data.getStringExtra("foodName"));
                snackFoodCal.setText(data.getStringExtra("foodCal"));
                snack.setImageBitmap(bm);
                break;
        }
    }
}







