package com.bhm.sdk.demo.activity;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bhm.sdk.bhmlibrary.interfaces.PictureCall;
import com.bhm.sdk.bhmlibrary.utils.CameraGalleryGetPic;
import com.bhm.sdk.bhmlibrary.views.TitleBar;
import com.bhm.sdk.demo.R;
import com.bumptech.glide.Glide;

import java.io.File;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
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

//        String path = Environment.getExternalStorageDirectory().getAbsolutePath()
//                + File.separator + "CPictureActivity" + "/";
        String path = getExternalFilesDir(
                Environment.DIRECTORY_DOWNLOADS).getAbsolutePath()
                + File.separator;

        getPic = CameraGalleryGetPic.newBuilder(this)
                .setPicPath(path)
                .isCrop(true)//是否剪切图片，默认为false
                .setScale(320, 320)//isCrop(true)有效
                .setFormat(CameraGalleryGetPic.FORMAT_PNG)
                .build();
    }


    @OnClick({R.id.btn_camera, R.id.btn_gallery})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_camera:
                getPic.cameraGetPic(getPictureCall());
                break;
            case R.id.btn_gallery:
                getPic.galleryGetPic(getPictureCall());
                break;
        }
    }

    private PictureCall getPictureCall(){
        return new PictureCall() {
            @Override
            public void result(String path) {
                Glide.with(GetPictureActivity.this).load(path).into(imageView);
            }
        };
    }
}
