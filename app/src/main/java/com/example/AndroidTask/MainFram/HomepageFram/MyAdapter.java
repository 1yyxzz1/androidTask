package com.example.AndroidTask.MainFram.HomepageFram;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cq_1014_task.R;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.net.ssl.HttpsURLConnection;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.LinearViewHolder> {
    private Context mContext;
    private OnItemClickListener mListener;
    //private String content;
    private int num=0;
    //private News[] news=new News[100];
    private ArrayList<News> datalist = new ArrayList<News>();
    private ArrayList<Bitmap> bitmaps = new ArrayList<Bitmap>();
    private Bitmap bmp;
    private MyAdapter.LinearViewHolder mainholder;

    public MyAdapter(Context context, ArrayList<News> datalist,ArrayList<Bitmap> bitmaps,OnItemClickListener listener){
        this.mContext=context;
        this.mListener=listener;
//        content=getConnect();
//        getNews();
//        for(int i = 0;i != 100;i++){
//            news[i] = new News();
//        }
        this.datalist = datalist;
        for (News n: this.datalist
             ) {
            System.out.println(n.toString());
        }
        this.bitmaps = bitmaps;

    }

    @Override
    public MyAdapter.LinearViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new LinearViewHolder(LayoutInflater.from(mContext).inflate(R.layout.homepage_fragment_recycleitem,parent,false));//传入view，即每一个item长什么样的布局
    }

    @Override
    public void onBindViewHolder(MyAdapter.LinearViewHolder holder, final int position) {
        mainholder=holder;
        holder.title.setText(datalist.get(position).getTitle());
        holder.time.setText(datalist.get(position).getPublishTime());//设置时间
        holder.content.setText(datalist.get(position).getDesc());//设置内容
        holder.imag.setImageBitmap(bitmaps.get(position));
        //        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");//注意月份是MM
//        Date date=null;
//        try {
//            date = simpleDateFormat.parse(datalist.get(position).getPublishTime());
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }





        //Bitmap bmp=getBitmap(news[position].getImageUrL());
        //holder.imag.setImageBitmap(bmp);
        //setImage(holder,datalist.get(position).getImageUrL());//设置图片

        //Uri imguri = Uri.parse((String) news[position].getImageUrL());
        //holder.imag.setImageURI(imguri);
        //loadImageFromNetwork(news[position].getImageUrL());
        //holder.imag.setImageBitmap(bmp);
        //holder.imag.setImageResource(R.drawable.news_picture);

        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onClick(position);
            }
        });
    }

    public void setImage(final MyAdapter.LinearViewHolder holder, final String str){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    URL url=new URL(str);
                    Log.d("url", "== imageUrl: " + url);
                    //InputStream inputStream = (InputStream) url.getContent();
                    //final Drawable drawable = Drawable.createFromStream(inputStream, "123.jpg");
                    URLConnection conn = url.openConnection();
                    conn.connect();
                    InputStream is = conn.getInputStream();
                    BitmapFactory.Options options=new BitmapFactory.Options();
                    options.inSampleSize = 10;
                    bmp = BitmapFactory.decodeStream(is);//bitmap压缩，使其可以被载入
                    Matrix matrix=new Matrix();
                    matrix.setScale(0.2f,0.25f);
                    bmp=Bitmap.createBitmap(bmp,0,0,bmp.getWidth(),bmp.getHeight(),matrix,true);
                    is.close();
                    // 该线程的结果，再返回，操作ＵＩ线程
                    holder.imag.post(new Runnable() {
                        @Override
                        public void run() {
                            holder.imag.setImageBitmap(bmp);
                        }
                    });
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
    /*
    private Drawable loadImageFromNetwork(String str){
        Drawable drawable = null;
        try{
            //judge if has picture locate or not according to filename
            drawable = Drawable.createFromStream(new URL(str).openStream(), "123.jpg");
        }catch(IOException e){
            Log.d("test",e.getMessage());
        }
        if(drawable == null){
            Log.d("test","null drawable");
        }else{
            Log.d("test","not null drawable");

        }
        return drawable;
    }


    private void setImage(final Drawable d, final MyAdapter.LinearViewHolder holder){
        new Thread(new Runnable(){
            Drawable drawable = d;
            @Override
            public void run(){
                //post() is quite important,update pictures in UI main thread
                holder.imag.setImageDrawable(drawable);
                System.out.println(""+drawable);
            }
        }).start();  //线程启动
    }*/

    @Override
    public int getItemCount() {
        return datalist.size();
    }


    class LinearViewHolder extends RecyclerView.ViewHolder{

        private TextView title,time,content;
        private ImageView imag;

        public LinearViewHolder(View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.tv_ti);
            time=itemView.findViewById(R.id.tv_time);
            content=itemView.findViewById(R.id.tv_content);
            imag=itemView.findViewById(R.id.newspic);
        }
    }

    public interface OnItemClickListener{
        void onClick(int pos);
    }

    private static String getConnect(){
        String connectStr = String.format("http://49.235.134.191:8080/news/get");
        try {
            URL url = new URL(connectStr);
            HttpURLConnection connect=(HttpURLConnection)url.openConnection();
            connect.setRequestMethod("GET");//设置请求方法
            connect.setConnectTimeout(5000);//设置超时时长
            int responsecode=connect.getResponseCode();
            System.out.println("code="+responsecode);
            if(responsecode== HttpsURLConnection.HTTP_OK){
                InputStream input=connect.getInputStream();
                BufferedReader in=new BufferedReader(new InputStreamReader(input));
                String line=null;
                StringBuffer sb=new StringBuffer();
                //Log.e("", ""+connect.getResponseCode());
                while ((line = in.readLine()) != null) {
                    sb.append(line);
                }
                return sb.toString();
            }
            else {
                System.out.println(responsecode);
                return " ";
            }
        }catch (Exception e){
            Log.e("e:", String.valueOf(e));
            return e.toString();
        }
    }
    /*
    private void getNum(){
        new Thread(){
            @Override
            public void run(){
                content=getConnect();
                try{
                    JSONObject result_json=new JSONObject(content);
                    JSONArray data= result_json.getJSONArray("data");
                    num=data.length();
                    System.out.println("num:"+num);
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }.start();
    }*/
    /*
    public Bitmap getBitmap(String s)
    {
        URL url;
        HttpURLConnection connection=null;
        Bitmap bitmap=null;
        try {
            url = new URL(s);
            connection=(HttpURLConnection)url.openConnection();
            connection.setConnectTimeout(6000); //超时设置
            connection.setDoInput(true);
            connection.setUseCaches(false); //设置不使用缓存
            InputStream inputStream=connection.getInputStream();
            bitmap=BitmapFactory.decodeStream(inputStream);
            inputStream.close();
            System.out.println("finish getbitmap");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }*/

