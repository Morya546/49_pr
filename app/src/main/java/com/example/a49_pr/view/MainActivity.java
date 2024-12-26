package com.example.a49_pr.view;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.a49_pr.R;
import com.example.a49_pr.model.Shoe;
import com.example.a49_pr.model.ShoeDB;
import com.example.a49_pr.controler.ShoeAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button addShoeBtn, deleteShoeBtn, refreshShoesBtn;
    private EditText nameInputField, brandInputField, sizeInputField, priceInputField;
    private RecyclerView recyclerView;
    private ShoeAdapter shoeAdapter;
    private ShoeDB shoeDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addShoeBtn = findViewById(R.id.addRowBtn);
        deleteShoeBtn = findViewById(R.id.deleteRowBtn);
        refreshShoesBtn = findViewById(R.id.refreshRowsBtn);

        nameInputField = findViewById(R.id.name_inputField);
        brandInputField = findViewById(R.id.brand_inputField);
        sizeInputField = findViewById(R.id.size_inputField);
        priceInputField = findViewById(R.id.price_inputField);

        recyclerView = findViewById(R.id.shoeRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        shoeDB = Room.databaseBuilder(getApplicationContext(), ShoeDB.class, "ShoeDB").build();

        addShoeBtn.setOnClickListener(v -> {
            String name = nameInputField.getText().toString();
            String brand = brandInputField.getText().toString();
            String sizeStr = sizeInputField.getText().toString();
            String priceStr = priceInputField.getText().toString();

            if (name.isEmpty() || brand.isEmpty() || sizeStr.isEmpty() || priceStr.isEmpty()) {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                double size = Double.parseDouble(sizeStr);
                double price = Double.parseDouble(priceStr);
                Shoe newShoe = new Shoe(name, brand, size, price);
                new Thread(() -> shoeDB.getShoeDAO().addShoe(newShoe)).start();
                clearInputFields();
                refreshShoes();
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Invalid size or price", Toast.LENGTH_SHORT).show();
            }
        });

        refreshShoesBtn.setOnClickListener(v -> refreshShoes());

        deleteShoeBtn.setOnClickListener(v -> {
            if (shoeAdapter != null && shoeAdapter.getItemCount() > 0) {
                Shoe shoeToDelete = shoeAdapter.getShoeAt(0); // Удаление первого элемента - нужно изменить на выбор пользователя
                new Thread(() -> shoeDB.getShoeDAO().deleteShoe(shoeToDelete)).start();
                refreshShoes();
            }
        });
    }

    private void clearInputFields() {
        nameInputField.setText("");
        brandInputField.setText("");
        sizeInputField.setText("");
        priceInputField.setText("");
    }

    private void refreshShoes() {
        new Thread(() -> {
            List<Shoe> shoes = shoeDB.getShoeDAO().getAllShoes();
            shoeAdapter = new ShoeAdapter(shoes);
            runOnUiThread(() -> recyclerView.setAdapter(shoeAdapter));
        }).start();
    }
}