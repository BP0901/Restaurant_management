package com.stu.doantinhoc.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stu.doantinhoc.Controller.AdminController.FoodController;
import com.stu.doantinhoc.Controller.AdminController.UserController;
import com.stu.doantinhoc.Model.Category;
import com.stu.doantinhoc.Model.Food;
import com.stu.doantinhoc.R;
import com.stu.doantinhoc.Util.ListData;

import java.util.ArrayList;
import java.util.List;


public class food_item_card_adaptor extends RecyclerView.Adapter<food_item_card_adaptor.ViewHoder> {

    Context context;
    ArrayList<Food> foodList;

    public food_item_card_adaptor(Context context, ArrayList<Food> foodList) {
        this.context = context;
        this.foodList = foodList;
    }

    @NonNull
    @Override
    public ViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new food_item_card_adaptor.ViewHoder(LayoutInflater.from(parent.getContext()).inflate(R.layout.foods_vertical_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoder holder, int position) {
        try {
            holder.imageView.setImageResource(R.drawable.pizza_pic);
            holder.name.setText(foodList.get(position).getName().toString());
            holder.discount.setText("");
            if (foodList.get(position).getDiscount() == 0) {
                holder.price.setText(String.valueOf(foodList.get(position).getPrice()));
            } else {
                holder.price.setText(String.valueOf(foodList.get(position).getPrice()));
                holder.discount.setText(String.valueOf(foodList.get(position).getDiscount()));
                holder.price.setPaintFlags(holder.price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            }
        } catch (Exception e) {

        }
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public class ViewHoder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name;
        TextView price;
        TextView discount;

        public ViewHoder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.foods_img_food);
            name = itemView.findViewById(R.id.food_item_name);
            price = itemView.findViewById(R.id.food_item_price);
            discount = itemView.findViewById(R.id.food_item_pricediscount);
        }
    }

}
