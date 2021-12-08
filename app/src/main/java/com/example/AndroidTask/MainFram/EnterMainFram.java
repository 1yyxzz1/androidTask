package com.example.AndroidTask.MainFram;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.AndroidTask.EnterFram.Login;
import com.example.AndroidTask.Database.SPSave;
import com.example.AndroidTask.MainFram.Check_scheduleFram.Process_Activity;
import com.example.AndroidTask.MainFram.SidebarFram.HistoryActivity;
import com.example.cq_1014_task.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;
import androidx.slidingpanelayout.widget.SlidingPaneLayout;

public class EnterMainFram extends AppCompatActivity {
    private RecyclerView mRvMain;
    private PageEnabledSlidingPaneLayout slidingPaneLayout;
    private Button EXIT;
    private TextView HISTORY;
    private TextView COLLECTION;
    int ReturnLocation=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_main_activity);
        EXIT=(Button) findViewById(R.id.sidebar_exit);
        HISTORY = (TextView) findViewById(R.id.sidebar_history);
        COLLECTION = (TextView) findViewById(R.id.sidebar_collection);
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottomnavigationview);
        NavController navController= Navigation.findNavController(this,R.id.fragment2);
        AppBarConfiguration configuration=new AppBarConfiguration.Builder(bottomNavigationView.getMenu()).build();
        NavigationUI.setupActionBarWithNavController(this,navController,configuration);
        NavigationUI.setupWithNavController(bottomNavigationView,navController);

        slidingPaneLayout = (PageEnabledSlidingPaneLayout)findViewById(R.id.main);
        initSlidingPaneLayout();
        setListeners();
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
                    break;
                case R.id.sidebar_history:
                    intent=new Intent(EnterMainFram.this, HistoryActivity.class);
                    break;
                default:
            }
            finish();
            startActivity(intent);
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

}
