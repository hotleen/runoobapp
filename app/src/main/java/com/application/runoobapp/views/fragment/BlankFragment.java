package com.application.runoobapp.views.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.application.runoobapp.R;


import android.app.Activity;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;

import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment {

    private View root;
    private AnotherFragment anotherFragment;
    private FragmentManager manager;
    private MessageTransfer transfer;


    /**
     * 利用bundle 实现 activity到fragment传参
     * TODO: 利用fragment构造方法也可实现传参，待验证
     * @param title 传递的参数
     * @return fragment对象
     */
    public static BlankFragment newInstance(String title) {
        BlankFragment blankFragment = new BlankFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        blankFragment.setArguments(bundle);
        return blankFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (root == null) {
            root = inflater.inflate(R.layout.fragment_blank, container, false);
        }
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView tv = root.findViewById(R.id.fg_tv);
        if (getArguments() != null) {
            //取值
            tv.setText(getArguments().getString("title"));
        }
        Button jumpButton = view.findViewById(R.id.btn_changeFragment);
        jumpButton.setOnClickListener(v -> {
            if (anotherFragment == null) {
                anotherFragment = new AnotherFragment();
            }
            Fragment f = getActivity().getSupportFragmentManager().findFragmentByTag("blank");
            manager = getActivity().getSupportFragmentManager();
            if (f != null) {
                manager.beginTransaction().hide(f).add(R.id.fg_container,anotherFragment)
                        .addToBackStack(null).commitAllowingStateLoss();
            }else{
                //replace返回会触发onCreateView，丢失之前fragment状态
                manager.beginTransaction().replace(R.id.fg_container, anotherFragment)
                        .addToBackStack(null).commitAllowingStateLoss();
            }
        });

        Button resetButton = view.findViewById(R.id.btn_resetText);
        resetButton.setOnClickListener(v -> tv.setText(R.string.BUTTON_SET_TEXT));

        Button transferButton = view.findViewById(R.id.btn_transfer);
        transferButton.setOnClickListener(v-> transfer.onClick(getString(R.string.FRAGMENT_MESSAGE_TEXT)));

        //获取fragment 绑定的activity TODO: best practice?
        if (getActivity() != null) {
            Activity activity = getActivity();
        }
    }

    //fragment依附到activity触发attach事件
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            transfer = (MessageTransfer) context;
        }catch (ClassCastException e){
            throw new ClassCastException("activity class must implement transfer interface!");
        }
    }
}