package com.lhd.aes;

import android.Manifest;
import android.graphics.Bitmap;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lhd.mutils.MUtils;

import java.io.File;
import java.util.List;


public class MainActivity extends AppCompatActivity{

    Button bt;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE},
                200);
        bt=findViewById(R.id.bt);
        img=findViewById(R.id.img);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MUtils.fileUtil.mkdirs(MUtils.fileUtil.getExternalStorageDirectory()+File.separator+"123");
                Bitmap bitmap= MUtils.imageUtil.getBitmpa(
                        MUtils.fileUtil.getExternalStorageDirectory()+ File.separator+"123"+File.separator+"123.png",
                        1);
                img.setImageBitmap(bitmap);
                MUtils.imageUtil.saveBitmapFile(bitmap,
                        MUtils.fileUtil.getExternalStorageDirectory()+File.separator+"123456"+File.separator+"456.jpg");
            }
        });

    }


}
