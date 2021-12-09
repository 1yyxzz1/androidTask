package com.example.AndroidTask.MainFram.TakePhotoFram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.AndroidTask.MainFram.viewpageFram.EnterMainFram;
import com.example.cq_1014_task.R;

public class CameraActivity extends AppCompatActivity {
    private final String TAG = "TakePhoto";
    private Button openbButton;
    private Button pickButton;
    private ImageView imageView;
    private String mPictureFile;
    private String filePath;
    private ImageButton returnpic;


    private final int OPEN_RESULT = 2; // 打开相机
    private final int PICK_RESULT = 3; // 查看相册

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        openbButton = (Button) findViewById(R.id.btnTakePhoto);
        pickButton = (Button) findViewById(R.id.btnPick);
        imageView = (ImageView) findViewById(R.id.imgPotho);
        returnpic=(ImageButton)findViewById(R.id.img_camera);

        openbButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 使用意图直接调用安装在手机上的照相机
                Intent intent = new Intent(
                        android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                // 打开照相机,设置请求码
                startActivityForResult(intent, OPEN_RESULT);
            }
        });

        pickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 使用意图直接调用手机相册
                Intent intent = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                // 打开手机相册,设置请求码
                startActivityForResult(intent, PICK_RESULT);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode == OPEN_RESULT) {
            if (resultCode == RESULT_OK) {
                Bundle bundle = data.getExtras();
                Bitmap bitmap = (Bitmap) bundle.get("data");
                imageView.setImageBitmap(bitmap);

                Intent intent=new Intent(CameraActivity.this, EnterMainFram.class);

                Bundle bundle1=new Bundle();
                bundle1.putParcelable("bitmap",bitmap);
                intent.putExtras(bundle1);
                setResult(OPEN_RESULT, intent);
                finish();
            }
        } else if (requestCode == PICK_RESULT) {
            // 表示选择图片库的图片结果
            if (resultCode == RESULT_OK) {
                Uri uri = data.getData();
                imageView.setImageURI(uri);
                Intent intent=new Intent(CameraActivity.this, EnterMainFram.class);
                intent.setData(uri);
                setResult(PICK_RESULT, intent);
                finish();
            }
        }
    }

    /**
     * 获得照片路径
     *
     * @return
     */
    private String getPhotoPath() {
        return Environment.getExternalStorageDirectory() + "/DCIM/";
    }
}