//    private void getNews(){
//        new Thread(){
//            @Override
//            public void run(){
//                content=getConnect();
//                try{
//                    JSONObject result_json=new JSONObject(content);
//                    JSONArray data= result_json.getJSONArray("data");
//                    num=data.length();
//
//                    for (int i=0;i<num;i++){
//                        //news[i]=new News();
//                        JSONObject object= data.getJSONObject(i);
//                        news[i].setImageUrL(object.getString("imageUrl"));
//                        news[i].setId(object.getInt("id"));
//                        news[i].setTitle(object.getString("title"));
//                        news[i].setDesc(object.getString("desc"));
//                        news[i].setPublishAccount(object.getString("publishAccount"));
//                        news[i].setPublishTime(object.getString("publishTime"));
//                        //Bitmap bmp=getBitmap(news[i].getImageUrL());
//
//                        System.out.println("标题："+news[i].getTitle());
//                    }
////                    //System.out.println(content);
////                    JSONObject jsonObject = new JSONObject(content);
////                    JSONArray jsonArray = jsonObject.getJSONArray("data");
////                    //ArrayList<News> Newslist = new ArrayList<News>();
////                    Gson gson = new Gson();
////                    for(int i = 0;i != jsonArray.length();i++){
////                        //news[i] = new News();
////                        JSONObject js = jsonArray.getJSONObject(i);
////                        news[i] = gson.fromJson(String.valueOf(js),News.class);
////                    System.out.println(news[i].toString());
////                    }
//                }catch (JSONException e){
//                    e.printStackTrace();
//                }
//            }
//        }.start();
//    }
}
