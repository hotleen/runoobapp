package com.application.runoobapp.util;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DownloadUtils {

    private static DownloadUtils instance;

    private final OkHttpClient httpClient;

    private final static String TAG = DownloadUtils.class.getSimpleName();

    private DownloadUtils() {
        httpClient = new OkHttpClient();
    }

    public static DownloadUtils getInstance() {
        if (instance == null) {
            instance = new DownloadUtils();
        }
        return instance;
    }

    public void downloadFile(String url, String fileSaveDir, DownloadListener listener) {
        Request request = new Request.Builder().url(url).build();
        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                listener.onDownloadFailed(e.getMessage());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                InputStream inputStream = null;
                OutputStream outputStream = null;
                byte[] bytes = new byte[1024];
                int len = 0;
                try {
                    inputStream = response.body().byteStream();
                    long total = response.body().contentLength();
                    File file = getFileFromUrl(fileSaveDir, url);
                    if (file == null) {
                        listener.onDownloadFailed("null file!");
                    }
                    outputStream = new FileOutputStream(file);
                    long sum = 0;
                    while ((len = inputStream.read(bytes)) != -1) {
                        outputStream.write(bytes, 0, len);
                        sum += len;
                        int progress = (int) (sum * 1.0f / total * 100);
                        listener.onDownloading(progress);
                    }
                    outputStream.flush();
                    listener.onDownloadSuccess(null);
                } catch (Exception e) {
                    listener.onDownloadFailed(e.getMessage());
                } finally {
                    try {
                        if (inputStream!=null) {
                            inputStream.close();
                        }
                        if (outputStream!=null) {
                            outputStream.close();
                        }
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private File getFileFromUrl(String fileSaveDir, String url) {

        String fileName = "";
        try {
            File parentDir = new File(fileSaveDir);
            if (!parentDir.exists()) {
                parentDir.mkdir();
            }
            fileName = url.substring(url.lastIndexOf("/") + 1);
            return new File(parentDir, fileName);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public interface DownloadListener {
        void onDownloadSuccess(Uri uri);

        void onDownloading(int progress);

        void onDownloadFailed(String errMsg);
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public void downloadFileToDownloads(Context context, String url, String relativePath, String fileName, DownloadListener listener) {
        Request request = new Request.Builder().url(url).build();
        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                listener.onDownloadFailed(e.getMessage());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                InputStream inputStream = null;
                OutputStream outputStream = null;
                byte[] bytes = new byte[1024];
                int len = 0;
                try {

                    String contentType = response.header("contentType","application/pdf");
                    Log.i(TAG, "onResponse: contentType: "+contentType);
                    ContentValues values = new ContentValues();
                    values.put(MediaStore.Files.FileColumns.DISPLAY_NAME, fileName);
                    values.put(MediaStore.Files.FileColumns.MIME_TYPE, contentType);
                    values.put(MediaStore.Files.FileColumns.RELATIVE_PATH, relativePath);

                    ContentResolver resolver = context.getContentResolver();
                    Uri fileUri = resolver.insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, values);
                    if (fileUri != null) {
                        outputStream = resolver.openOutputStream(fileUri);
                    }

                    inputStream = response.body().byteStream();
                    long total = response.body().contentLength();
                    long sum = 0;
                    while ((len = inputStream.read(bytes)) != -1) {
                        outputStream.write(bytes, 0, len);
                        sum += len;
                        int progress = (int) (sum * 1.0f / total * 100);
                        listener.onDownloading(progress);
                    }
                    outputStream.flush();
                    listener.onDownloadSuccess(fileUri);
                } catch (Exception e) {
                    listener.onDownloadFailed(e.getMessage());
                } finally {
                    try {
                        if (inputStream!=null) {
                            inputStream.close();
                        }
                        if (outputStream!=null) {
                            outputStream.close();
                        }
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

}
