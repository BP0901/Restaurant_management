package com.stu.doantinhoc.Controller.AdminController;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.stu.doantinhoc.Activities.Admin.ui.FoodMenu.Components.AddFoodActivity;
import com.stu.doantinhoc.Activities.Components.MsgDialog;
import com.stu.doantinhoc.Adapter.Category_foods_horizontal;
import com.stu.doantinhoc.Adapter.IUpdateVerticalFoodsFgm;
import com.stu.doantinhoc.Adapter.food_item_card_adaptor;
import com.stu.doantinhoc.Model.Category;
import com.stu.doantinhoc.Model.Food;
import com.stu.doantinhoc.RequestHttp.FoodRequestHttp;
import com.stu.doantinhoc.Util.ListData;
import com.stu.doantinhoc.Util.Url;

import java.util.ArrayList;

public class FoodController {
    private Context context;
    private Activity activity;
    private RecyclerView rcvH, rcvV;
    private food_item_card_adaptor foodAdaptor;


    public FoodController(Context context, Activity activity, RecyclerView rcvH, RecyclerView rcvV) {
        this.context = context;
        this.activity = activity;
        this.rcvH = rcvH;
        this.rcvV = rcvV;
    }

    public FoodController(Context context) {
        this.context = context;
    }

    //region Add food

    public void addFoodToDB(String name, String price, String discount, int category, String picture) {
        if (isVliad(name, price, discount)) {
            int pc = Integer.parseInt(price);
            int dis = Integer.parseInt((discount));
            StringBuilder url = new StringBuilder(Url.URLINSERTFOOD);
            url.append("&name=");
            url.append(name);
            url.append("&price=");
            url.append(pc);
            url.append("&discount=");
            url.append(dis);
            url.append("&picture=");
            url.append(picture);
            url.append("&category=");
            url.append(category);
            FoodRequestHttp requestHttp = new FoodRequestHttp(context);
            requestHttp.insertAndDeleteAndUpdate(url.toString());
            int id = ListData.listFood.get(ListData.listFood.size() - 1).getId() + 1;
            Food food = new Food(id, name, pc, dis, picture, category);
            ListData.listFood.add(food);
            MsgDialog.showDialog(context, "Add Successfully!");
        }

    }

    private boolean isVliad(String name, String price, String discount) {
        if (name.isEmpty()) {
            MsgDialog.showDialog(context, "Name is empty!");
            return false;
        }
        if (price.isEmpty()) {
            MsgDialog.showDialog(context, "Price is empty!");
            return false;
        }
        if (discount.isEmpty()) {
            MsgDialog.showDialog(context, "Discount is empty!");
            return false;
        }
        return true;
    }

    //endregion

    //region Delete food

    public void delFood(Food food) {
        StringBuilder url = new StringBuilder(Url.URLDELETEFOOD);
        url.append("&id=");
        url.append(food.getId());
        FoodRequestHttp requestHttp = new FoodRequestHttp(context);
        requestHttp.insertAndDeleteAndUpdate(url.toString());
        ListData.listFood.remove(food);
    }

    //endregion

    //region upadte food

    public void updateFood(Food food, String name, String price, String discount, int category, String picture) {
        for (int i = 0; i < ListData.listFood.size();i++){
            if (ListData.listFood.get(i).equals(food)) {
                ListData.listFood.get(i).setName(name);
                ListData.listFood.get(i).setPrice(Integer.parseInt(price));
                ListData.listFood.get(i).setPrice(Integer.parseInt(discount));
                ListData.listFood.get(i).setCategory(category);
            }
        }
        StringBuilder url = new StringBuilder(Url.URLUPDATEFOOD);
        url.append("&id=");
        url.append(food.getId());
        url.append("&name=");
        url.append(name);
        url.append("&price=");
        url.append(price);
        url.append("&discount=");
        url.append(discount);
        url.append("&category=");
        url.append(category);
        url.append("&picture=");
        url.append(picture);
        FoodRequestHttp requestHttp = new FoodRequestHttp(context);
        requestHttp.insertAndDeleteAndUpdate(url.toString());
    }

    //endregion

}
