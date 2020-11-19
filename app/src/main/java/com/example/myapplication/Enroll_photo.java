package com.example.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class Enroll_photo extends AppCompatActivity {
    private static final int REQUEST_CODE = 0;
    Button getPhotoButton, enrollButton;
    Uri uri;
    Bitmap bm;
    ImageView imageView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enroll_photo);
        getPhotoButton = (Button)findViewById(R.id.enroll_photo_button);
        enrollButton = (Button)findViewById(R.id.enroll_photo_enroll);
        imageView = (ImageView)findViewById(R.id.enroll_photo_image);
        Intent intent = getIntent();
        Bundle bundle = new Bundle();

        getPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                doTakeAlbumAction();

            }
        });

        enrollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bundle.putParcelable("image",bm);
                intent.putExtras(bundle);
                finish();
            }
        });
    }

    public void doTakeAlbumAction() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,2);

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        InputStream is = null;
        try {
            is =getContentResolver().openInputStream(data.getData());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        bm = BitmapFactory.decodeStream(is);
        try {
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

         imageView.setImageBitmap(bm);


    }
}
