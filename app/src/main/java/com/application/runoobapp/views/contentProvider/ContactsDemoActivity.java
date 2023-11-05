package com.application.runoobapp.views.contentProvider;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.application.runoobapp.R;

public class ContactsDemoActivity extends AppCompatActivity {
    private static final String TAG = ContactsDemoActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
    }

    public void getMobileContacts(View view) {
        readContacts();
    }

    private void readContacts() {
        try (Cursor cursor = getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null,
                null,
                null,
                null
        )) {
            //查询联系人数据,使用了getContentResolver().query方法来查询系统的联系人的数据
            //CONTENT_URI就是一个封装好的Uri，是已经解析过得常量
            //对cursor进行遍历，取出姓名和电话号码
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    //获取联系人姓名
                    @SuppressLint("Range") String contactName = cursor.getString(cursor.getColumnIndex(
                            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
                    ));
                    //获取联系人手机号
                    @SuppressLint("Range") String phoneNum = cursor.getString(cursor.getColumnIndex(
                            ContactsContract.CommonDataKinds.Phone.NUMBER
                    ));

                    Log.i(TAG, "getMobileContacts msg: " + " contact name: " + contactName
                            + " phone number: " + phoneNum);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 权限的验证及处理，相关方法
     */
    public void requestContactPermission(View v) {

        int hasWriteContactsPermission = checkSelfPermission(
                android.Manifest.permission.READ_CONTACTS);
        if (hasWriteContactsPermission !=
                PackageManager.PERMISSION_GRANTED) {
            int REQUEST_CODE_ASK_PERMISSIONS = 123;
            requestPermissions(new String[]
                            {Manifest.permission.READ_CONTACTS},
                    REQUEST_CODE_ASK_PERMISSIONS);

        }


    }
}
