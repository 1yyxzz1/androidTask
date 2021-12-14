package com.example.AndroidTask.MainFram.viewpageFram;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.AndroidTask.MainFram.HomepageFram.HomepageFragment;
import com.example.AndroidTask.MainFram.SidebarFram.PageEnabledSlidingPaneLayout;
import com.example.AndroidTask.MainFram.TakePhotoFram.OpenMap;
import com.example.AndroidTask.MainFram.TakePhotoFram.TakephotoFragment;
import com.example.AndroidTask.MainFram.TakePhotoFram.TakephotoViewModel;
import com.example.AndroidTask.MainFram.UserFram.UserFragment;
import com.example.cq_1014_task.R;

import java.util.ArrayList;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.AndroidTask.EnterFram.Login;
import com.example.AndroidTask.Database.SPSave;
import com.example.AndroidTask.MainFram.SidebarFram.HistoryActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.slidingpanelayout.widget.SlidingPaneLayout;

public class EnterMainFram extends AppCompatActivity {
    ViewPager2 mviewPager2;
    private RadioGroup rg;
    private RadioButton rb1,rb2,rb3,rb4;
    private EnterMainAdapter enterMainAdapter;

    private RecyclerView mRvMain;
    private PageEnabledSlidingPaneLayout slidingPaneLayout;
    private Button EXIT;
    private TextView HISTORY;
    private TextView COLLECTION;
    int ReturnLocation=1;
    private String account;


    private ArrayList<Fragment> fragmentArrayList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        account = intent.getStringExtra("account");
        setContentView(R.layout.main);
        EXIT=(Button) findViewById(R.id.sidebar_exit);
        HISTORY = (TextView) findViewById(R.id.sidebar_history);
        COLLECTION = (TextView) findViewById(R.id.sidebar_collection);

        /*viewpage2的方法*/
        initview();
        initdata();
        initadpater();
        initlistener();

        slidingPaneLayout = (PageEnabledSlidingPaneLayout)findViewById(R.id.enterMain);
        initSlidingPaneLayout();
        setListeners();
    }
    private void initadpater() {
        enterMainAdapter=new EnterMainAdapter(this, fragmentArrayList);
        mviewPager2.setAdapter(enterMainAdapter);

    }
    private void initdata() {
        fragmentArrayList.add(new HomepageFragment());
        fragmentArrayList.add(new TakephotoFragment(account));
        fragmentArrayList.add(new UserFragment());
    }

    private void initview() {
        mviewPager2=findViewById(R.id.viewpager2);
        rg=findViewById(R.id.rg);
        rb1=findViewById(R.id.rb1);
        rb2=findViewById(R.id.rb2);
        rb3=findViewById(R.id.rb3);
    }
    private void initlistener() {
        //设置viewpager滑动监听
        mviewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                if(position==0){
                    rb1.setChecked(true);
                }else  if(position==1){
                    rb2.setChecked(true);
                }else  if(position==2){
                    rb3.setChecked(true);
                }
            }

        });
        //点击顶部的时候，切换ViewPager
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //checkedId代表的是选中的rb的id
                if(rb1.getId()==checkedId){
                    mviewPager2.setCurrentItem(0);
                }else if(rb2.getId()==checkedId){
                    mviewPager2.setCurrentItem(1);
                }else if(rb3.getId()==checkedId){
                    mviewPager2.setCurrentItem(2);
                }else if(rb4.getId()==checkedId){
                    mviewPager2.setCurrentItem(3);
                }


            }
        });
    }

    private void setListeners(){
        EnterMainFram.OnClick onClick=new EnterMainFram.OnClick();
        EXIT.setOnClickListener(onClick);
        HISTORY.setOnClickListener(onClick);
        COLLECTION.setOnClickListener(onClick);
        /*mBtnZc.setOnClickListener(onClick);
        mBtnDenglu.setOnClickListener(onClick);*/
    }
    private void initSlidingPaneLayout(){
        slidingPaneLayout.setSliderFadeColor(Color.TRANSPARENT);
        final View leftView = slidingPaneLayout.getChildAt(0);
        slidingPaneLayout.setPanelSlideListener(new SlidingPaneLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(@NonNull View panel, float slideOffset) {
                // 划动过程中
                // 左侧边栏的变化
                leftView.setPivotX(-leftView.getWidth() / 5.0f);
                leftView.setPivotY(leftView.getHeight() / 2.0f);
                leftView.setScaleX(0.8f + 0.2f * slideOffset);//0.8~1
                leftView.setScaleY(0.8f + 0.2f * slideOffset);//0.8~1
                leftView.setAlpha(0.8f + 0.2f * slideOffset);//0.8~1
            }

            @Override
            public void onPanelOpened(@NonNull View view) {

            }

            @Override
            public void onPanelClosed(@NonNull View panel) {
            }
        });
    }

    private class OnClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent intent = null;
            switch (v.getId()) {
                case R.id.sidebar_exit:
                    Toast.makeText(getApplicationContext(),"退出成功",Toast.LENGTH_SHORT).show();
                    SPSave.deleteAll(getApplicationContext());
                    intent=new Intent(EnterMainFram.this, Login.class);
                    finish();
                    break;
                case R.id.sidebar_history:
                    intent=new Intent(EnterMainFram.this, HistoryActivity.class);
                    intent.putExtra("account",account);
                    break;
                default:
            }

            startActivity(intent);
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }


}
