package com.stu.doantinhoc.Controller;

import android.content.Context;
import android.content.Intent;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.stu.doantinhoc.Activities.Admin.MainMenuActivity;
import com.stu.doantinhoc.Activities.Components.MsgDialog;
import com.stu.doantinhoc.Activities.Staff.MainStaffActivity;
import com.stu.doantinhoc.Model.Category;
import com.stu.doantinhoc.Model.Food;
import com.stu.doantinhoc.Model.Table;
import com.stu.doantinhoc.Model.User;
import com.stu.doantinhoc.RequestHttp.CategoryRequestHttp;
import com.stu.doantinhoc.RequestHttp.FoodRequestHttp;
import com.stu.doantinhoc.RequestHttp.UserRequestHttp;
import com.stu.doantinhoc.Util.ListData;
import com.stu.doantinhoc.Util.Url;

import java.util.ArrayList;

public class LoginController {
    private Context context;
    private UserRequestHttp userRequestHttp;
    private ArrayList<User> listUser;

    public LoginController(Context context) {
        this.context = context;
        this.userRequestHttp = new UserRequestHttp(context);
        this.listUser = this.userRequestHttp.getData(Url.URLGETALLUSER);
        ListData.listUser = listUser;
    }


    public void actionLogin(String username, String password) {
        if (username.isEmpty()) {
            MsgDialog.showDialog(context, "Nhập vào username!");
            return;
        } else if (password.isEmpty()) {
            MsgDialog.showDialog(context, "Nhập vào password!");
            return;
        }
        for (User user : listUser) {
            if (user.getUsername().equals(username))
                if (user.getPassword().equals(password))
                    if (user.isType()) {
                        context.startActivity(new Intent(context, MainMenuActivity.class));
                    } else {
                        context.startActivity(new Intent(context, MainStaffActivity.class));
                    }
                else {
                    MsgDialog.showDialog(context, "Sai mật khẩu hoặc tài khoản!");
                    return;
                }

        }

    }


    public void loadDataFromDB() {
        if (ListData.FistRun) {

            CategoryRequestHttp requestHttp = new CategoryRequestHttp(context);
            requestHttp.getData(Url.URLGETALLCATE);

            FoodRequestHttp foodRequestHttp = new FoodRequestHttp(context);
            foodRequestHttp.getData(Url.URLGETALLFOOD);

            for (int i = 1; i <= 15; i++) {
                ListData.listTable.add(new Table("Table " + i, new ArrayList<>()));
            }

            ListData.FistRun = false;
        }
    }
}
