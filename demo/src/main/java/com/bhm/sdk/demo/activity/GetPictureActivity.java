package com.bhm.sdk.demo.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bhm.sdk.bhmlibrary.interfaces.PictureCall;
import com.bhm.sdk.bhmlibrary.utils.CameraGalleryGetPic;
import com.bhm.sdk.bhmlibrary.views.TitleBar;
import com.bhm.sdk.demo.R;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/5/31.
 */

public class GetPictureActivity extends AppCompatActivity {

    @BindView(R.id.titleBar)
    protected TitleBar titleBar;
    @BindView(R.id.btn_camera)
    protected Button btn_camera;
    @BindView(R.id.btn_gallery)
    protected Button btn_gallery;
    @BindView(R.id.imageView)
    protected ImageView imageView;

    private CameraGalleryGetPic getPic;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_pic);
        ButterKnife.bind(this);
        titleBar.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        String path = Environment.getExternalStorageDirectory().getAbsolutePath()
                + File.separator + "CPictureActivity" + "/";

        getPic = CameraGalleryGetPic.newBuilder(this)
                .setPicPath(path)
                .isCrop(false)//是否剪切图片，默认为false
                .setScale(320, 320)//isCrop(true)有效
                .setFormat(CameraGalleryGetPic.FORMAT_PNG)
                .build();
    }


    @OnClick({R.id.btn_camera, R.id.btn_gallery})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_camera:
                getPic.cameraGetPic();
                break;
            case R.id.btn_gallery:
                getPic.galleryGetPic();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getPic.call(requestCode, resultCode, data, new PictureCall() {
            @Override
            public void result(String path) {
                Bitmap bitmap = BitmapFactory.decodeFile(path);
                if(null != bitmap){
                    imageView.setImageBitmap(bitmap);
                }
            }
        });
    }
}
