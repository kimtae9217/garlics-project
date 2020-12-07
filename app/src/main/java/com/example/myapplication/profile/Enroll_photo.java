package com.example.myapplication.profile;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;


public class Enroll_photo extends AppCompatActivity {
    private static final int REQUEST_CODE = 0;
    Button getPhotoButton, enrollButton;
    Bitmap bm;
    EditText foodName,foodCal;
    ImageView imageView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enroll_photo);

        getPhotoButton = (Button)findViewById(R.id.enroll_photo_button);
        enrollButton = (Button)findViewById(R.id.enroll_photo_enroll);
        imageView = (ImageView)findViewById(R.id.enroll_photo_image);
        foodName = (EditText)findViewById(R.id.enroll_photo_foodName);
        foodCal = (EditText)findViewById(R.id.enroll_photo_foodCal);



        getPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                doTakeAlbumAction();

            }
        });

        enrollButton.setOnClickListener(new View.OnClickListener() {
            // 등록된 사진 , 입력받은 값 반환
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("picture",BitmapToString(bm));
                intent.putExtra("foodName",foodName.getText().toString());
                intent.putExtra("foodCal",foodCal.getText().toString());
                setResult(RESULT_OK,intent);
                finish();

            }
        });
    }

    // 앨범에 접근
    public void doTakeAlbumAction() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,REQUEST_CODE);

    }

    // 사진 등록
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        InputStream is = null;
        try {
            is = getContentResolver().openInputStream(data.getData());
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

    // 비트맵 형식의 사진을 String으로 변환
    public static String BitmapToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 70, baos);
        byte[] bytes = baos.toByteArray();
        String temp = Base64.encodeToString(bytes, Base64.DEFAULT);
        return temp;
    }
}


