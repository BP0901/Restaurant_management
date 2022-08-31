package com.stu.doantinhoc.Activities.Admin.ui.FoodMenu.Components;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.stu.doantinhoc.Controller.AdminController.FoodController;
import com.stu.doantinhoc.Model.Category;
import com.stu.doantinhoc.Util.ListData;
import com.stu.doantinhoc.databinding.ActivityAddFoodBinding;

import java.util.ArrayList;

public class AddFoodActivity extends AppCompatActivity {

    private ActivityAddFoodBinding binding;
    private Button btnAdd;
    private Button btnCancel;
    private Spinner spnCate;
    private FoodController controller;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddFoodBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        init();
    }

    private void init() {
        btnAdd = binding.btnAddFood;
        btnCancel = binding.btnCancelAddFood;
        spnCate = binding.spinnerAddCategories;
        controller = new FoodController(AddFoodActivity.this);
        initSpinnerCate();
        setEvent();
    }

    private void setEvent() {
        btnCancel.setOnClickListener(v -> super.onBackPressed());
        btnAdd.setOnClickListener(v -> {
            controller.addFoodToDB(
                    binding.edtAddFoodname.getText().toString().trim(),
                    binding.edtAddFoodPrice.getText().toString().trim(),
                    binding.edtAddFoodDiscount.getText().toString().trim(),
                    ListData.listCate.get(spnCate.getSelectedItemPosition()).getId(),
                    ""
            );
        });
    }

    private void initSpinnerCate() {
        ArrayList<String> cateNames = new ArrayList<>();
        for (Category cate : ListData.listCate) {
            cateNames.add(cate.getCateName());
        }
        ArrayAdapter adapter = new ArrayAdapter(AddFoodActivity.this, android.R.layout.simple_spinner_item, cateNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnCate.setAdapter(adapter);
    }
}