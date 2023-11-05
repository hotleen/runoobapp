package com.application.runoobapp.views.rootSqlite;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface StudentDao {

    //可变参数
    @Insert
    void insertStudent(Student... students);

    @Update
    void updateStudent(Student... students);

    //删除单个
    @Delete
    void deleteStudent(Student... students);

    //删除所有
    @Query("DELETE FROM Student")
    void deleteStudents();

    @Query("SELECT * FROM Student ORDER BY id DESC")
    List<Student> getAllStudent();

}
