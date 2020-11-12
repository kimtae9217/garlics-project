package com.example.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class Profile extends Fragment  {

    private static final int REQUEST_CODE = 0;
    ImageView userProfile, morning;
    ViewGroup viewGroup;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.profile, container, false);
        userProfile = (ImageView) viewGroup.findViewById(R.id.profile_userPicture);
        morning = (ImageView) viewGroup.findViewById(R.id.profile_morning_image);



        userProfile.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                {
                    doTakeAlbumAction();
                }

            }

        });
        morning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),Enroll_photo.class );
                startActivity(intent);
            }
        });

        return viewGroup;
    }

    public void doTakeAlbumAction() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, REQUEST_CODE);

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        InputStream is = null;
        try {
            is = getActivity().getContentResolver().openInputStream(data.getData());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Bitmap bm = BitmapFactory.decodeStream(is);
        try {
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        userProfile.setImageBitmap(bm);

    }


}
