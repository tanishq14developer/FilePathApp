package com.tanishq.optionsapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.TextView;

import com.tanishq.optionsapp.R;

public class AlertScreen extends Dialog implements View.OnClickListener  {

    public Activity c;
    public TextView takePicture,selectFile,selectFromGallery,cancel;
    public static int FILE_CODE= 2;
    public static int GALLERY_CODE= 3;



    public AlertScreen(Activity a) {

        super(a);
        this.c = a;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_screen);
        takePicture = findViewById(R.id.takePicture);
        takePicture.setOnClickListener(this);
        selectFile = findViewById(R.id.selectFile);
        selectFile.setOnClickListener(this);
        selectFromGallery = findViewById(R.id.selectFromGallery);
        selectFromGallery.setOnClickListener(this);
        cancel = findViewById(R.id.cancel);
        cancel.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.takePicture:

                break;
            case R.id.selectFile:
                Intent fileIntent = new Intent(Intent.ACTION_GET_CONTENT);
                fileIntent.addCategory(Intent.CATEGORY_OPENABLE);
                fileIntent.setType("application/pdf");
                c.startActivityForResult(fileIntent,FILE_CODE);


                break;
            case R.id.selectFromGallery:
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                c.startActivityForResult(photoPickerIntent, GALLERY_CODE);

            case R.id.cancel:
                dismiss();
                break;
            default:
                break;

        }
        dismiss();

    }
}