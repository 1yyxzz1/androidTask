package com.example.AndroidTask.MainFram.TakePhotoFram;

import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.request.RequestOptions;
import com.example.AndroidTask.JsonTool.ParseJson;
import com.example.cq_1014_task.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Date;

public class TakephotoFragment extends Fragment {
    private TakephotoViewModel mViewModel;

    /*控件*/
    private EditText text_title,text_desc;
    private ImageButton openCamera,open_map;
    private RadioButton rb_safe,rb_clean,rb_order,rb_important,rb_normal;
    private Button btn_login;
    private RadioGroup rg_content,rg_important;
    private TextView t_tag_question,t_tag_important,myLocation = null;

    /*上传信息*/
    FeedBack feedBack;//用于上传
    private String imageUrl; //图片url
    private String title;   //标题
    private String desc;  //描述
    private String address; //地址，定位
    private String category; //类别：安全隐患、卫生问题、秩序问题
    private int degree;  //级别：0-一般，  1-重要
    private Date time;  //时间: 2021-11-06T13:14:25.909+00:00
    private String process; //当前状态："已提交"
    private String account;

    /*返回信息*/
    private final int ReturnLocation=1;//返回地址定位
    private final int OPEN_RESULT = 2; // 打开相机
    private final int PICK_RESULT = 3; // 查看相册

    public TakephotoFragment(String account) {
        this.account=account;
    }

    private void setListeners(){
        OnClick onClick=new OnClick();
        rb_safe.setOnClickListener(onClick);
        rb_clean.setOnClickListener(onClick);
        rb_normal.setOnClickListener(onClick);
        rb_important.setOnClickListener(onClick);
        rb_order.setOnClickListener(onClick);
        btn_login.setOnClickListener(onClick);
    }

    private class OnClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.rb_safe:
                    category="安全隐患";
                    t_tag_question.setText("问题类型是：安全隐患");
                    break;
                case R.id.rb_clean:
                    category="卫生问题";
                    t_tag_question.setText("问题类型是：卫生问题");
                    break;
                case R.id.rb_order:
                    category="秩序问题";
                    t_tag_question.setText("问题类型是：秩序问题");
                    break;
                case R.id.rb_important:
                    degree=1;
                    t_tag_important.setText("问题重要性是：重要");
                    break;
                case R.id.rb_normal:
                    degree=0;
                    t_tag_important.setText("问题重要性是：一般");
                    break;
                case R.id.btn_login:
                    submit();
                    break;

            }
        }
    }

    public static TakephotoFragment newInstance() {
        return new TakephotoFragment(newInstance().account);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        //避免切换Fragment 的时候重绘UI 。失去数据
        if (TakephotoViewModel.view == null) {
            //System.out.println(1);
            TakephotoViewModel.view = inflater.inflate(R.layout.takephoto_fragment, null);
        }
        // 缓存的viewiew需要判断是否已经被加过parent，
        // 如果有parent需要从parent删除，要不然会发生这个view已经有parent的错误。
        ViewGroup parent = (ViewGroup) TakephotoViewModel.view.getParent();
        if (parent != null) {
            parent.removeView(TakephotoViewModel.view);
        }

        return TakephotoViewModel.view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ReturnLocation) {
            Bundle bundle = data.getExtras();
            String str= bundle.getString("returnLocation");
            address=str;
            myLocation.setText("当前位置:"+str);
        }
        else if (resultCode == OPEN_RESULT) {
            Bundle bundle = data.getExtras();
            Bitmap bitmap = (Bitmap) bundle.getParcelable("bitmap");
            openCamera.setImageBitmap(bitmap);
        }
        else if (resultCode == PICK_RESULT) {
            // 表示选择图片库的图片结果
            Uri uri = data.getData();
            openCamera.setImageURI(uri);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initComponent();//初始化部件---findbyview
        //拍照
        openCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent();
                intent2.setClass(getActivity().getApplicationContext(), CameraActivity.class);
                Bundle bundle = new Bundle();
                intent2.putExtras(bundle);
                startActivityForResult(intent2, 2);
            }
        });
        //地图
        open_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent();
                intent2.setClass(getActivity().getApplicationContext(),OpenMap.class);
                Bundle bundle = new Bundle();
                intent2.putExtras(bundle);
                startActivityForResult(intent2, 1);
            }
        });
        //if(intent==null){
        openCamera.setImageDrawable(getResources().getDrawable(R.drawable.add));
        //}
        /*if(intent!=null){
            Bitmap bitmap=intent.getParcelableExtra("bitmap");
            *//*FragmentManager fm = getActivity().getFragmentManager();
            //注意v4包的配套使用
            Fragment fragment = new 目标fragment();
            fm.beginTransaction().replace(容器控件id,fragment).commit();*//*
            openCamera.setImageBitmap(bitmap);
        }
        if(intent!=null){
            Uri uri = intent.getParcelableExtra("uri");
            openCamera.setImageURI(uri);
        }
        setListeners();*/

    }
    private void initComponent(){
        mViewModel = ViewModelProviders.of(this).get(TakephotoViewModel.class);// TODO: Use the ViewModel

        text_title=getView().findViewById(R.id.e_title);
        text_desc=getView().findViewById(R.id.e_desc);
        rb_safe=getView().findViewById(R.id.rb_safe);
        openCamera = getView().findViewById(R.id.img_camera);
        rb_clean=getView().findViewById(R.id.rb_clean);
        rb_normal=getView().findViewById(R.id.rb_normal);
        rb_safe=getView().findViewById(R.id.rb_safe);
        rb_order=getView().findViewById(R.id.rb_order);
        rb_important= getView().findViewById(R.id.rb_important);
        rg_content=getView().findViewById(R.id.rg_content);
        rg_important=getView().findViewById(R.id.rg_important);
        open_map=getView().findViewById(R.id.openMap);
        myLocation= getView().findViewById(R.id.textView);
        btn_login=getView().findViewById(R.id.btn_login);


        t_tag_question=getView().findViewById(R.id.t_tag_question);
        t_tag_important=getView().findViewById(R.id.t_tag_important);
        openCamera.setImageDrawable(getResources().getDrawable(R.drawable.add));
        setListeners();
    }
    private void submit(){
        ParseJson parseJson = new ParseJson("Historys",feedBack);//工具初始化
        parseJson.getJsonFromInternet();//连接
        title=text_title.getText().toString().trim();;
        desc=text_desc.getText().toString().trim();;
        process="已提交";
        imageUrl="http://49.235.134.191:8080/images/2021-10-29/761f7daddd8a4f61b04f3780dbf18a27.jpg";
        time=new Date(System.currentTimeMillis());
        feedBack=new FeedBack(imageUrl,title,desc,account,address,category,degree,time,process);
        Toast.makeText(getActivity(), feedBack.toString(), Toast.LENGTH_SHORT).show();
        int result=parseJson.postJsonToInternet();
        if (result==200)
            Toast.makeText(getActivity(), "上传成功", Toast.LENGTH_SHORT).show();
        else Toast.makeText(getActivity(), "上传失败", Toast.LENGTH_SHORT).show();
    }


}
