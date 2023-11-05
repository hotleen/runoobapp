package com.application.runoobapp.views.housecountdown;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.application.runoobapp.R;

import java.time.LocalDate;
import java.time.Period;

public class HouseCountDownActivity extends AppCompatActivity {

    private static final String TAG = "HOUSE_COUNTDOWN";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_housecountdown);
        TextView textView = findViewById(R.id.countdown_text);
        LocalDate currentDate = LocalDate.now();
        LocalDate targetDate = LocalDate.of(2023,10,1);
        Period p = Period.between(currentDate,targetDate);
        long days = targetDate.toEpochDay()-currentDate.toEpochDay();
        String text = "距离交房还有："+p.getYears()+"年"+p.getMonths()+"月"+p.getDays()+"天, 总计"+days+"天";
        textView.setText(text);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: activity restart!");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: activity resume!");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: activity start!");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: activity pause!");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: activity stop!");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: activity destroy!");
    }

}
