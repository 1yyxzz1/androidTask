package com.example.AndroidTask.MainFram.HomepageFram;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.PhantomReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import kotlin.jvm.internal.PropertyReference0Impl;

public class News {
    private int id;
    private String imageUrL;
    private String title;
    private String desc;
    private String publishAccount;
    private String publishTime;

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", imageUrL='" + imageUrL + '\'' +
                ", title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", publishAccount='" + publishAccount + '\'' +
                ", publishTime='" + publishTime + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageUrL() {
        return imageUrL;
    }

    public void setImageUrL(String imageUrL) {
        this.imageUrL = imageUrL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPublishAccount() {
        return publishAccount;
    }

    public void setPublishAccount(String publishAccount) {
        this.publishAccount = publishAccount;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

}