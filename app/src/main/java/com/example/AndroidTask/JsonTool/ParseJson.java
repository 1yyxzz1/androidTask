package com.example.AndroidTask.JsonTool;


import com.example.AndroidTask.MainFram.HomepageFram.News;
import com.example.AndroidTask.MainFram.SidebarFram.Historys;
import com.google.gson.Gson;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

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
    //    private MultipartFile file;
//    private FeedBackInfo feedBack;
//    private EvaluateInfo Evaluate;
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
    }

    //评价构造函数
//    public ParseJson(String urlname,EvaluateInfo feedBack){
//        this.URLname = urlname;
//        this.feedBack = feedBack;
//    }

    //文件流构造函数
//    public ParseJson(String urlname,MultipartFile feedBack){
//        this.URLname = urlname;
//        this.feedBack = feedBack;
//    }

    //反馈信息构造函数
//    public ParseJson(String urlname,FeedBackInfo file){
//        this.URLname = urlname;
//        this.file = file;
//    }



    public void getJsonFromInternet(){
        switch (URLname){
            case "News": url = NewsURL;break;
            case "Login": url = LoginURL + account + "&password=" + password;break;
            case "Register": url = RegisterURL + account + "&password=" + password;break;
            // case "Image": url = ImageURL;break;
            // case "FeedBack": url = FeedBackURL;break;
            case "Historys": url = HistorysURL + "?account="+account;break;
            //  case "Evaluate": url = EvaluateURL;break;
            case "Process": url = ProcessURL + "?feed_back_id"+feed_back_id;break;
        }
        try {
            HttpURLConnection connection = (HttpURLConnection)new URL(url).openConnection();
            connection.setConnectTimeout(5000);
            connection.setRequestMethod("GET");
            int code = connection.getResponseCode();
            System.out.println("code = " + code);
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
            System.out.println(content);
            JSONObject jsonObject = new JSONObject(content);
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            ArrayList<Historys> historyslist = new ArrayList<Historys>();
            Gson gson = new Gson();
            for(int i = 0;i != jsonArray.length();i++){
                JSONObject js = jsonArray.getJSONObject(i);
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
            System.out.println(content);
            JSONObject jsonObject = new JSONObject(content);
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            ArrayList<News> Newslist = new ArrayList<News>();
            Gson gson = new Gson();
            for(int i = 0;i != jsonArray.length();i++){
                JSONObject js = jsonArray.getJSONObject(i);
                News news = gson.fromJson(String.valueOf(js),News.class);
                Newslist.add(news);
            }
            return  Newslist;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}