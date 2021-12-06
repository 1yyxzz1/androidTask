package com.example.AndroidTask.MainFram.SidebarFram;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.AndroidTask.Database.SPSave;
import com.example.AndroidTask.EnterFram.Login;
import com.example.AndroidTask.MainFram.EnterMainFram;
import com.example.AndroidTask.MainFram.MyAdapter;
import com.example.cq_1014_task.R;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    //private Historys[] historys;//存储通过网络编程获取的数据

    //test code
    private ArrayList<Historys> datelist = new ArrayList<Historys>();
    private Historys[] historys = new Historys[]{
            new Historys(20,"http://42.194.213.29:5999/images/2021-05-24/feeeb0349d05494f82d0c304e38fdad0.jpg",
            "快递中心门口井盖没盖！","前面的路上有井盖没盖好！大家注意绕行！！","test",
        "{\"address\":\"福建省福州市闽侯县学府南路96路靠近博学苑B区\",\"city\":\"福州市\",\"latitude\":26.049758,\"longitude\":119.19259}",
            "安全隐患",1,"2021-05-24T01:42:31.000+00:00","处理完成"),
        new Historys(20,"http://42.194.213.29:5999/images/2021-05-24/feeeb0349d05494f82d0c304e38fdad0.jpg",
        "快递中心门口井盖没盖！","前面的路上有井盖没盖好！大家注意绕行！！","test",
        "{\"address\":\"福建省福州市闽侯县学府南路96路靠近博学苑B区\",\"city\":\"福州市\",\"latitude\":26.049758,\"longitude\":119.19259}",
        "安全隐患",1,"2021-05-24T01:42:31.000+00:00","处理完成"),
            new Historys(20,"http://42.194.213.29:5999/images/2021-05-24/feeeb0349d05494f82d0c304e38fdad0.jpg",
                    "快递中心门口井盖没盖！","前面的路上有井盖没盖好！大家注意绕行！！","test",
                    "{\"address\":\"福建省福州市闽侯县学府南路96路靠近博学苑B区\",\"city\":\"福州市\",\"latitude\":26.049758,\"longitude\":119.19259}",
                    "安全隐患",1,"2021-05-24T01:42:31.000+00:00","处理完成"),
            new Historys(20,"http://42.194.213.29:5999/images/2021-05-24/feeeb0349d05494f82d0c304e38fdad0.jpg",
                    "快递中心门口井盖没盖！","前面的路上有井盖没盖好！大家注意绕行！！","test",
                    "{\"address\":\"福建省福州市闽侯县学府南路96路靠近博学苑B区\",\"city\":\"福州市\",\"latitude\":26.049758,\"longitude\":119.19259}",
                    "安全隐患",1,"2021-05-24T01:42:31.000+00:00","处理完成"),
            new Historys(20,"http://42.194.213.29:5999/images/2021-05-24/feeeb0349d05494f82d0c304e38fdad0.jpg",
                    "快递中心门口井盖没盖！","前面的路上有井盖没盖好！大家注意绕行！！","test",
                    "{\"address\":\"福建省福州市闽侯县学府南路96路靠近博学苑B区\",\"city\":\"福州市\",\"latitude\":26.049758,\"longitude\":119.19259}",
                    "安全隐患",1,"2021-05-24T01:42:31.000+00:00","处理完成")
    };

    private void initList(){
        for(int i = 0;i != historys.length;i++){
            datelist.add(historys[i]);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initList();
        setContentView(R.layout.activity_history);
        RecyclerView recyclerView = findViewById(R.id.rv_history);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        HistoryAdapter historyAdapter = new HistoryAdapter(HistoryActivity.this,datelist);
        recyclerView.setAdapter(historyAdapter);
        historyAdapter.setOnItemClickListener(new HistoryAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onClick(View view, HistoryAdapter.ViewName viewName, int position) {
                switch (viewName){
                    case ITEM:
                        break;
                    case CHECK_SCHEDULE:
                        //进入查看进度界面
                        //test code
                        //Log.println(100,"CHECK_SCHEDULE",position+":查看进度");

                        //insert code

                        break;
                    case EVALUATE:
                        //进入我要评价界面
                        //Log.println(100,"EVALUATE",position+":我要评价");

                        //insert code

                        break;
                }
            }
        });
    }

//    private void setListeners() {
//
//    }

//    //OnClick类 实现activity跳转
//    private class OnClick implements View.OnClickListener {
//
//        @Override
//        public void onClick(View v) {
//            Intent intent = null;
//            switch (v.getId()) {
//                //查看进度
//                case R.id.Check_schedule:
//                    //code insert
//
//                    break;
//
//                    //我要评价
//                case R.id.evaluate:
//                    //code insert
//
//                    break;
//            }
//            finish();
//            startActivity(intent);
//        }
//    }



