package com.example.hw_registrtion_login_android;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;//這要自己補上唷

import com.google.gson.Gson;


public class RegistrationLoginFirstFragment extends Fragment {
    private final static String TAG = "TAG_MainFragment";
    private ImageView ivUiDe; //宣告fragment_registration_login_first.xml上所有元件
    private Activity activity;
    private EditText etName , etPassword;
    private Button btLogin , btRegistration , btForgetPassword;
    private CheckBox cbRemeberPassword;
    private Gson gson;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
        gson = new Gson();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        activity.setTitle("Registration & Login");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registration_login_first, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ivUiDe = view.findViewById(R.id.ivUiDe);//取得各元件位置資訊
        etName = view.findViewById(R.id.etName);
        etPassword = view.findViewById(R.id.etPassword);
        btLogin = view.findViewById(R.id.btLogin);
        btRegistration = view.findViewById(R.id.btRegistration);
        btForgetPassword = view.findViewById(R.id.btForgetPassword);
        cbRemeberPassword = view.findViewById(R.id.cbRemeberPassword);

        btLogin.setOnClickListener(new View.OnClickListener() {//加設監聽器
            @Override
            public void onClick(View v) {
                String username = etName.getText().toString();
                String userpasswowd = etPassword.getText().toString();
                User user = new User(username , userpasswowd);

                if (networkConnected()) {
                    String url = Common.URL + "LoginServlet";
                    String outStr = gson.toJson(user);
                    CommonTask loginTask = new CommonTask(url, outStr);
                    boolean isUserValid = false;
                    try {
                        String jsonIn = loginTask.execute().get();
                        JsonObject jsonObject = gson.fromJson(jsonIn, JsonObject.class);
                        isUserValid = jsonObject.get("result").getAsBoolean();
                    } catch (Exception e) {
                        Log.e(TAG, e.getMessage());
                    }

                    if (isUserValid) {
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("user", user);
                    } else {
                        new AlertDialog.Builder(activity)//彈跳視窗
                                .setTitle("帳號或密碼輸入錯誤")
                                .setMessage("請輸入正確的帳號或密碼...\n要是不爽可以選擇離開本系統\n   慢走不送!!～")
                                .setPositiveButton("離開系統", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        activity.finish();//結束本頁
                                    }
                                })
                                .setNegativeButton("重新輸入帳號密碼", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();//返回登入頁
                                    }
                                })
                                .show();
                    }
                }
            }
        });


        btRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //cbRemeberPassword
    }

    // 檢查是否有網路連線
    private boolean networkConnected() {
        ConnectivityManager conManager =
                (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = conManager != null ? conManager.getActiveNetworkInfo() : null;
        return networkInfo != null && networkInfo.isConnected();
    }
}
