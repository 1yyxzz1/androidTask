package com.example.AndroidTask.MainFram.Check_scheduleFram;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.AndroidTask.MainFram.SidebarFram.HistoryActivity;
import com.example.AndroidTask.MainFram.SidebarFram.HistoryAdapter;
import com.example.cq_1014_task.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Process_Activity extends AppCompatActivity {
    List<String> bc= Arrays.asList("xxx","yyy","zzz");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //this.setSupportActionBar(toolbar);
        setContentView(R.layout.activity_check_schedule);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        RecyclerView recyclerView = findViewById(R.id.rv_schedule);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ProcessAdapter processAdapter = new ProcessAdapter(bc,Process_Activity.this);
        recyclerView.setAdapter(processAdapter);

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
