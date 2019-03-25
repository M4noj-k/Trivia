package com.example.trivia.Data.Room;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;

@Entity(tableName = "store_data")
public class QAEntiry implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private Integer userId;
    private String userName;
    private String date;
    private String q1ques;
    private String q1ans;
    private String q2ques;
    private String q2ans;

    @NonNull
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(@NonNull Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getQ1ques() {
        return q1ques;
    }

    public void setQ1ques(String q1ques) {
        this.q1ques = q1ques;
    }

    public String getQ1ans() {
        return q1ans;
    }

    public void setQ1ans(String q1ans) {
        this.q1ans = q1ans;
    }

    public String getQ2ques() {
        return q2ques;
    }

    public void setQ2ques(String q2ques) {
        this.q2ques = q2ques;
    }

    public String getQ2ans() {
        return q2ans;
    }

    public void setQ2ans(String q2ans) {
        this.q2ans = q2ans;
    }
}
