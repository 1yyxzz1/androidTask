package com.example.AndroidTask.MainFram.HomepageFram;

import androidx.lifecycle.ViewModelProviders;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.AndroidTask.JsonTool.ParseJson;
import com.example.cq_1014_task.R;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class HomepageFragment extends Fragment {

    private HomepageViewModel mViewModel;
    private RecyclerView mRvMain;
    private ArrayList<News> datalist = new ArrayList<News>();
    private ArrayList<Bitmap> bitmaps = new ArrayList<Bitmap>();
    public static HomepageFragment newInstance() {
        return new HomepageFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.homepage_fragment, container, false);
        //避免切换Fragment 的时候重绘UI 。失去数据
        if (mViewModel.view == null) {
            mViewModel.view = inflater.inflate(R.layout.homepage_fragment, container, false);
        }
        // 缓存的viewiew需要判断是否已经被加过parent，
        // 如果有parent需要从parent删除，要不然会发生这个view已经有parent的错误。
        /*ViewGroup parent = (ViewGroup) mViewModel.view.getParent();
        if (parent != null) {
            parent.removeView(mViewModel.view);
        }*/

        return mViewModel.view;
    }

    //数据列表初始化
    public void InitList(){
        ParseJson parseJson = new ParseJson("News");
        parseJson.getJsonFromInternet();
        datalist = parseJson.ParseJsonToNews();
//        for (News news: datalist
//             ) {
//            System.out.println(news.toString());
//        }
        setImage();
    }

    public void setImage(){
        try{
            for (int i = 0;i != datalist.size();i++){
                URL url=new URL(datalist.get(i).getImageUrL());
                Log.d("url", "== imageUrl: " + url);
                //InputStream inputStream = (InputStream) url.getContent();
                //final Drawable drawable = Drawable.createFromStream(inputStream, "123.jpg");
                URLConnection conn = url.openConnection();
                conn.connect();
                InputStream is = conn.getInputStream();
                BitmapFactory.Options options=new BitmapFactory.Options();
                options.inSampleSize = 10;
                Bitmap bmp = BitmapFactory.decodeStream(is);//bitmap压缩，使其可以被载入
                Matrix matrix=new Matrix();
                matrix.setScale(0.2f,0.25f);
                bmp= Bitmap.createBitmap(bmp,0,0,bmp.getWidth(),bmp.getHeight(),matrix,true);
                bitmaps.add(bmp);
                is.close();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        InitList();
        mViewModel = ViewModelProviders.of(this).get(HomepageViewModel.class);
        mRvMain=(RecyclerView) getView().findViewById(R.id.rv_main);
        mRvMain.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        /*mRvMain.setAdapter(new MyAdapter(getActivity(), new MyAdapter.OnItemClickListener() {
            @Override
            public void onClick(int pos) {
                Toast.makeText(getActivity(),"click:"+pos,Toast.LENGTH_SHORT).show();
            }
        }));*/
        if (HomepageViewModel.myAdapter==null)
            HomepageViewModel.myAdapter=new MyAdapter(getActivity(), datalist,bitmaps,new MyAdapter.OnItemClickListener() {
                @Override
                public void onClick(int pos) {
                    Toast.makeText(getActivity(),"click:"+pos,Toast.LENGTH_SHORT).show();
                }
            });
        mRvMain.setAdapter(HomepageViewModel.myAdapter);
        // TODO: Use the ViewModel
    }



    @Override
    public void onDestroy(){
        super.onDestroy();
    }

}
