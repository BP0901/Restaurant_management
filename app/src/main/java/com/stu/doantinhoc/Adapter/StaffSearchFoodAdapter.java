package com.stu.doantinhoc.Adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.stu.doantinhoc.Activities.Components.MsgDialog;
import com.stu.doantinhoc.Controller.AdminController.TableController;
import com.stu.doantinhoc.Model.Food;
import com.stu.doantinhoc.R;

import java.util.ArrayList;

public class StaffSearchFoodAdapter extends RecyclerView.Adapter<StaffSearchFoodAdapter.ViewHolder> implements Filterable {
    private ArrayList<Food> listFood;
    private ArrayList<Food> listFoodOld;
    private Context context;

    public StaffSearchFoodAdapter(ArrayList<Food> listFood, Context context) {
        this.listFood = listFood;
        this.listFoodOld = listFood;
        this.context = context;
    }

    @NonNull
    @Override
    public StaffSearchFoodAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new StaffSearchFoodAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.add_food_to_table_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(listFood.get(position).getName());
        holder.discount.setText("");
        if (listFood.get(position).getDiscount() == 0) {
            holder.price.setText(String.valueOf(listFood.get(position).getPrice()));
        } else {
            holder.price.setText(String.valueOf(listFood.get(position).getPrice()));
            holder.discount.setText(String.valueOf(listFood.get(position).getDiscount()));
            holder.price.setPaintFlags(holder.price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }
    }



    @Override
    public int getItemCount() {
        return listFood.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String strsearch = constraint.toString();
                if (strsearch.isEmpty()) {
                    listFood = listFoodOld;
                } else {
                    ArrayList<Food> list = new ArrayList<>();
                    for (Food food : listFoodOld) {
                        if (food.getName().toLowerCase().contains(strsearch.toLowerCase())) {
                            list.add(food);
                        }

                    }
                    listFood = list;
                }
                FilterResults results = new FilterResults();
                results.values = listFood;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                listFood = (ArrayList<Food>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView price;
        TextView discount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nameAddFoodRow);
            price = itemView.findViewById(R.id.priceAddFoodRow);
            discount = itemView.findViewById(R.id.discountAddFoodRow);
        }
    }

}
