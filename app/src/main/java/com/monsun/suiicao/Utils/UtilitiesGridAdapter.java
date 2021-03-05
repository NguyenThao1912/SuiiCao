package com.monsun.suiicao.Utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.monsun.suiicao.R;
import com.monsun.suiicao.models.Utilities;

import java.util.List;

public class



UtilitiesGridAdapter extends RecyclerView.Adapter<UtilitiesGridAdapter.ViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    List<Utilities> listItem;

    public UtilitiesGridAdapter(Context context, List<Utilities> listItem) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.listItem = listItem;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.grid_item_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(listItem.get(position).getUtiIcon()).into(holder.icon);
        holder.item_menu.setText(listItem.get(position).getUtiName());
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView item_menu;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            icon =  itemView.findViewById(R.id.menu_icon_stu);
            item_menu = itemView.findViewById(R.id.menu_item_stu);
        }
    }
}
