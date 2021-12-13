package com.example.AndroidTask.EnterFram;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.AndroidTask.JsonTool.ParseJson;
import com.example.cq_1014_task.R;

import java.util.Observer;

import javax.xml.transform.Result;

public class RegisterActivity extends AppCompatActivity {
    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }*/
    private EditText etAccount;
    private EditText etPassword;
    private EditText etPasswordAgain;
    private Button register;

    private String account;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();

        //initHandlerEvent();
    }

    private void initView() {
        etAccount = findViewById(R.id.et_account);
        etPassword = findViewById(R.id.et_password);
        etPasswordAgain = findViewById(R.id.password_again);
        register = findViewById(R.id.bt_register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
    }

    private void register() {
        account = etAccount.getText().toString().trim();
        password = etPassword.getText().toString().trim();
        String passwordAgain = etPasswordAgain.getText().toString().trim();

        if (TextUtils.isEmpty(account)) {
            Toast.makeText(RegisterActivity.this, "请输入账号", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(RegisterActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(passwordAgain)) {
            Toast.makeText(RegisterActivity.this, "请再次输入密码", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!password.equals(passwordAgain)) {
            Toast.makeText(RegisterActivity.this, "两次密码不一致", Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            ParseJson parseJson = new ParseJson("Register",account,password);
            parseJson.getJsonFromInternet();
            Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
        }
        //UserHttp userHttp = new UserHttp();
        //userHttp.register(account, password);
    }
    /*
    //处理注册返回结果
    private void initHandlerEvent() {
        LiveEventBus.get(Constant.USER_REGISTER, Result.class)
                .observe(this, new Observer<Result>() {
                    @Override
                    public void onChanged(Result result) {
                        if (ResultCode.SUCCESS.code == result.getCode()) {
                            AccountUtils.saveUserToSP(RegisterActivity.this, account, password);
                            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                            startActivity(intent);
                            Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(RegisterActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });*/
    }
