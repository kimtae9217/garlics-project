package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class SetPhoto extends Activity {
    ImageView userProfile, morning, lunch, dinner, snack;
    TextView tx;
    Bitmap bm;
    Uri uri;


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        Intent intent = getIntent();

        if (intent.getIntExtra("profile", 1) == 1) {
            doTakeAlbumAction(1);
            intent.putExtra("image",uri);
            setResult(RESULT_OK,intent);
            finish();
        }

    }


    public void doTakeAlbumAction(int num) {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, num);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 ) {


            InputStream is = null;
            try { is = getContentResolver().openInputStream(data.getData());
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
            uri = data.getData();

            } else if (requestCode == 2) {

            }
        }
    }




