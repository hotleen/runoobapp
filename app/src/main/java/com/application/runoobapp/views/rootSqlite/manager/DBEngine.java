package com.application.runoobapp.views.rootSqlite.manager;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.application.runoobapp.views.rootSqlite.Student;
import com.application.runoobapp.views.rootSqlite.StudentDao;
import com.application.runoobapp.views.rootSqlite.StudentDatabase;

import java.util.List;

public class DBEngine {

    private static final String TAG = DBEngine.class.getSimpleName();
    private final StudentDao studentDao;

    public DBEngine(Context context) {
        StudentDatabase database = StudentDatabase.getDatabaseInstance(context);
        this.studentDao = database.getStudentDao();
    }

    public void insertStudents(Student... students) {
        new InsertAsyncTask(studentDao).execute(students);
    }

    public void updateStudents(Student... students) {
        new UpdateAsyncTask(studentDao).execute(students);
    }

    public void deleteStudents(Student... students) {
        new DeleteAsyncTask(studentDao).execute(students);
    }

    public void deleteAllStudents() {
        new DeleteAllAsyncTask(studentDao).execute();
    }

    public void queryAllStudents() {
        new QueryAllAsyncTask(studentDao).execute();
    }

    private static class InsertAsyncTask extends AsyncTask<Student, Void, Void> {
        private final StudentDao studentDao;

        public InsertAsyncTask(StudentDao studentDao) {
            this.studentDao = studentDao;
        }

        @Override
        protected Void doInBackground(Student... students) {
            studentDao.insertStudent(students);
            return null;
        }
    }

    private static class UpdateAsyncTask extends AsyncTask<Student, Void, Void> {
        private final StudentDao studentDao;

        public UpdateAsyncTask(StudentDao studentDao) {
            this.studentDao = studentDao;
        }

        @Override
        protected Void doInBackground(Student... students) {
            studentDao.updateStudent(students);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<Student, Void, Void> {
        private final StudentDao studentDao;

        public DeleteAsyncTask(StudentDao studentDao) {
            this.studentDao = studentDao;
        }

        @Override
        protected Void doInBackground(Student... students) {
            studentDao.deleteStudent(students);
            return null;
        }
    }

    private static class DeleteAllAsyncTask extends AsyncTask<Void, Void, Void> {
        private final StudentDao studentDao;

        public DeleteAllAsyncTask(StudentDao studentDao) {
            this.studentDao = studentDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            studentDao.deleteStudents();
            return null;
        }
    }
    private static class QueryAllAsyncTask extends AsyncTask<Void, Void, Void> {
        private final StudentDao studentDao;

        public QueryAllAsyncTask(StudentDao studentDao) {
            this.studentDao = studentDao;
        }


        @Override
        protected Void doInBackground(Void... voids) {
            List<Student> students = studentDao.getAllStudent();
            for (Student student : students) {
                Log.d(TAG, "doInBackground: student info: "+student);
            }
            return null;
        }
    }

}
