package com.example.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
public class Profile extends Fragment  {


    private Uri mImageCaptureUri;
    ImageView imageView;
    ViewGroup viewGroup;

    public void doTakeAlbumAction()

    {

        // 앨범 호출

        Intent intent = new Intent(Intent.ACTION_PICK);

        //intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);

        intent.setDataAndType(mImageCaptureUri, android.provider.MediaStore.Images.Media.CONTENT_TYPE);
        intent.putExtra("outputX", 50);
        intent.putExtra("outputY", 50);
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("scale", true);
        intent.putExtra("return-data", true);

        startActivityForResult(intent, 1);

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        mImageCaptureUri = data.getData();
        imageView.setImageURI(mImageCaptureUri);
    }




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.profile, container, false);
        imageView = (ImageView) viewGroup.findViewById(R.id.profile_userPicture);






        imageView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {  DialogInterface.OnClickListener albumListener = new DialogInterface.OnClickListener()

            {

                @Override

                public void onClick(DialogInterface dialog, int which)

                {

                    doTakeAlbumAction();

                }


            };
                new AlertDialog.Builder(getActivity())
                        .setTitle("업로드할 이미지 선택")
                        .setNeutralButton("앨범선택", albumListener)
                        .show();

            }
        });







        return viewGroup;
    }
}

