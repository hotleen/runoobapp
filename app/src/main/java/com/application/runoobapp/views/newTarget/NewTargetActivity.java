package com.application.runoobapp.views.newTarget;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.application.runoobapp.R;
import com.application.runoobapp.base.action.ToastAction;
import com.application.runoobapp.util.DownloadUtils;
import com.application.runoobapp.util.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

public class NewTargetActivity extends AppCompatActivity implements ToastAction, EasyPermissions.PermissionCallbacks {

    private static final String TAG = NewTargetActivity.class.getSimpleName();

    private static final int READ_PHONE_PERMISSION_REQUEST_CODE = 102;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newtarget);
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, NewTargetActivity.class);
        context.startActivity(intent);
    }


    public void saveImage(View view) {
        // 需要创建的图片对象
        BitmapDrawable bitmapDrawable = (BitmapDrawable) getResources().getDrawable(R.drawable.river, null);
        Bitmap bitmap = bitmapDrawable.getBitmap();

        ContentValues contentValues = new ContentValues();
        // 指定文件保存的文件夹名称
        // Environment.DIRECTORY_PICTURES 值为 Pictures
        // Environment.DIRECTORY_DCIM 值为 DCIM
        // Environment.DIRECTORY_MOVIES 值为 Movies
        // Environment.DIRECTORY_MUSIC 值为 Music
        // Environment.DIRECTORY_DOWNLOADS 值为 Download
        // 如果想获取上述文件夹的真实地址可以通过这样的方式 Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath() 获取，他返回的值类似 /storage/emulated/0/Pictures
        contentValues.put(MediaStore.Images.ImageColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES + "/runnoob/");
        // 指定文件名
        contentValues.put(MediaStore.Images.ImageColumns.DISPLAY_NAME, "river");
        // 指定文件的 mime（比如 image/jpeg, application/vnd.android.package-archive 之类的）
        contentValues.put(MediaStore.Images.ImageColumns.MIME_TYPE, "image/jpeg");
        contentValues.put(MediaStore.Images.ImageColumns.WIDTH, bitmap.getWidth());
        contentValues.put(MediaStore.Images.ImageColumns.HEIGHT, bitmap.getHeight());

        ContentResolver contentResolver = this.getContentResolver();
        // 通过 ContentResolver 在指定的公共目录下按照指定的 ContentValues 创建文件，会返回文件的 content uri（类似这样的地址 content://media/external/images/media/102）
        // 可以通过 MediaStore 保存文件的公共目录有：
        // MediaStore.Images.Media.EXTERNAL_CONTENT_URI - 图片目录，只能保存 mime 为图片类型的文件
        //     图片目录包括 Environment.DIRECTORY_PICTURES, Environment.DIRECTORY_DCIM 文件夹
        // MediaStore.Audio.Media.EXTERNAL_CONTENT_URI - 音频目录，只能保存 mime 为音频类型的文件
        //     音频目录包括 Environment.DIRECTORY_MUSIC, Environment.DIRECTORY_RINGTONES 等等文件夹
        // MediaStore.Video.Media.EXTERNAL_CONTENT_URI - 视频目录，只能保存 mime 为视频类型的文件
        //     视频目录包括 DIRECTORY_MOVIES, Environment.DIRECTORY_PICTURES, Environment.DIRECTORY_DCIM 文件夹
        // MediaStore.Downloads.EXTERNAL_CONTENT_URI - 下载目录，可以保存任意类型的文件
        //     下载目录包括 Environment.DIRECTORY_DOWNLOADS 文件夹
        Uri uri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
        Environment.getExternalStorageDirectory();
        if (uri == null) {
            Log.i(TAG, "创建文件失败");
        } else {
            Log.i(TAG, "创建文件成功：" + uri.toString());
        }

        // 写入图片数据
        OutputStream outputStream = null;
        try {
            outputStream = contentResolver.openOutputStream(uri);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 85, outputStream);
        } catch (Exception ex) {
            Log.d(TAG, "写入数据失败：" + ex.toString());
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.flush();
                    outputStream.close();
                }
            } catch (Exception ex) {
                Log.i(TAG, ex.getMessage());
            }
        }
    }


    public void requestStoragePermission(View view) {
        if (EasyPermissions.hasPermissions(this, Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Toast.makeText(this, "APP已经获取权限", Toast.LENGTH_LONG).show();
        } else {
            EasyPermissions.requestPermissions(this,
                    "APP申请存储读取、写入权限",
                    100,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
    }

    public void saveFile(View view) {
        String content = "fileContent";
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
            Uri contentUri = MediaStore.Downloads.EXTERNAL_CONTENT_URI;
            //创建contentValues对象，准备掺入数据
            ContentValues contentValues = new ContentValues();
            contentValues.put(MediaStore.Downloads.MIME_TYPE, "text/plain");//文件格式
            contentValues.put(MediaStore.Downloads.DATE_TAKEN, System.currentTimeMillis());
            contentValues.put(MediaStore.Downloads.DISPLAY_NAME, "file_save");
            Uri fileUri = this.getContentResolver().insert(contentUri, contentValues);
            if (fileUri != null) {
                OutputStream outputStream = null;
                try {
                    outputStream = this.getContentResolver().openOutputStream(fileUri);
                    if (outputStream!=null){
                        outputStream.write(content.getBytes());
                        outputStream.flush();
                    }
                } catch (Exception e) {
                    Log.i(TAG, "saveFile: err "+e.getMessage());
                    this.getContentResolver().delete(fileUri,null,null);
                } finally {
                    if (outputStream!=null){
                        try {
                            outputStream.close();
                        } catch (IOException e) {
                            Log.i(TAG, "saveFile: close err "+e.getMessage());
                        }
                    }
                }
            }
        }
    }

    /**
     * url: https://filesamples.com/samples/document/pdf/sample3.pdf
     * @param view
     */
    public void downloadFile(View view) {
        String url = "https://filesamples.com/samples/document/pdf/sample3.pdf";
        String saveDir = this.getCacheDir() + "/runoob/";
        Log.i(TAG, "downloadFile: dir: "+saveDir);
        DownloadUtils.getInstance().downloadFile(url, saveDir, new DownloadUtils.DownloadListener() {
            @Override
            public void onDownloadSuccess(Uri uri) {
                NewTargetActivity.this.runOnUiThread(() -> {
                    Toast.makeText(NewTargetActivity.this, "download success", Toast.LENGTH_LONG).show();
                });
            }

            @Override
            public void onDownloading(int progress) {

            }

            @Override
            public void onDownloadFailed(String errMsg) {
                Log.e(TAG, "onDownloadFailed: err"+errMsg);
                NewTargetActivity.this.runOnUiThread(() -> {
                    Toast.makeText(NewTargetActivity.this, "download failed", Toast.LENGTH_LONG).show();
                });
            }
        });
    }

    public void judgeFileStatus(View view) {
        listFile(this.getCacheDir().getPath());
        listFile(this.getCacheDir() + "/runoob");
    }

    private void listFile(String dir) {
        Log.i(TAG, "listFile: dir: "+dir);
        try {
            File saveFiles = new File(dir);
            File[] files = saveFiles.listFiles();
            for( File file : files) {
                if (file.isFile()) {
                    Log.i(TAG, "judgeFileStatus: files "+file);
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "judgeFileStatus: err"+e.getMessage());
        }
    }

    public void copyFileToDownloads(View view) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            String filePath = this.getCacheDir() + "/runoob/" + "sample3.pdf";
            File file = new File(filePath);
            FileUtils.getInstance().copyPrivateFileToDownloads(this, file,"sample3.pdf",
                    "application/pdf","Download/runoob");
        }
    }

    public void copyFileWithoutMime(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            String filePath = this.getCacheDir() + "/runoob/" + "sample3.pdf";
            File file = new File(filePath);
            FileUtils.getInstance().copyPrivateFileToDownloads(this, file,"sample3.pdf",
                    "","Download/runoob");
        }
    }

    public void downloadFileDirectly(View view) {
        // dir: /storage/emulated/0/Android/data/com.application.runoobapp/files/runoob
        String dir = this.getExternalFilesDir("/runoob").getAbsolutePath();
        Log.i(TAG, "copyFile: dir: "+dir);
        // dir1: /storage/emulated/0/Android/data/com.application.runoobapp/files/runoob
        String dir1 = this.getExternalFilesDir("runoob").getAbsolutePath();
        Log.i(TAG, "copyFile: dir1: "+dir1);
        String url = "https://filesamples.com/samples/document/pdf/sample1.pdf";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            DownloadUtils.getInstance().downloadFileToDownloads(this, url, "Download/runoob", "sample1.pdf", new DownloadUtils.DownloadListener() {
                @Override
                public void onDownloadSuccess(Uri uri) {
                    Log.i(TAG, "onDownloadSuccess: ");
                    NewTargetActivity.this.runOnUiThread(() -> {
                        Toast.makeText(NewTargetActivity.this, "download success", Toast.LENGTH_LONG).show();
                    });
                    String result = FileUtils.getInstance().encodeFileToBase64(NewTargetActivity.this, uri);
                    Log.i(TAG, "onDownloadSuccess: base64: "+ result);
                }

                @Override
                public void onDownloading(int progress) {

                }

                @Override
                public void onDownloadFailed(String errMsg) {
                    Log.i(TAG, "onDownloadFailed: err "+errMsg);
                    NewTargetActivity.this.runOnUiThread(() -> {
                        Toast.makeText(NewTargetActivity.this, "download failed", Toast.LENGTH_LONG).show();
                    });
                }
            });
        }
    }

    public void queryFileInDownloads(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            FileUtils.getInstance().listFiles(this);
        }
    }

    public void getReadPhonePermission(View view) {
        String[] perms = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_PHONE_STATE};
        if (EasyPermissions.hasPermissions(this, perms)) {
            toast("已经获取了Read_Phone_State权限");
        } else {
            toast("没有Read_Phone_State权限");
        }
    }

    //TODO: 这里还有问题
    public void requestReadPhonePermission(View view) {
        String[] perms = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_PHONE_STATE};
        if (!EasyPermissions.hasPermissions(this, perms)) {
            EasyPermissions.requestPermissions(this, "请求获取读取设备权限",READ_PHONE_PERMISSION_REQUEST_CODE,
                     perms);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // 将结果传递给EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        if (requestCode == READ_PHONE_PERMISSION_REQUEST_CODE){
            toast("用户同意了Read_Phone_State权限");
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)){
            toast("用户拒绝了Read_Phone_State权限");
        }
    }

}
