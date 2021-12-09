package com.example.AndroidTask.MainFram.TakePhotoFram;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.cq_1014_task.R;

public class TakephotoFragment extends Fragment {
    private TakephotoViewModel mViewModel;
    private ImageButton openCamera;
    private RadioButton rb_safe,rb_clean,rb_order,rb_important,rb_normal;
    private RadioGroup rg_content,rg_important;
    private TextView t_tag_question;
    private TextView t_tag_important;
    private ImageButton open_map;
    private TextView myLocation = null;
    private ImageView imageView;

    private final int ReturnLocation=1;//返回地址定位
    private final int OPEN_RESULT = 2; // 打开相机
    private final int PICK_RESULT = 3; // 查看相册

    private void setListeners(){
        OnClick onClick=new OnClick();
        rb_safe.setOnClickListener(onClick);
        rb_clean.setOnClickListener(onClick);
        rb_normal.setOnClickListener(onClick);
        rb_important.setOnClickListener(onClick);
        rb_order.setOnClickListener(onClick);
    }

    private class OnClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.rb_safe:
                    t_tag_question.setText("问题类型是：安全隐患");
                    break;
                case R.id.rb_clean:
                    t_tag_question.setText("问题类型是：卫生问题");
                    break;
                case R.id.rb_order:
                    t_tag_question.setText("问题类型是：秩序问题");
                    break;
                case R.id.rb_important:
                    t_tag_important.setText("问题重要性是：重要");
                    break;
                case R.id.rb_normal:
                    t_tag_important.setText("问题重要性是：一般");
                    break;
            }
        }
    }

    public ImageButton getImageButton(){
        return openCamera;
    }

    public static TakephotoFragment newInstance() {
        return new TakephotoFragment();
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
        mViewModel = ViewModelProviders.of(this).get(TakephotoViewModel.class);// TODO: Use the ViewModel

        rb_safe=(RadioButton) getView().findViewById(R.id.rb_safe);
        openCamera = (ImageButton) getView().findViewById(R.id.img_camera);
        rb_clean=(RadioButton) getView().findViewById(R.id.rb_clean);
        rb_normal=(RadioButton) getView().findViewById(R.id.rb_normal);
        rb_safe=(RadioButton) getView().findViewById(R.id.rb_safe);
        rb_order=(RadioButton) getView().findViewById(R.id.rb_order);
        rb_important=(RadioButton) getView().findViewById(R.id.rb_important);
        rg_content=(RadioGroup) getView().findViewById(R.id.rg_content);
        rg_important=(RadioGroup) getView().findViewById(R.id.rg_important);
        open_map=(ImageButton) getView().findViewById(R.id.openMap);
        myLocation=(TextView) getView().findViewById(R.id.textView);

        t_tag_question=(TextView) getView().findViewById(R.id.t_tag_question);
        t_tag_important=(TextView) getView().findViewById(R.id.t_tag_important);
        openCamera.setImageDrawable(getResources().getDrawable(R.drawable.add));
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


}
