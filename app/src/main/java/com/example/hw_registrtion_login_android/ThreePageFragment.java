package com.example.hw_registrtion_login_android;


import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class ThreePageFragment extends Fragment {
    private Activity activity;
    private EditText etEnterNewPassword , etConfirmNewPassword , etEnterConfirmationCode ;
    private Button btGetVerificationCode , btConfirm;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        activity.setTitle("Registration");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_three_page, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        etEnterNewPassword = view.findViewById(R.id.etEnterNewPassword);
        etConfirmNewPassword = view.findViewById(R.id.etConfirmNewPassword);
        etEnterConfirmationCode = view.findViewById(R.id.etEnterConfirmationCode);
        btGetVerificationCode = view.findViewById(R.id.btGetVerificationCode);
        btConfirm = view.findViewById(R.id.btConfirm);


    }
}
