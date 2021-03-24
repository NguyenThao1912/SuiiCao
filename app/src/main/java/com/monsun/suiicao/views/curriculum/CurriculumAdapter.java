package com.monsun.suiicao.views.curriculum;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.monsun.suiicao.R;
import com.monsun.suiicao.models.Curriculum;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class CurriculumAdapter extends RecyclerView.Adapter<CurriculumAdapter.ViewHolder> {

    private List<Curriculum> list;
    //thu
    private List<Boolean> isexpandable ;
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
        holder.lectureName.setText(list.get(position).getCourseName().trim());
        holder.lectureid.setText(list.get(position).getCourseId().trim());
        holder.credit.setText(list.get(position).getCourseCredit().trim());
        holder.practical.setText(list.get(position).getCoursePractical().trim());
        holder.theory.setText(list.get(position).getCourseTheory().trim());
        holder.discuss.setText( list.get(position).getDiscuss().trim());
        holder.semester.setText( list.get(position).getSemester().trim());
        holder.relativeLayout.setVisibility(list.get(position).isIsexpandable()?View.VISIBLE:View.GONE);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Curriculum c =  list.get(position);
                c.setIsexpandable(!c.isIsexpandable());
                notifyItemChanged(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        //thụt thò
        int n = list.size();
        isexpandable = new ArrayList<>(Collections.nCopies(n, false));
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView lectureName,credit,theory,practical,discuss,semester,lectureid;
        LinearLayout linearLayout;
        RelativeLayout relativeLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lectureName = itemView.findViewById(R.id.lecture_name);
            credit = itemView.findViewById(R.id.lecture_credit);
            theory = itemView.findViewById(R.id.lecture_theory);
            practical = itemView.findViewById(R.id.lecture_practical);
            discuss = itemView.findViewById(R.id.lecture_discuss);
            semester = itemView.findViewById(R.id.lecture_semester);
            lectureid = itemView.findViewById(R.id.lecture_id);
            linearLayout = itemView.findViewById(R.id.lecture_linear);
            relativeLayout = itemView.findViewById(R.id.lecture_expanable);

        }
    }
}
