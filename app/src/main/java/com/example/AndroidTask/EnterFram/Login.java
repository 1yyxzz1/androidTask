package com.example.AndroidTask.EnterFram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.provider.SyncStateContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bumptech.glide.request.ErrorRequestCoordinator;
import com.example.AndroidTask.MainFram.EnterMainFram;
import com.example.AndroidTask.Database.SPSave;
import com.example.cq_1014_task.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.LinkedHashMap;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;



public class Login extends AppCompatActivity {
    private Button mBtnZc;//点开注册界面
    private Button mBtnDenglu;
    private EditText et_account; //账号输入框
    private EditText et_password; //密码输入框
    private String code;//返回值
    //private Map<String,String> Saved_information; //获取保存在XML中的信息
    /*private String Saved_account;
    private String Saved_password;*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        SharedPreferences sp=getApplicationContext().getSharedPreferences("data", Context.MODE_PRIVATE);
        String account= sp.getString("account",null);
        String password= sp.getString("password",null);
        //String connectStr=String.format("http://49.235.134.191:8080/user/login?account="+account+"&password="+password);

        //Map<String,String> Saved_information=new HashMap<String,String>(SPSave.getUserInfo(getApplicationContext()));
        //if (Saved_information.get("account")!=null&&Saved_information.get("password")!=null) {
        if (account!=null&&password!=null) {
            Toast.makeText(getApplicationContext(),"自动登录成功",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Login.this, EnterMainFram.class);
            startActivity(intent);
        }

        //Toast.makeText(getApplicationContext(),account,Toast.LENGTH_SHORT).show();
        setContentView(R.layout.activity_login);
        mBtnZc=(Button) findViewById(R.id.btn_woyaozhuce);
        mBtnDenglu=(Button) findViewById(R.id.btn_denglu);
        et_account=(EditText) findViewById(R.id.et_1);
        et_password=(EditText) findViewById(R.id.et_2);
        setListeners();

    }

    private void setListeners(){
        OnClick onClick=new OnClick();
        mBtnZc.setOnClickListener(onClick);
        mBtnDenglu.setOnClickListener(onClick);
    }

    private void sendRequsetWithOKHttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String account =et_account.getText().toString().trim();
                    String password=et_password.getText().toString();
                    OkHttpClient client = new OkHttpClient();
                    String connectStr = String.format("http://49.235.134.191:8080/user/login?account=" + account + "&password=" + password);
                    Request request = new Request.Builder().url(connectStr).build();
                    Response response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        Log.e("Message----", response.body().string());
                    } else {
                        throw new IOException("Unexpected code " + response);
                    }
                    //Toast.makeText(getApplicationContext(),"返回值："+response,Toast.LENGTH_SHORT).show();

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
/*
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what== )
        }
    };*/

    private void showResponse(final String response){
        try {
            JSONObject jsonObject=new JSONObject(response);
            String getcode= jsonObject.getString("code");
            code=getcode;
            Log.e("code",code);
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    private class OnClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent intent=null;
            switch (v.getId()){
                case R.id.btn_woyaozhuce:
                    intent=new Intent(Login.this,RegisterActivity.class);
                    break;
                case R.id.btn_denglu:
                    String account =et_account.getText().toString().trim();
                    String password=et_password.getText().toString();
                    if (TextUtils.isEmpty(account)){
                        Toast.makeText(getApplicationContext(),"请输入账号",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (TextUtils.isEmpty(password)){
                        Toast.makeText(getApplicationContext(),"请输入密码",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    sendRequsetWithOKHttp();

                    //if(responseText.equals("200")){
                   // Toast.makeText(getApplicationContext(),"返回值："+code,Toast.LENGTH_SHORT).show();
                    //}
                    /*
                    String connectStr=String.format("http://49.235.134.191:8080/user/login?account="+account+"&password="+password);
                    try{
                       URL url = new URL(connectStr);
                       HttpURLConnection conn= (HttpURLConnection) url.openConnection();
                       conn.setRequestMethod("GET");//设置请求方法
                       conn.setConnectTimeout(5000);//设置超时时长
                       int responseCode = conn.getResponseCode();
                       Toast.makeText(getApplicationContext(),"请求码："+responseCode,Toast.LENGTH_SHORT).show();
                       if(responseCode==200){
                           InputStream is=conn.getInputStream();
                           String str=is.toString();
                           JSONObject jsonObject=new JSONObject(is.toString());
                           intent=new Intent(Login.this, EnterMainFram.class);
                           //String code=jsonObject.getString("code");
                           //Toast.makeText(getApplicationContext(),code,Toast.LENGTH_SHORT).show();
                           Toast.makeText(getApplicationContext(),"登录成功",Toast.LENGTH_SHORT).show();
                       }
                       conn.disconnect();//关闭连接
                    }
                    catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }*/
                    intent=new Intent(Login.this, EnterMainFram.class);
                    boolean isSaveSuccess = SPSave.saveUserInfo(getApplicationContext(),account,password);
                    if (isSaveSuccess){
                        Toast.makeText(getApplicationContext(),"保存成功",Toast.LENGTH_SHORT).show();
                    }
                    else Toast.makeText(getApplicationContext(),"保存失败",Toast.LENGTH_SHORT).show();
                    break;
            }
            finish();
            startActivity(intent);
        }
    }

}