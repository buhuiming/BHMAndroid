package com.bhm.sdk.bhmlibrary.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.widget.Toast;

import com.bhm.sdk.bhmlibrary.interfaces.PictureCall;
import com.bhm.sdk.bhmlibrary.result.ActivityResult;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;

import androidx.annotation.NonNull;
import androidx.core.content.FileProvider;
import io.reactivex.functions.Consumer;

import static android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION;
import static android.content.Intent.FLAG_GRANT_WRITE_URI_PERMISSION;


/**
 * Created by bhm on 2018/5/31.
 */

public class CameraGalleryGetPic {

    public static final String FORMAT_JPEG = Bitmap.CompressFormat.JPEG.toString();
    public static final String FORMAT_PNG = Bitmap.CompressFormat.PNG.toString();
    public static final String FORMAT_WEBP = Bitmap.CompressFormat.WEBP.toString();
    private Builder builder;
    private String temporaryPath;

    public CameraGalleryGetPic(@NonNull Builder builder){
        this.builder = builder;
        File file = new File(builder.picPath);
        setCacheFile(file);
    }

    public static Builder newBuilder(@NonNull Activity activity){
        return new Builder(activity);
    }

    /** get picture from camera
     */
    @SuppressLint("CheckResult")
    public void cameraGetPic(final PictureCall call){
        RxPermissions permissions = new RxPermissions(builder.activity);
        permissions.request(Manifest.permission.CAMERA)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if(!aBoolean){
                            Toast.makeText(builder.activity,
                                    "无法获取权限，请在设置中授权", Toast.LENGTH_SHORT).show();
                        }else{
                            initPicPath();
                            Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            camera.putExtra(MediaStore.EXTRA_OUTPUT,
                                    getUriForFile(builder.activity, new File(temporaryPath)));
                            new ActivityResult(builder.activity).startForResult(camera, new ActivityResult.Callback() {
                                @Override
                                public void onActivityResult(int resultCode, Intent data) {
                                    if(!new File(temporaryPath).exists()){
                                        call.result("");
                                        return;
                                    }
                                    if(builder.isCrop){
                                        cutImageCamera(call, getUriForFile(builder.activity, new File(temporaryPath)));
                                    }else if(null != call){
                                        call.result(temporaryPath);
                                    }
                                }
                            });
                        }
                    }
                });
    }

    /** get picture from gallery
     */
    @SuppressLint("CheckResult")
    public void galleryGetPic(final PictureCall call) {
        RxPermissions permissions = new RxPermissions(builder.activity);
        permissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (!aBoolean) {
                            Toast.makeText(builder.activity,
                                    "无法获取权限，请在设置中授权", Toast.LENGTH_SHORT).show();
                        } else {
                            initPicPath();
                            Intent album = new Intent(Intent.ACTION_PICK,
                                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            new ActivityResult(builder.activity).startForResult(album, new ActivityResult.Callback() {
                                @Override
                                public void onActivityResult(int resultCode, Intent data) {
                                    if(null == data){
                                        call.result("");
                                        return;
                                    }
                                    if(builder.isCrop){
                                        cutImageGallery(call, data.getData());
                                    }else if(null != call){
                                        //获取照片路径
                                        try {
                                            String[] filePathColumn = {MediaStore.Audio.Media.DATA};
                                            Cursor cursor = builder.activity.getContentResolver().query(
                                                    data.getData(), filePathColumn, null, null, null);
                                            cursor.moveToFirst();
                                            String photoPath = cursor.getString(cursor.getColumnIndex(filePathColumn[0]));
                                            cursor.close();
                                            call.result(photoPath);
                                        }catch (Exception e){

                                        }
                                    }
                                }
                            });
                        }
                    }
                });
    }

    public static class Builder{
        private Activity activity;
        private String format;
        private String picPath;
        private int outputX;
        private int outputY;
        private boolean isCrop;

        public Builder(@NonNull Activity activity){
            this.activity = activity;
        }

        public Builder setPicPath(String picPath){
            this.picPath = picPath;
            return this;
        }

        public Builder setScale(int outputX, int outputY){
            this.outputX = outputX;
            this.outputY = outputY;
            return this;
        }

        public Builder setFormat(String format){
            this.format = format;
            return this;
        }

        public Builder isCrop(boolean isCrop){
            this.isCrop = isCrop;
            return this;
        }

        public CameraGalleryGetPic build(){
            return new CameraGalleryGetPic(this);
        }
    }

    /** 通过file文件，拿到uri地址
     * @param context
     * @param file
     * @return
     */
    private Uri getUriForFile(Context context, File file) {
        if (context == null || file == null) {
            throw new NullPointerException();
        }
        Uri uri;
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            String authority = getAuthority(context);
            uri = FileProvider.getUriForFile(context.getApplicationContext(), authority, file);
        } else {
            uri = Uri.fromFile(file);
        }
        return uri;
    }

    private String getAuthority(Context context){
        try {
            ApplicationInfo appInfo = context.getPackageManager().
                    getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            return appInfo.metaData.getString("authorities");
        }catch (Exception e){

        }
        return "";
    }

    private void initPicPath() {
        String dir =  builder.picPath;
        String filename = System.currentTimeMillis() + "." +
                ((TextUtils.isEmpty(builder.format) ? FORMAT_PNG: builder.format)).toLowerCase();
        File dirs = new File(dir);
        if (!dirs.exists()) {
            dirs.mkdirs();
        }
        temporaryPath = dir + filename;
    }

    private void setCacheFile(File file){
        if(file.isFile()) {
            file.delete();
            return;
        }
        if(file.isDirectory()){
            File[] childFiles = file.listFiles();
            if (childFiles == null || childFiles.length == 0) {
                file.delete();
                return;
            }else if(childFiles.length >= 50){//超过50张 把所有图片都删除
                for (File childFile : childFiles) {
                    setCacheFile(childFile);
                }
                file.delete();
            }
        }
        file.mkdirs();
    }

    /** 相册裁剪
     * @param call
     * @param uri
     */
    private void cutImageGallery(final PictureCall call, Uri uri) {
        File f = new File(temporaryPath);
        if (f.exists()) {
            f.delete();
        }
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // crop为true是设置在开启的intent中设置显示的view可以剪裁
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);// aspectX aspectY 是宽高的比例
        intent.putExtra("aspectY", 1);
        // outputX,outputY 是剪裁图片的宽高
        intent.putExtra("outputX", builder.outputX == 0 ? 300 : builder.outputX);
        intent.putExtra("outputY", builder.outputY == 0 ? 300 : builder.outputY);
        intent.putExtra("return-data", false);
        intent.putExtra("scale", true);
        Uri newUri;
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.Q) {
            //android 11以上，将文件创建在相册，因为相册不能调用app的私有地址资源
            ContentResolver contentResolver = builder.activity.getContentResolver();
            ContentValues values = new ContentValues();
            values.put(MediaStore.MediaColumns.DISPLAY_NAME, String.valueOf(System.currentTimeMillis()));
            values.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg");
            values.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DCIM);
            newUri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        }else {
            newUri = Uri.fromFile(f);
        }
        intent.putExtra(MediaStore.EXTRA_OUTPUT, newUri);
        intent.putExtra("outputFormat", TextUtils.isEmpty(builder.format)
                ? FORMAT_JPEG : builder.format);
        intent.putExtra("noFaceDetection", true);
        new ActivityResult(builder.activity).startForResult(intent, new ActivityResult.Callback() {
            @Override
            public void onActivityResult(int resultCode, Intent data) {
                if(null != call){
                    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.Q) {
                        try {
                            String[] filePathColumn = {MediaStore.Audio.Media.DATA};
                            Cursor cursor = builder.activity.getContentResolver().query(
                                    newUri, filePathColumn, null, null, null);
                            cursor.moveToFirst();
                            String photoPath = cursor.getString(cursor.getColumnIndex(filePathColumn[0]));
                            cursor.close();
                            call.result(photoPath);
                        }catch (Exception e){

                        }
                    }else {
                        call.result(temporaryPath);
                    }
                }
            }
        });
    }

    /**相机裁剪
     * @param call
     * @param uri
     */
    private void cutImageCamera(final PictureCall call, Uri uri) {
        if (uri == null) {
            return;
        }
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.addFlags(FLAG_GRANT_READ_URI_PERMISSION);
            intent.addFlags(FLAG_GRANT_WRITE_URI_PERMISSION);
        }
        // 设置裁剪
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);// aspectX aspectY 是宽高的比例
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", builder.outputX == 0 ? 250 : builder.outputX);
        intent.putExtra("outputY", builder.outputY == 0 ? 250 : builder.outputY);
        intent.putExtra("return-data", false);
        intent.putExtra("noFaceDetection", false);//去除默认的人脸识别，否则和剪裁匡重叠
        intent.putExtra("outputFormat", TextUtils.isEmpty(builder.format)
                ? FORMAT_JPEG : builder.format);
        new ActivityResult(builder.activity).startForResult(intent, new ActivityResult.Callback() {
            @Override
            public void onActivityResult(int resultCode, Intent data) {
                if(null != call){
                    call.result(temporaryPath);
                }
            }
        });
    }
}
