package com.application.runoobapp.util;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;

import androidx.annotation.RequiresApi;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;

public class FileUtils {

    private static FileUtils instance;
    private final static String TAG = FileUtils.class.getSimpleName();

    public static FileUtils getInstance() {
        if (instance == null) {
            instance = new FileUtils();
        }
        return instance;
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public boolean copyPrivateFileToDownloads(Context context, File file, String fileName,
                                              String mimeType, String relativePath) {
        boolean result = false;
        if (!file.exists()) {
            return false;
        }

        ContentValues values = new ContentValues();
        values.put(MediaStore.Files.FileColumns.DISPLAY_NAME, fileName);
        values.put(MediaStore.Files.FileColumns.MIME_TYPE, mimeType);
        values.put(MediaStore.Files.FileColumns.RELATIVE_PATH, relativePath);

        ContentResolver resolver = context.getContentResolver();
        Uri fileUri = resolver.insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, values);

        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = new FileInputStream(file);
            if (fileUri != null) {
                outputStream = resolver.openOutputStream(fileUri);
            }
            if (outputStream != null) {
                byte[] buffer = new byte[1024];
                int len = 0;
                while ((len = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, len);
                }
                outputStream.flush();
                result = true;
            }
        } catch (Exception e) {
            Log.i(TAG, "copyPrivateFileToDownloads: err: " + e.getMessage());
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (Exception e) {
                Log.i(TAG, "copyPrivateFileToDownloads: close error " + e.getMessage());
            }
        }
        return result;
    }


    /**
     * 通过文件类型得到相应文件的集合
     **/
    @RequiresApi(api = Build.VERSION_CODES.Q)
    public void listFiles(Context context) {
        // 扫描files文件库
        Cursor cursor = null;
        try {
            cursor = context.getContentResolver().query(MediaStore.Downloads.EXTERNAL_CONTENT_URI, new String[]{"_id", "_data", "_size"}, null, null, null);
            int dataindex = cursor.getColumnIndex(MediaStore.Files.FileColumns.DATA);

            while (cursor.moveToNext()) {
                String path = cursor.getString(dataindex);
                Log.i(TAG, "getFilesByType: path " + path);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public String encodeFileToBase64(Context context, Uri uri) {
        InputStream inputStream = null;
        String base64Result;
        try {
            inputStream = context.getContentResolver().openInputStream(uri);
            byte[] bytes = new byte[inputStream.available()];
            int len = inputStream.read(bytes);
            base64Result = Base64.encodeToString(bytes, 0, len, Base64.DEFAULT);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return base64Result;
    }

    public void readAssetsFile(Context context) {
        AssetManager manager = context.getAssets();
        InputStream inputStream = null;
        try {
            inputStream = manager.open("router.xml");
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(inputStream, "UTF-8");

            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG) {
                    String tagName = parser.getName();
                    if (!TextUtils.isEmpty(tagName)&&TextUtils.equals(tagName, "activity")){
                        Log.i(TAG, "readAssetsFile: tagName: " + tagName);
                        String activityName = parser.getAttributeValue(0);
                        Log.i(TAG, "readAssetsFile: activityName: " + activityName);
                        String className = "com.application.runoobapp" + activityName;
                        Log.i(TAG, "readAssetsFile: className: " + className);

                        Class clazz = Class.forName(className);
                        if (clazz == null) {
                            return;
                        }
                        Method method = clazz.getMethod("start", Context.class);
                        if (method == null) {
                            return;
                        }
                        method.invoke(null, context);
                    }

                    // 处理 XML 标签
                }
                eventType = parser.next();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }
}