//    /**
//     * item＋item里的控件点击监听事件
//     */
//    private HistoryAdapter.OnItemClickListener MyItemClickListener = new HistoryAdapter.OnItemClickListener() {
//
//        @Override
//        public void onItemClick(View v, int position) {
//            //viewName可区分item及item内部控件
//            switch (v.getId()){
//                case R.id.Check_schedule:
//                    Toast.makeText(HistoryActivity.this,"你点击了查看进度"+(position+1),Toast.LENGTH_SHORT).show();
//                    break;
//                case R.id.evaluate:
//                    Toast.makeText(HistoryActivity.this,"你点击了我要评价"+(position+1),Toast.LENGTH_SHORT).show();
//                    break;
//                default:
//                    Toast.makeText(HistoryActivity.this,"你点击了item按钮"+(position+1),Toast.LENGTH_SHORT).show();
//                    break;
//            }
//        }
//
//        @Override
//        public void onItemLongClick(View v) {
//
//        }
//    };



////适配器部分 进行网络编程时需修改
//    class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.MyViewHolder>{
//
//
//        @NonNull
//        //加载item界面的布局文件 并返回MyViewHolder对象
//        @Override
//        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//            View view = LayoutInflater.from(HistoryActivity.this).inflate(R.layout.sidebar_history_item,parent,false);
//           // MyViewHolder myViewHolder =  new MyViewHolder(view,mOnItemClickListener);
//            MyViewHolder myViewHolder = new MyViewHolder(view);
//            return myViewHolder;
//        }
//
////        @Override
////        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
////
////        }
//
//        //获取数据并显示到控件
//        @Override
//        public void onBindViewHolder(@NonNull MyViewHolder holder,final int position) {
//            //网络链接登录后立即向各存储数据的结构传输数据，
//            //此处Historys[]已经存储数据，剩下的只需要进行数据的读取与item各空间value的设置
//            holder.tv_problem.setText(historys[position].getTitle());
//            holder.tv_pro_content.setText(historys[position].getDesc());
//            holder.tv_pro_type.setText(historys[position].getCategory());
//            holder.tv_datetime.setText(historys[position].getTime().toString());
//            holder.tv_pro_state.setText(historys[position].getProcess());
//
//            holder.Check_schedule.setTag(position);
//            holder.evaluate.setTag(position);
//
//            holder.Check_schedule.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    //点击事件
//                    Toast.makeText(getApplicationContext(),position+":查看进度",Toast.LENGTH_LONG);
//                }
//            });
//
//            holder.evaluate.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    //点击事件
//                    Toast.makeText(getApplicationContext(),position+":我要评价",Toast.LENGTH_LONG);
//                }
//            });
//        }
//
//
//        //获取item个数
//        @Override
//        public int getItemCount() {
//            //return historys.length;
//
//            //test code
//            return 5;
//        }
//
////        public interface OnItemClickListener{
////            void onItemClick(View v, int position);
////            void onItemLongClick(View v);
////        }
////
////        private OnItemClickListener mOnItemClickListener;//声明自定义的接口
////
////        //第三步：定义方法并暴露给外面的调用者
////        public void setOnItemClickListener(OnItemClickListener  listener) {
////            this.mOnItemClickListener  = listener;
////        }
//
//        //HistoryAdapter内的Holder类
//        //public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
//        public class MyViewHolder extends RecyclerView.ViewHolder{
//
//            TextView tv_problem;
//            TextView tv_pro_content;
//            TextView tv_datetime;
//            TextView tv_pro_type;
//            TextView tv_pro_state;
//            Button Check_schedule;
//            Button evaluate;
//
//          //  public MyViewHolder(@NonNull View itemView,final OnItemClickListener onItemClickListener) {
//            public MyViewHolder(@NonNull View itemView) {
//                super(itemView);
//                tv_problem = itemView.findViewById(R.id.tv_problem);
//                tv_pro_content = itemView.findViewById(R.id.tv_pro_content);
//                tv_datetime = itemView.findViewById(R.id.tv_datetime);
//                tv_pro_type = itemView.findViewById(R.id.tv_pro_type);
//                tv_pro_state = itemView.findViewById(R.id.tv_pro_state);
//                Check_schedule = itemView.findViewById(R.id.Check_schedule);
//                evaluate = itemView.findViewById(R.id.evaluate);
//
////                Check_schedule.setOnClickListener(this);
////                evaluate.setOnClickListener(this);
//            }
//
////            @Override
////            public void onClick(View view) {
////                if (mOnItemClickListener != null) {
////                    mOnItemClickListener.onItemClick(view, getAdapterPosition());
////                }
////            }
//        }
//    }

}