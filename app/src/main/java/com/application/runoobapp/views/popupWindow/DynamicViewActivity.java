package com.application.runoobapp.views.popupWindow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.application.runoobapp.R;

public class DynamicViewActivity extends AppCompatActivity implements View.OnClickListener {

    private Button BT_Gone;//让布局隐藏
    private Button BT_Visiable;//让布局显示
    private Button BT_Add;//增添布局
    private Button BT_Delete;//删除布局

    private RelativeLayout RL_main;
    private RadioGroup RL_RadioGroup;
    private RelativeLayout RL_InfoTip;
    private LinearLayout LL_test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_view);

        init();//初始化
    }

    private void init() {
        BT_Gone= (Button) findViewById(R.id.button1);
        BT_Visiable= (Button) findViewById(R.id.button2);
        BT_Add= (Button) findViewById(R.id.button3);
        BT_Delete= (Button) findViewById(R.id.button4);

        RL_main=(RelativeLayout)findViewById(R.id.main_layout);
        RL_RadioGroup=(RadioGroup)findViewById(R.id.radio_group);
        RL_InfoTip=(RelativeLayout)findViewById(R.id.info_tip);

        //此处要获取其他xml的控件需要先引入改layout的view(这个linearlayout用于演示添加和删除)
        View view= LayoutInflater.from(this).inflate(R.layout.layout_dynaimc_view,null,false );
        LL_test=(LinearLayout)view.findViewById(R.id.dynamic_container);

        BT_Gone.setOnClickListener(this);
        BT_Visiable.setOnClickListener(this);
        BT_Add.setOnClickListener(this);
        BT_Delete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button1:
                RL_InfoTip.setVisibility(View.GONE);//底部tip设置不可见
                //初始化宽高属性
                RelativeLayout.LayoutParams lp1 = new RelativeLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                lp1.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);//设置置底
                lp1.setMargins(10, 0, 0, 10);//设置margin,此处单位为px
                RL_RadioGroup.setLayoutParams(lp1);//动态改变布局
                break;
            case R.id.button2:
                RL_InfoTip.setVisibility(View.VISIBLE);//底部tip设置可见
                //初始化宽高属性
                RelativeLayout.LayoutParams lp2 = new RelativeLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                lp2.setMargins(10, 0, 0, 10);//设置margin,此处单位为px
                lp2.addRule(RelativeLayout.ABOVE, R.id.info_tip);//设置above，让控件于R.id.info_tip之上
                RL_RadioGroup.setLayoutParams(lp2);//动态改变布局
                break;
            case R.id.button3:
                //初始化宽高属性,此处单位为px
                RelativeLayout.LayoutParams lp3 = new RelativeLayout.LayoutParams(200, 200);
                lp3.addRule(RelativeLayout.BELOW, R.id.button4);//设置below,让控件于R.id.button4之下
                RL_main.addView(LL_test, lp3);//动态改变布局
                LL_test.setVisibility(View.VISIBLE);//此处需要设置布局显示，否则会不显示
                break;
            case R.id.button4:
                RL_main.removeView(LL_test);//动态改变布局
                break;
        }
    }
}
