package com.example.a49_pr.view;

import android.content.Context;

import com.example.a49_pr.model.Shoe;
import com.example.a49_pr.model.ShoeDB;
import com.example.a49_pr.view.ShoeView;

import java.util.List;

public class ShoePresenter {
    private final ShoeView view;
    private final ShoeDB shoeDB;

    public ShoePresenter(ShoeView view, Context context) {
        this.view = view;
        this.shoeDB = ShoeDB.getInstance(context);
    }

    public void loadShoes() {
        new Thread(() -> {
            List<Shoe> shoes = shoeDB.getShoeDAO().getAllShoes();
            view.showShoes(shoes);
        }).start();
    }

    public void addShoe(String name, String brand, String sizeStr, String priceStr) {
        if (name.isEmpty() || brand.isEmpty() || sizeStr.isEmpty() || priceStr.isEmpty()) {
            view.showMessage("All fields are required");
            return;
        }

        try {
            double size = Double.parseDouble(sizeStr);
            double price = Double.parseDouble(priceStr);
            Shoe newShoe = new Shoe(name, brand, size, price);
            new Thread(() -> {
                shoeDB.getShoeDAO().addShoe(newShoe);
                loadShoes();
            }).start();
            view.clearInputFields();
        } catch (NumberFormatException e) {
            view.showMessage("Invalid size or price");
        }
    }

    public void deleteShoe(Shoe shoe) {
        new Thread(() -> {
            shoeDB.getShoeDAO().deleteShoe(shoe);
            loadShoes();
        }).start();
    }
}
