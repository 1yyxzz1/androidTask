package com.example.AndroidTask.MainFram.Check_scheduleFram;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cq_1014_task.R;
import com.github.vipulasri.timelineview.TimelineView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ProcessAdapter extends RecyclerView.Adapter<ProcessAdapter.ViewHolder> {
    //List<ProcessBean.Data> processes;
    List<String> processes;
    Context context;

    public ProcessAdapter(List<String> p, Context context){
        this.processes=p;
        this.context = context;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView status;
        public TextView time;
        public TimelineView timelineView;

        public ViewHolder(View view, int viewType){
            super(view);
            status=(TextView)view.findViewById(R.id.text_timeline_desc);
            time=(TextView)view.findViewById(R.id.text_timeline_date);
            timelineView=(TimelineView)view.findViewById(R.id.timeline);
            timelineView.initLine(viewType);
        }
    }

    @Override
    public int getItemCount() {
        return processes.size();
    }

    @Override
    public int getItemViewType(int position) {
        return TimelineView.getTimeLineViewType(position,getItemCount());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=View.inflate(parent.getContext(),R.layout.item_timeline2,null);
        return new ViewHolder(view,viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.status.setText(processes.get(position)); //设置状态
        Date date=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        holder.time.setText(simpleDateFormat.format(date)); //设置时间
        //设置图片
        if (processes.get(position)=="yyy") holder.timelineView.setMarker(ResourcesCompat.getDrawable(context.getResources(),R.drawable.done,null));

        //下面大概写了咋传参 具体实现根据接口 记得删掉上面的测试
        //ProcessBean.Data process=processes.get(position);
        /*holder.status.setText(process.getDesc());
        Date date=process.getTime();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        holder.time.setText(simpleDateFormat.format(date));*/

        /*if (process.getDesc().equals("已提交")){
            holder.timelineView
                    .setMarker(ResourcesCompat.getDrawable(context.getResources(),R.drawable.submit,null));
        } else if (process.getDesc().equals("等待处理")){
            holder.timelineView
                    .setMarker(ResourcesCompat.getDrawable(context.getResources(),R.drawable.waiting,null));
        } else if (process.getDesc().equals("处理中")){
            holder.timelineView
                    .setMarker(ResourcesCompat.getDrawable(context.getResources(),R.drawable.dealing,null));
        } else if (process.getDesc().equals("处理完成")){
            holder.timelineView
                    .setMarker(ResourcesCompat.getDrawable(context.getResources(),R.drawable.done,null));
        }*/
    }
}
