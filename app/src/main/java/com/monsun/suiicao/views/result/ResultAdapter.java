package com.monsun.suiicao.views.result;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.monsun.suiicao.databinding.ItemTongKetBinding;
import com.monsun.suiicao.models.Result;

import java.util.ArrayList;
import java.util.List;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ViewHolder> {
    private List<Result> list = new ArrayList<>();

    public ResultAdapter(List<Result> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemTongKetBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.setData(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemTongKetBinding binding;
        public ViewHolder(@NonNull ItemTongKetBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
