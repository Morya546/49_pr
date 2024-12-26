package com.example.a49_pr.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Shoe.class}, version = 1, exportSchema = false)
public abstract class ShoeDB extends RoomDatabase {
    public abstract ShoeDAO getShoeDAO();

    private static ShoeDB instance;

    public static synchronized ShoeDB getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    ShoeDB.class, "ShoeDB").build();
        }
        return instance;
    }
}