package com.example.AndroidTask.JsonTool;


import android.database.Cursor;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.CursorAdapter;
import android.widget.Toast;

import com.example.AndroidTask.MainFram.Check_scheduleFram.Processes;
import com.example.AndroidTask.MainFram.EvaluateFram.Evaluate;
import com.example.AndroidTask.MainFram.HomepageFram.News;
import com.example.AndroidTask.MainFram.SidebarFram.Historys;
import com.example.AndroidTask.MainFram.TakePhotoFram.FeedBack;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class ParseJson {
    private static String NewsURL = "http://49.235.134.191:8080/news/get";//新闻列表
    private static String LoginURL = "http://49.235.134.191:8080/user/login";//登录
    private static String RegisterURL = "http://49.235.134.191:8080/user/save";//注册
    private static String ImageURL = "http://49.235.134.191:8080/file/image/upload";//上传图片
    private static String FeedBackURL = "http://49.235.134.191:8080/feedback/save";//随手拍信息上传
    private static String HistorysURL = "http://49.235.134.191:8080/feedback/get";//历史记录
    private static String EvaluateURL = "http://49.235.134.191:8080/feedback/evaluate";//评价
    private static String ProcessURL = "http://49.235.134.191:8080/feedback/process";//进度

    private String URLname;
    private String url;
    private String content;
    private String account;
    private String password;
    //private MultipartFile file;
    private FeedBack feedBack;
    private Evaluate evaluate;
    private int feed_back_id;

    //历史记录构造函数
    public ParseJson(String urlname,String account){
        this.URLname = urlname;
        this.account = account;
    }

    //登录、注册构造函数
    public ParseJson(String urlname,String account,String password){
        this.URLname = urlname;
        this.account = account;
        this.password = password;
    }

    //进度构造函数
    public ParseJson(String urlname,int feed_back_id){
        this.URLname = urlname;
        this.feed_back_id = feed_back_id;
        System.out.println(feed_back_id);
    }

    //评价构造函数
    public ParseJson(String urlname, Evaluate evaluate){
        this.URLname = urlname;
        this.evaluate = evaluate;
    }

    //新闻列表构造函数
    public ParseJson(String urlname){
        this.URLname = urlname;
    }

    //文件流构造函数
//    public ParseJson(String urlname,MultipartFile feedBack){
//        this.URLname = urlname;
//        this.feedBack = feedBack;
//    }

    //反馈信息构造函数
    public ParseJson(String urlname,FeedBack feedBack){
        this.URLname = urlname;
        this.feedBack = feedBack;
    }
    //获取图片路径
    public String getImagePath(){
        String path=null;
        String[] fpColumn={MediaStore.MediaColumns.DATA};

        /*Cursor cursor=get*/
        return "http://49.235.134.191:8080/images/2021-10-29/761f7daddd8a4f61b04f3780dbf18a27.jpg";
    }
    //post请求方式向服务器提交数据
    public int postJsonToInternet(){
        try{
            String data;
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX").create();
            switch (URLname){
                case "Evaluate": url = EvaluateURL;data = gson.toJson(evaluate);break;
                case "FeedBack": url = FeedBackURL;data = gson.toJson(feedBack);break;
                default:data = "";break;
            }
            HttpURLConnection conn = (HttpURLConnection)new URL(url).openConnection();
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(5000);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);
            OutputStream os = conn.getOutputStream();
            os.write(data.getBytes());

            int code = conn.getResponseCode();//获取请求码
           // System.out.println("评价提交结果,Code="+code);
            return code;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void getJsonFromInternet(){
        switch (URLname){
            case "News": url = NewsURL;break;
            case "Login": url = LoginURL + "?account=" + account + "&password=" + password;break;
            case "Register": url = RegisterURL + "?account=" + account + "&password=" + password;break;
            // case "Image": url = ImageURL;break;
            // case "FeedBack": url = FeedBackURL;break;
            case "Historys": url = HistorysURL + "?account="+account;break;
            case "Processes": url = ProcessURL + "?feed_back_id="+feed_back_id;break;
        }
        try {
            HttpURLConnection connection = (HttpURLConnection)new URL(url).openConnection();
            connection.setConnectTimeout(5000);
            connection.setRequestMethod("GET");
            int code = connection.getResponseCode();
            System.out.println("code = " + code+"数据流获取成功");
            if(connection.getResponseCode()==HttpURLConnection.HTTP_OK){
                InputStream inputStream = connection.getInputStream();//获取流
                BufferedReader in=new BufferedReader(new InputStreamReader(inputStream));
                String line=null;
                StringBuffer sb=new StringBuffer();
                while ((line = in.readLine()) != null) {
                    sb.append(line);
                }
                content = sb.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //获取历史记录
    public ArrayList<Historys> ParseJsontoHistorys(){
        try {
            System.out.println("Content="+content);
            JSONObject jsonObject = new JSONObject(content);
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            ArrayList<Historys> historyslist = new ArrayList<Historys>();
            Gson gson = new Gson();
            for(int i = 0;i != jsonArray.length();i++){
                JSONObject js = jsonArray.getJSONObject(i);
                //System.out.println(String.valueOf(js));
                Historys historys = gson.fromJson(String.valueOf(js),Historys.class);
                historyslist.add(historys);
            }
            return  historyslist;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    //获取新闻列表
    public ArrayList<News> ParseJsonToNews(){
        try {
            System.out.println("新闻列表：Content="+content);
            JSONObject jsonObject = new JSONObject(content);
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            ArrayList<News> Newslist = new ArrayList<News>();
            Gson gson = new Gson();
            System.out.println("JsonArray长度："+jsonArray.length());
            for(int i = 0;i != jsonArray.length();i++){
                JSONObject js = jsonArray.getJSONObject(i);
                System.out.println(String.valueOf(js));
                News news = gson.fromJson(String.valueOf(js),News.class);
                Newslist.add(news);
            }
            return  Newslist;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    //获取进度列表
    public ArrayList<Processes> ParseJsonToProcesses(){
        try {
            System.out.println(content);
            JSONObject jsonObject = new JSONObject(content);
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            ArrayList<Processes> Processlist = new ArrayList<Processes>();
            Gson gson = new Gson();
            for(int i = 0;i != jsonArray.length();i++){
                JSONObject js = jsonArray.getJSONObject(i);
                Processes process = gson.fromJson(String.valueOf(js),Processes.class);
                Processlist.add(process);
            }
            return  Processlist;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
