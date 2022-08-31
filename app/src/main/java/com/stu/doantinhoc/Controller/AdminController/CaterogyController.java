package com.stu.doantinhoc.Controller.AdminController;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Base64;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.stu.doantinhoc.Activities.Components.MsgDialog;
import com.stu.doantinhoc.Adapter.CategoryAdaptorRCV;
import com.stu.doantinhoc.Model.Category;
import com.stu.doantinhoc.R;
import com.stu.doantinhoc.RequestHttp.CategoryRequestHttp;
import com.stu.doantinhoc.Util.ListData;
import com.stu.doantinhoc.Util.Url;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class CaterogyController{
    private Context context;
    private RecyclerView recyclerViewCate;
    private CategoryRequestHttp requestHttp;
    private CategoryAdaptorRCV adaptorRCV;

    public CaterogyController(Context context, RecyclerView recyclerViewCate) {
        this.context = context;
        this.recyclerViewCate = recyclerViewCate;
        this.requestHttp = new CategoryRequestHttp(context);
    }
    public CaterogyController(Context context){
        this.context = context;
        this.requestHttp = new CategoryRequestHttp(context);
    }

    public void loadRCVCate() {
        adaptorRCV = new CategoryAdaptorRCV(context, ListData.listCate, recyclerViewCate);
        recyclerViewCate.setAdapter(adaptorRCV);
        recyclerViewCate.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
        recyclerViewCate.setHasFixedSize(true);
        recyclerViewCate.setNestedScrollingEnabled(false);
    }

    public void addCateToData(String name, String pic) {
        if (name.isEmpty()) {
            MsgDialog.showDialog(context, "Name is empty!");
            return;
        }
//        StringBuilder url = new StringBuilder(Url.URLINSERTCATE);
//        url.append("&catename=");
//        url.append(name);
//        requestHttp.insertAndDeleteAndUpdate(url.toString(), pic);
        requestHttp.insertCategory(name,pic);
        int id = ListData.listCate.get(ListData.listCate.size() - 1).getId() + 1;
        ListData.listCate.add(new Category(id, name, ""));
        MsgDialog.showDialog(context, "Successfully!");
    }

    public void delCateInDB(int id) {
        StringBuilder url = new StringBuilder(Url.URLDELETECATE);
        url.append("&ID=");
        url.append(id);
        requestHttp.insertAndDeleteAndUpdate(url.toString());
    }


    public void updateCate(Category category,String name) {
        StringBuilder url = new StringBuilder(Url.URLUPDATECATE);
        url.append("&ID=");
        url.append(category.getId());
        url.append("&catename=");
        url.append(name);
        Toast.makeText(context,url.toString(),Toast.LENGTH_SHORT).show();
        requestHttp.insertAndDeleteAndUpdate(url.toString());
        for (Category cate:ListData.listCate) {
            if(cate.getId()==category.getId())
                    cate.setCateName(name);
        }
        loadRCVCate();
    }

}
