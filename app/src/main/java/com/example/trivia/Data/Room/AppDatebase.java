package com.example.trivia.Data.Room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(
        version = 1,
        entities ={QAEntiry.class}
)
public abstract class AppDatebase extends RoomDatabase {
    public abstract RoomApi getDao();
}
