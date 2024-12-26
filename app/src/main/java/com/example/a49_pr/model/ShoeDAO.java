package com.example.a49_pr.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ShoeDAO
{
    @Insert
    void addShoe(Shoe shoe);

    @Update
    void updateShoe(Shoe shoe);

    @Delete
    void deleteShoe(Shoe shoe);

    @Query("SELECT * FROM shoes")
    List<Shoe> getAllShoes();

    @Query("SELECT * FROM shoes WHERE id = :shoeId")
    Shoe getShoeById(int shoeId);
}
