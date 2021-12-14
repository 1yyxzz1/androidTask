package com.example.AndroidTask.MainFram.HomepageFram;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.AndroidTask.JsonTool.ParseJson;
import com.example.cq_1014_task.R;

import java.util.ArrayList;

public class HomepageFragment extends Fragment {

    private HomepageViewModel mViewModel;
    private RecyclerView mRvMain;
    private ArrayList<News> datalist = new ArrayList<News>();
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
            HomepageViewModel.myAdapter=new MyAdapter(getActivity(), datalist,new MyAdapter.OnItemClickListener() {
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
