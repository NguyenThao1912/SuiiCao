package com.monsun.suiicao.views.timetable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.monsun.suiicao.databinding.SelectSemesterRowBinding;
import com.monsun.suiicao.models.Semester;

import java.util.List;

public class SemesterAdapter extends RecyclerView.Adapter<SemesterAdapter.ViewHolder> {
    private List<Semester> list;
    private int mSelectedItem = -1;
    private onItemListener onItemListener;
    public SemesterAdapter(List<Semester> list,onItemListener onItemListener){
        this.list = list;
        this.onItemListener = onItemListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(SelectSemesterRowBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent,false
        ),onItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.semesterName.setText(list.get(position).getSemesterName());
        holder.binding.semesterSelection.setChecked(position == mSelectedItem);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private SelectSemesterRowBinding binding;
        private onItemListener onItemListener;
        public ViewHolder(@NonNull SelectSemesterRowBinding itemView,onItemListener onItemListener) {
            super(itemView.getRoot());
            this.binding = itemView;
            this.onItemListener = onItemListener;
            binding.semesterSelection.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mSelectedItem = getAdapterPosition();
            notifyDataSetChanged();
            onItemListener.onRadioButtonClick(mSelectedItem);
        }
    }

    public interface onItemListener{
        void onRadioButtonClick(int position);
    }
}
