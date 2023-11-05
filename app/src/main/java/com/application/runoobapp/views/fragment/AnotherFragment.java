package com.application.runoobapp.views.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.application.runoobapp.R;


public class AnotherFragment extends Fragment {

    private View root;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (root==null){
            root = inflater.inflate(R.layout.fragment_another, container, false);
        }
        // Inflate the layout for this fragment
        return root;
    }
}