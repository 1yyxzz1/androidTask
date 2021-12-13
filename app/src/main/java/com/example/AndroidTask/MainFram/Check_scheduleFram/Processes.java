package com.example.AndroidTask.MainFram.Check_scheduleFram;

import android.provider.ContactsContract;

import java.util.Date;

public class Processes {
    private int id;
    private int feedBackId;
    private String desc;
    private Date time;
    private String imageUrl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFeedBackId() {
        return feedBackId;
    }

    public void setFeedBackId(int feedBackId) {
        this.feedBackId = feedBackId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Processes(int id, int feedBackId, String desc, Date time, String imageUrl) {
        this.id = id;
        this.feedBackId = feedBackId;
        this.desc = desc;
        this.time = time;
        this.imageUrl = imageUrl;
    }
}
