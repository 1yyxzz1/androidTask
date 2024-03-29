package com.example.AndroidTask.MainFram.EvaluateFram;

public class Evaluate {
    private long id; //自动生成，无需指定
    private long feedBackId; //反馈信息Id
    private int ratingResult; //处理结果星级评价，1-5
    private int ratingSpeed; //处理速度星级评价，1-5
    private String commend; //其他建议

    public Evaluate(long feedBackId, int ratingResult, int ratingSpeed, String commend) {
        this.feedBackId = feedBackId;
        this.ratingResult = ratingResult;
        this.ratingSpeed = ratingSpeed;
        this.commend = commend;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getFeedBackId() {
        return feedBackId;
    }

    public void setFeedBackId(long feedBackId) {
        this.feedBackId = feedBackId;
    }

    public int getRatingResult() {
        return ratingResult;
    }

    public void setRatingResult(int ratingResult) {
        this.ratingResult = ratingResult;
    }

    public int getRatingSpeed() {
        return ratingSpeed;
    }

    public void setRatingSpeed(int ratingSpeed) {
        this.ratingSpeed = ratingSpeed;
    }

    public String getCommend() {
        return commend;
    }

    public void setCommend(String commend) {
        this.commend = commend;
    }
}
