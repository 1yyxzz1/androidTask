package com.example.AndroidTask.MainFram.Check_scheduleFram;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.AndroidTask.JsonTool.ParseJson;
import com.example.cq_1014_task.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Process_Activity extends AppCompatActivity {
    private int feedBackId;
    private ArrayList<Processes> datalist = new ArrayList<Processes>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.setSupportActionBar(toolbar);
        Intent intent = getIntent();
        feedBackId = intent.getIntExtra("feedBackId",0);//初始化必要参数
        System.out.println("feedBackId = "+feedBackId);
        //初始化列表
        Initlist();
        
        setContentView(R.layout.activity_check_schedule);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        RecyclerView recyclerView = findViewById(R.id.rv_schedule);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ProcessAdapter processAdapter = new ProcessAdapter(datalist,Process_Activity.this);
        recyclerView.setAdapter(processAdapter);

    }

    private void Initlist() {

        ParseJson parseJson = new ParseJson("Processes",feedBackId);
        parseJson.getJsonFromInternet();
        datalist = parseJson.ParseJsonToProcesses();
           // System.out.println("哈哈哈哈哈哈哈哈哈哈或或"+datalist.size());

        for (Processes processes:datalist
             ) {
            System.out.println("进度数据测试："+processes.getDesc());
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:   //返回键的id
                this.finish();
                return false;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
