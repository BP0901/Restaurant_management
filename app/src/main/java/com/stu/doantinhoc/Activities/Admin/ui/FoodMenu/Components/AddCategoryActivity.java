package com.stu.doantinhoc.Activities.Admin.ui.FoodMenu.Components;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.stu.doantinhoc.Controller.AdminController.CaterogyController;
import com.stu.doantinhoc.databinding.ActivityAddCategoryBinding;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class AddCategoryActivity extends AppCompatActivity {

    private ActivityAddCategoryBinding binding;
    private CaterogyController controller;
    private Button btnSave;
    private Button btnCancel;
    private Bitmap bitmap;
    private boolean flag = false;
    private Button btnSelectPicCate;
    private ImageView img;
    private EditText catename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddCategoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        init();
    }

    private void init() {
        controller = new CaterogyController(AddCategoryActivity.this);
        btnCancel = binding.btnCancelAddCate;
        btnSave = binding.btnAddCate;
        catename = binding.edtAddCateName;
        img = binding.imgAddCatePic;
        btnSelectPicCate = binding.btnSelectPicCate;
        setEvent();
    }

    private void setEvent() {
        btnCancel.setOnClickListener(v -> super.onBackPressed());
        btnSave.setOnClickListener(v -> {
            if (!flag)
                controller.addCateToData(catename.getText().toString().trim(), encodeBitmapImage(bitmap));
            else
                controller.addCateToData(catename.getText().toString().trim(), "");
            catename.setText("");

        });
        btnSelectPicCate.setOnClickListener(v -> {
            Dexter.withActivity(AddCategoryActivity.this)
                    .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    .withListener(new PermissionListener() {
                        @Override
                        public void onPermissionGranted(PermissionGrantedResponse response) {
                            Intent intent = new Intent(Intent.ACTION_PICK);
                            intent.setType("image/*");
                            startActivityForResult(Intent.createChooser(intent, "Browse Image"), 1);
                        }

                        @Override
                        public void onPermissionDenied(PermissionDeniedResponse response) {

                        }

                        @Override
                        public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                            token.continuePermissionRequest();
                        }
                    }).check();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Uri filepath = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(filepath);
                bitmap = BitmapFactory.decodeStream(inputStream);
                img.setImageBitmap(bitmap);
                encodeBitmapImage(bitmap);
                flag = true;
            } catch (Exception ex) {

            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private String encodeBitmapImage(Bitmap bitmap) {
        String encodeImageString;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] bytesofimage = byteArrayOutputStream.toByteArray();
        encodeImageString = android.util.Base64.encodeToString(bytesofimage, Base64.DEFAULT);
        return encodeImageString;
    }
}