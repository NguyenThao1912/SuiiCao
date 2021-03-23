package com.monsun.suiicao.views.curriculum;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.monsun.suiicao.R;
import com.monsun.suiicao.models.Curriculum;

import java.util.List;


public class CurriculumAdapter extends RecyclerView.Adapter<CurriculumAdapter.ViewHolder> {

    private List<Curriculum> list;
    public CurriculumAdapter(List<Curriculum> mlist)
    {
        list = mlist;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.curriculum_row_item,parent,false);
        return new CurriculumAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.lectureName.setText(list.get(position).getCourseName());
        holder.lectureid.setText(list.get(position).getCourseId());
        holder.credit.setText(list.get(position).getCourseCredit());
        holder.theory.setText(list.get(position).getCourseTheory());
        holder.discuss.setText( list.get(position).getDiscuss());
        holder.semester.setText( list.get(position).getSemester());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView lectureName,credit,theory,practical,discuss,semester,lectureid;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lectureName = itemView.findViewById(R.id.lecture_name);
            credit = itemView.findViewById(R.id.lecture_credit);
            theory = itemView.findViewById(R.id.lecture_theory);
            practical = itemView.findViewById(R.id.lecture_practical);
            discuss = itemView.findViewById(R.id.lecture_discuss);
            semester = itemView.findViewById(R.id.lecture_semester);
            lectureid = itemView.findViewById(R.id.lecture_id);

        }
    }
}
