package com.example.AndroidTask.MainFram.SidebarFram;


import java.util.Date;

//拍照发布后需要存储内容,实际在历史列表显示内容为title、desc、category、time、process
public class Historys{
    private int id;
    private String imageUrl;
    private String title;//
    private String desc;//
    private String account;
    private String address;
    private String category;//pro_type
    private int degree;//
    private String time;//发布日期时间
    private String process;//pro_state

    public Historys(int id, String imageUrl, String title, String desc, String account, String address,
                    String category, int degree, String time, String process) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.title = title;
        this.desc = desc;
        this.account = account;
        this.address = address;
        this.category = category;
        this.degree = degree;
        this.time = time;
        this.process = process;
    }

    @Override
    public String toString() {
        return "Historys{" +
                "id=" + id +
                ", imageUrl='" + imageUrl + '\'' +
                ", title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", account='" + account + '\'' +
                ", address='" + address + '\'' +
                ", category='" + category + '\'' +
                ", degree=" + degree +
                ", time='" + time + '\'' +
                ", process='" + process + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }
}
