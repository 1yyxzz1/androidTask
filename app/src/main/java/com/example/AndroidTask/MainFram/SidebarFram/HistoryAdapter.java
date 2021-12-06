package com.example.AndroidTask.MainFram.SidebarFram;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import com.example.cq_1014_task.R;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ItemHolder> implements View.OnClickListener{

    private ArrayList<Historys> dataList = null;
    private Context context;

    public HistoryAdapter(Context context, ArrayList<Historys> list) {
        this.context = context;
        dataList = list;
        System.out.println("content:"+context);
    }

    @Override
    public HistoryAdapter.ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.sidebar_history_item, parent, false);
        return new ItemHolder(itemView);
    }

    @Override
    public void onBindViewHolder(HistoryAdapter.ItemHolder holder, final int position) {
        holder.tv_problem.setText(dataList.get(position).getTitle());
        holder.tv_pro_content.setText(dataList.get(position).getDesc());
        holder.tv_pro_type.setText(dataList.get(position).getCategory());
        holder.tv_datetime.setText(dataList.get(position).getTime().toString());
        holder.tv_pro_state.setText(dataList.get(position).getProcess());

        holder.Check_schedule.setTag(position);
        holder.evaluate.setTag(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder {
        
        public TextView tv_problem;
        public TextView tv_pro_content;
        public TextView tv_datetime;
        public TextView tv_pro_type;
        public TextView tv_pro_state;
        public Button Check_schedule;
        public Button evaluate;

        public ItemHolder(View itemView) {
            super(itemView);
            tv_problem = itemView.findViewById(R.id.tv_problem);
            tv_pro_content = itemView.findViewById(R.id.tv_pro_content);
            tv_datetime = itemView.findViewById(R.id.tv_datetime);
            tv_pro_type = itemView.findViewById(R.id.tv_pro_type);
            tv_pro_state = itemView.findViewById(R.id.tv_pro_state);
            Check_schedule = itemView.findViewById(R.id.Check_schedule);
            evaluate = itemView.findViewById(R.id.evaluate);
            //将创建的View注册点击事件
            Check_schedule.setOnClickListener(HistoryAdapter.this);
            evaluate.setOnClickListener(HistoryAdapter.this);
        }
    }


    //以下为item点击处理

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    /** item里面有多个控件可以点击 */
    public enum ViewName {
        ITEM,
        CHECK_SCHEDULE,
        EVALUATE
    }

    public interface OnRecyclerViewItemClickListener {
        void onClick(View view, ViewName viewName, int position);
    }

    @Override
    public void onClick(View v) {
        //注意这里使用getTag方法获取数据
        int position = (int) v.getTag();
        if (mOnItemClickListener != null) {
            switch (v.getId()){
                case R.id.Check_schedule:
                    mOnItemClickListener.onClick(v, ViewName.CHECK_SCHEDULE, position);
                    break;
                case R.id.evaluate:
                    mOnItemClickListener.onClick(v, ViewName.EVALUATE, position);
                    break;
                default:
                    mOnItemClickListener.onClick(v, ViewName.ITEM, position);
                    break;
            }
        }
    }
}
