package com.stu.doantinhoc.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stu.doantinhoc.Controller.AdminController.FoodController;
import com.stu.doantinhoc.Model.Category;
import com.stu.doantinhoc.Model.Food;
import com.stu.doantinhoc.R;
import com.stu.doantinhoc.Util.ListData;

import java.util.ArrayList;


public class Category_foods_horizontal extends RecyclerView.Adapter<Category_foods_horizontal.ViewHoder> {

    IUpdateVerticalFoodsFgm iUpdateVerticalFoodsFgm;
    Activity activity;
    ArrayList<Category> listCate;

    boolean selected = true;
    boolean checked = true;
    int row_index = -1;


    public Category_foods_horizontal(IUpdateVerticalFoodsFgm iUpdateVerticalFoodsFgm, Activity activity, ArrayList<Category> listCate) {
        this.iUpdateVerticalFoodsFgm = iUpdateVerticalFoodsFgm;
        this.activity = activity;
        this.listCate = listCate;
    }

    @NonNull
    @Override
    public ViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHoder(LayoutInflater.from(parent.getContext()).inflate(R.layout.foods_cate_horizontal_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoder holder, int position) {
        holder.imageView.setImageResource(R.drawable.pizza);
        holder.name.setText(listCate.get(position).getCateName());
        if (checked) {
            ArrayList<Food> list = new ArrayList<>();
            try {
                Category cate = ListData.listCate.get(0);
                for (Food food:ListData.listFood) {
                    if(food.getCategory()== cate.getId())
                        list.add(food);
                }
                iUpdateVerticalFoodsFgm.callBack(position, list);
                checked = false;
            }catch (Exception e){
                Toast.makeText(activity,e.toString(),Toast.LENGTH_SHORT).show();
            }

        }
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                row_index = position;
                notifyDataSetChanged();
                ArrayList<Food> list = new ArrayList<>();
                try {
                    Category cate = ListData.listCate.get(position);
                    for (Food food:ListData.listFood) {
                        if(food.getCategory()== cate.getId())
                            list.add(food);
                    }
                    iUpdateVerticalFoodsFgm.callBack(position, list);
                }catch (Exception e){
                    Toast.makeText(activity,e.toString(),Toast.LENGTH_SHORT).show();
                }
            }
        });
        if (selected) {
            if (position == 0)
                holder.layout.setBackgroundResource(R.drawable.change_bg_foods_horizontal_cate);
            selected = false;
        }
        else if (row_index == position)
            holder.layout.setBackgroundResource(R.drawable.change_bg_foods_horizontal_cate);
        else
            holder.layout.setBackgroundResource(R.drawable.default_bg_foods_horizontal_cate);


    }

    @Override
    public int getItemCount() {
        return listCate.size();
    }

    public class ViewHoder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView name;
        LinearLayout layout;

        public ViewHoder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.img_cate_foods);
            name = itemView.findViewById(R.id.name_cate_foods);
            layout = itemView.findViewById(R.id.lnlCardViewCate);
        }
    }
}
