package com.example.bibekshrestha.myfaceswap;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.IOException;

public class Work extends AppCompatActivity {


    ImageButton camera1, gallery1, gallery2, camera2;
    Button swap;
    ImageView imageView1, imageView2;

    final int opencamera1=1;
    final int opencamera2=2;
    final int requestgallery1=3;
    final int requestgallery2=4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work);



        camera1 = (ImageButton)findViewById(R.id.camera1);
        camera1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                accessCameraFirst();

            }
        });


        camera2 = (ImageButton)findViewById(R.id.camera2);
        camera2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                accessCameraSecond();

            }
        });


        gallery1 = (ImageButton)findViewById(R.id.gallery1);
        gallery1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                accessGalleryFirst();
            }
        });

        gallery2 = (ImageButton)findViewById(R.id.gallery2);
        gallery2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                accessGallerySecond();
            }
        });

        imageView1 = (ImageView)findViewById(R.id.image1);
        imageView2 = (ImageView)findViewById(R.id.image2);


        swap = (Button)findViewById(R.id.button);
        swap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent swapintent = new Intent(getApplicationContext(), SwapActivity.class);
                startActivity(swapintent);
            }
        });

    }


    public void accessCameraFirst(){
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, opencamera1);
    }

    public void accessCameraSecond(){
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, opencamera2);
    }

    public void accessGalleryFirst(){
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, requestgallery1);
    }

    public void accessGallerySecond(){
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, requestgallery2);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch(requestCode) {
            case opencamera1:
                if (requestCode == opencamera1 && resultCode == RESULT_OK) {
                    Bundle extras = data.getExtras();
                    Bitmap imageBitmap = (Bitmap) extras.get("data");
                    imageView1.setImageBitmap(imageBitmap);
                }

            case opencamera2:
                if (requestCode == opencamera2 && resultCode == RESULT_OK) {
                    Bundle extras = data.getExtras();
                    Bitmap imageBitmap = (Bitmap) extras.get("data");
                    imageView2.setImageBitmap(imageBitmap);
                }
            case requestgallery1:
                if(requestCode==requestgallery1 && resultCode==RESULT_OK){

                    Uri uri = data.getData();
                    try{
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                        imageView1.setImageBitmap(bitmap);
                    }catch (IOException e){
                        e.printStackTrace();
                    }

                }
            case requestgallery2:
                if(requestCode==requestgallery2 && resultCode==RESULT_OK){

                    Uri uri = data.getData();
                    try{
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                        imageView2.setImageBitmap(bitmap);
                    }catch (IOException e){
                        e.printStackTrace();
                    }

                }

        }
    }
}
