package com.example.yaratest.tools;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.yaratest.dao.MovieDao;
import com.example.yaratest.model.entity.MovieEntity;

@Database(entities = {MovieEntity.class}, version = 1)
public abstract class ApplicationDatabase extends RoomDatabase {

    private static ApplicationDatabase instance;

    public abstract MovieDao movieDao();

    public static synchronized ApplicationDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    ApplicationDatabase.class, "database")
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return instance;
    }
}
