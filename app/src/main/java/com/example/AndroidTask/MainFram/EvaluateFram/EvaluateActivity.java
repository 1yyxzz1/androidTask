package com.example.AndroidTask.MainFram.EvaluateFram;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cq_1014_task.R;

public class EvaluateActivity extends AppCompatActivity implements View.OnClickListener{
    private Button evaluate;
    private Button back;
    private RatingBar process_speed;
    private RatingBar process_result;
    private EditText text;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.evaluate_history_test2);
        evaluate=(Button) findViewById(R.id.evaluate_btn_deliver);
        back=(Button) findViewById(R.id.evaluate_btn_return);
        process_result=(RatingBar) findViewById(R.id.ratingBar1);
        process_speed=(RatingBar) findViewById(R.id.ratingBar2);
        text=(EditText) findViewById(R.id.evaluate_text);
        back.setOnClickListener(this);
        evaluate.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.evaluate_btn_deliver:
                float process_result_num=process_result.getRating(); //处理结果星星数
                float process_speed_num=process_speed.getRating(); //处理速度星星数目
                String evaluate_text=text.getText().toString(); //评价文本
                System.out.println(process_result_num+" "+process_speed_num+" "+evaluate_text);
                Toast.makeText(getApplicationContext(),"提交成功",Toast.LENGTH_SHORT).show();
                break;
            case R.id.evaluate_btn_return:
                Toast.makeText(getApplicationContext(),"返回成功",Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
    }
}
