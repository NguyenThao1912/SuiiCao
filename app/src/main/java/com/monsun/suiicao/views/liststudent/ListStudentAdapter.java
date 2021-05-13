package com.monsun.suiicao.views.liststudent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.monsun.suiicao.R;
import com.monsun.suiicao.models.Users;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ListStudentAdapter extends RecyclerView.Adapter<ListStudentAdapter.ViewHolder> implements Filterable {
    private List<Users> list;
    private List<Users> listAll;
    private OnItemCheckListener onItemCheckListener;
    public ListStudentAdapter(List<Users> mlist, OnItemCheckListener itemCheckListener)
    {
        list = mlist;
        listAll = mlist;
        this.onItemCheckListener = itemCheckListener;
    }

    @NonNull
    @Override
    public ListStudentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.liststudent_row,parent,false);
        return new ListStudentAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListStudentAdapter.ViewHolder holder, int position) {
        if (list.get(position).isCheckstatus())
            holder.status.setChecked(list.get(position).isCheckstatus());
        else
            holder.status.setChecked(false);
        holder.student_name.setText(list.get(position).getFullName());
        holder.student_email.setText(list.get(position).getEmail());
        //holder.imageView.setImageURI(list.get(position).getImg());
        holder.status.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    onItemCheckListener.onItemCheck(list.get(position));
                } else {
                    onItemCheckListener.onItemUnCheck(list.get(position));
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            //run on background thread
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                List<Users> filterlist = new ArrayList<>();
                if (constraint.toString().isEmpty())
                {
                    filterlist = listAll;
                }
                else {
                    for (Users users : listAll)
                    {
                        if (users.getFullName().toLowerCase().contains(constraint.toString().toLowerCase()))
                        {
                            filterlist.add(users);
                        }
                    }
                }
                FilterResults results = new FilterResults();
                results.values = filterlist;
                results.count = filterlist.size();
                return results;
            }
            //run on ui thread
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                list = (List<Users>) results.values;
                notifyDataSetChanged();
            }
        };
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements CompoundButton.OnCheckedChangeListener {
        public TextView student_name,student_email;
        public CircleImageView imageView;
        public CheckBox status;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            student_name = itemView.findViewById(R.id.list_student_name);
            student_email = itemView.findViewById(R.id.list_student_email);
            imageView = itemView.findViewById(R.id.list_student_img);
            status = itemView.findViewById(R.id.list_student_status);
            status.setOnCheckedChangeListener(this);
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        }
    }
    interface OnItemCheckListener {
        void onItemCheck(Users users);
        void onItemUnCheck(Users users);
    }
}
