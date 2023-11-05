package com.application.runoobapp.views.mySqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class MySqliteOpenHelper extends SQLiteOpenHelper {

    private static SQLiteOpenHelper sqLiteOpenHelper;
    private final String TAG = MySqliteOpenHelper.class.getName();

    /**
     * 单例模式
     *
     * @param context 上下文对象
     * @return sqliteHelper单例对象
     */
    public static synchronized SQLiteOpenHelper getSqLiteOpenHelper(Context context) {
        if (sqLiteOpenHelper == null) {
            sqLiteOpenHelper = new MySqliteOpenHelper(context, "config.db", null, 1);
        }
        return sqLiteOpenHelper;
    }

    private MySqliteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //创建表，数据库第一次初始化调用
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.e(TAG, "onCreate: create  person table!");
        //主键 primary key规范 _id integer 其余字段 text
        String sql = "create table tb_person(_id integer primary key autoincrement,name text)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
