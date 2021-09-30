package com.tanishq.optionsapp.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.tanishq.optionsapp.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class HomeActivity extends AppCompatActivity {
    Button action;
    public static int FILE_CODE= 2;
    public static int GALLERY_CODE= 3;
    TextView textView;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        action = findViewById(R.id.action);
        textView = findViewById(R.id.textView);
        imageView = findViewById(R.id.imageView);
        action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertScreen cdd = new AlertScreen(HomeActivity.this);
                cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                cdd.show();

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 2:
                if (resultCode==RESULT_OK){
                    String path = data.getData().getPath();
                    Log.d("mymy", "onActivityResult:" + data);
                    textView.setText(path);


                }
                break;

            case 3:
                if(resultCode==RESULT_OK){
                    Uri selectedImageUri = data.getData();
                    Log.d("pathpath", "onActivityResult: " + data);
                    String picturePath = getPath(getApplicationContext(), selectedImageUri);
                    String name = picturePath.substring(picturePath.lastIndexOf("/")+1);
                    textView.setText(name);
                    Log.d("Picture Path",name);

                }



        }
    }
    public static String getPath(Context context, Uri uri ) {
        String result = null;
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = context.getContentResolver( ).query( uri, proj, null, null, null );
        if(cursor != null){
            if ( cursor.moveToFirst( ) ) {
                int column_index = cursor.getColumnIndexOrThrow( proj[0] );
                result = cursor.getString( column_index );
            }
            cursor.close();
        }
        if(result == null) {
            result = "Not found";
        }
        return result;
    }


}