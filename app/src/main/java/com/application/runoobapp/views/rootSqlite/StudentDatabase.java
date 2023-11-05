package com.application.runoobapp.views.rootSqlite;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


//数据库关联表结构（entity）信息
@Database(entities = {Student.class}, version = 1, exportSchema = false)
public abstract class StudentDatabase extends RoomDatabase {

    private static StudentDatabase database;

    //暴露dao操作数据库对象
    public abstract StudentDao getStudentDao();

    public static synchronized StudentDatabase getDatabaseInstance(Context context) {
        if (database == null) {
            database = Room.databaseBuilder(context.getApplicationContext(), StudentDatabase.class, "student_database")
                    //默认是异步线程操作数据库
                    .build();
        }
        return database;
    }
}
