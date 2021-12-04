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

import com.example.AndroidTask.MainFram.MyAdapter;
import com.example.cq_1014_task.R;

public class HomepageFragment extends Fragment {

    private HomepageViewModel mViewModel;
    private RecyclerView mRvMain;
    private MyAdapter myAdapter;

    public static HomepageFragment newInstance() {
        return new HomepageFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.homepage_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(HomepageViewModel.class);
        mRvMain=(RecyclerView) getView().findViewById(R.id.rv_main);
        mRvMain.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        /*mRvMain.setAdapter(new MyAdapter(getActivity(), new MyAdapter.OnItemClickListener() {
            @Override
            public void onClick(int pos) {
                Toast.makeText(getActivity(),"click:"+pos,Toast.LENGTH_SHORT).show();
            }
        }));*/
        myAdapter=new MyAdapter(getActivity(), new MyAdapter.OnItemClickListener() {
            @Override
            public void onClick(int pos) {
                Toast.makeText(getActivity(),"click:"+pos,Toast.LENGTH_SHORT).show();
            }
        });
        mRvMain.setAdapter(myAdapter);
        // TODO: Use the ViewModel
    }

}
