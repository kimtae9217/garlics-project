package com.example.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class Profile extends Fragment {

    public static final int USER_PROFILE = 0;
    public static final int MORNING = 0;
    ImageView userProfile, morning, lunch, dinner, snack;
    ViewGroup viewGroup;
    Bitmap bm;
    TextView tx;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.profile, container, false);
        userProfile = (ImageView) viewGroup.findViewById(R.id.profile_userPicture);
        morning = (ImageView) viewGroup.findViewById(R.id.profile_morning_image);
        lunch = (ImageView) viewGroup.findViewById(R.id.profile_lunch_image);
        dinner = (ImageView) viewGroup.findViewById(R.id.profile_dinner_Image);
        snack = (ImageView) viewGroup.findViewById(R.id.profile_snack_image);



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




