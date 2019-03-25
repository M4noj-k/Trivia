package com.example.trivia.Data.Room;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface RoomApi {

    @Query("SELECT * FROM store_data")
    List<QAEntiry> loadHistory();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertData(QAEntiry data);

    @Query("UPDATE store_data SET q1ques=:q1Qes, q1ans=:q1Ans WHERE userId=:userId")
    void updateQ1(Integer userId, String q1Qes, String q1Ans);

    @Query("UPDATE store_data SET q2ques=:q2Qes, q2ans=:q2Ans WHERE userId=:userId")
    void updateQ2(Integer userId, String q2Qes, String q2Ans);

    @Query("SELECT * FROM store_data ORDER BY userId DESC LIMIT 1")
    QAEntiry getLastUser();
}
