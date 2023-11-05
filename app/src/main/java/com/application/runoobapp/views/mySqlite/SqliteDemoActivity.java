package com.application.runoobapp.views.mySqlite;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.application.runoobapp.R;
import com.application.runoobapp.views.rootSqlite.Student;
import com.application.runoobapp.views.rootSqlite.manager.DBEngine;

public class SqliteDemoActivity extends AppCompatActivity {
    private final String TAG = SqliteDemoActivity.class.getName();

    private DBEngine dbEngine;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
        this.dbEngine = new DBEngine(this);
    }


    public void createDB(View view) {
        SQLiteOpenHelper helper = MySqliteOpenHelper.getSqLiteOpenHelper(SqliteDemoActivity.this);

        helper.getReadableDatabase();
    }

    public void queryDB(View view) {
        SQLiteOpenHelper helper = MySqliteOpenHelper.getSqLiteOpenHelper(SqliteDemoActivity.this);
        SQLiteDatabase readableDatabase = helper.getReadableDatabase();
        if (readableDatabase.isOpen()) {
            Cursor cursor = readableDatabase.rawQuery("select * from tb_person", null);
            while (cursor.moveToNext()) {
                @SuppressLint("Range")
                int _id = cursor.getInt(cursor.getColumnIndex("_id"));
                @SuppressLint("Range")
                String userName = cursor.getString(cursor.getColumnIndex("name"));
                Log.d(TAG, "queryDB result: " + _id + " " + userName);
            }

            //关闭资源
            cursor.close();
        }
        readableDatabase.close();
    }

    public void insertDB(View view) {
        SQLiteOpenHelper helper = MySqliteOpenHelper.getSqLiteOpenHelper(SqliteDemoActivity.this);
        SQLiteDatabase writableDatabase = helper.getWritableDatabase();
        if (writableDatabase.isOpen()) {
            String sql = "insert into tb_person('name') values('tom')";
            writableDatabase.execSQL(sql);
        }
        writableDatabase.close();
    }

    public void updateDB(View view) {
        SQLiteOpenHelper helper = MySqliteOpenHelper.getSqLiteOpenHelper(SqliteDemoActivity.this);
        SQLiteDatabase writableDatabase = helper.getWritableDatabase();
        if (writableDatabase.isOpen()) {
            String sql = "update tb_person set name = ? where _id = ?";
            writableDatabase.execSQL(sql, new Object[]{"jack", 2});
        }
        writableDatabase.close();
    }

    public void deleteDB(View view) {
        SQLiteOpenHelper helper = MySqliteOpenHelper.getSqLiteOpenHelper(SqliteDemoActivity.this);
        SQLiteDatabase writableDatabase = helper.getWritableDatabase();
        if (writableDatabase.isOpen()) {
            String sql = "delete from tb_person where _id = ?";
            writableDatabase.execSQL(sql, new Object[]{2});
        }
        writableDatabase.close();

    }

    public void queryDataByRoom(View view) {
        this.dbEngine.queryAllStudents();
    }

    public void insertDataByRoom(View view) {
        Student s1 = new Student("mary", 21);
        Student s2 = new Student("pony", 24);
        Student s3 = new Student("steve", 23);
        this.dbEngine.insertStudents(s1, s2, s3);
    }


    public void updateDataByRoom(View view) {
        Student s = new Student("john", 28);
        s.setId(2);
        this.dbEngine.updateStudents(s);
    }

    public void deleteDataByRoom(View view) {
        Student s = new Student("", 0);
        s.setId(2);
        this.dbEngine.deleteStudents(s);
    }

    public void deleteAllDataByRoom(View view) {
        this.dbEngine.deleteAllStudents();
    }
}
