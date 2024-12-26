package com.example.a49_pr.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "shoes")
public class Shoe {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int id;

    @ColumnInfo(name = "shoe_name")
    public String name;

    @ColumnInfo(name = "shoe_brand")
    public String brand;

    @ColumnInfo(name = "shoe_size")
    public double size;

    @ColumnInfo(name = "shoe_price")
    public double price;


    @Ignore
    public Shoe() {}

    public Shoe(String name, String brand, double size, double price) {
        this.name = name;
        this.brand = brand;
        this.size = size;
        this.price = price;
    }
}