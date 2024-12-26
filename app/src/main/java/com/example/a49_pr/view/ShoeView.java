package com.example.a49_pr.view;

import com.example.a49_pr.model.Shoe;

import java.util.List;

public interface ShoeView {
    void showShoes(List<Shoe> shoes);
    void showMessage(String message);
    void clearInputFields();
}