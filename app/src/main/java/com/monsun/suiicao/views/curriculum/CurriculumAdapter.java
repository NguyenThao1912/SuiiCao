package com.monsun.suiicao.views.curriculum;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
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


public class CurriculumAdapter extends RecyclerView.Adapter<CurriculumAdapter.ViewHolder> implements Filterable {

    private List<Curriculum> list;
    private List<Curriculum> filterlist;
    private List<Curriculum> unstudylist;
    //thu
    private List<Boolean> isexpandable ;
    public CurriculumAdapter(List<Curriculum> mlist,List<Curriculum> unstudy)
    {
        this.unstudylist = unstudy;
        list = mlist;
        filterlist = mlist;
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

       for(Curriculum curriculum: unstudylist){
           if (curriculum.getCourseId() == list.get(position).getCourseId()) {
               holder.imageView.setVisibility(View.GONE);
               break;
           }
       }

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

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                constraint = constraint.toString().toLowerCase();
                FilterResults results = new FilterResults();
                List<Curriculum> filter = new ArrayList<>();
                if (constraint.equals(null) || constraint.equals(""))
                {
                    results.values = filterlist;
                    return results;
                }
                else
                {
                    for (Curriculum r:list) {
                        if (r.getCourseName().toLowerCase().contains(constraint))
                        {
                            filter.add(r);
                        }

                    }

                }
                results.values = filter;
                return results;
            }
            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                List<Curriculum> curriculumList = (List<Curriculum>) results.values;
                list = curriculumList;
                notifyDataSetChanged();
            }
        };
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView lectureName,credit,theory,practical,discuss,semester,lectureid;
        LinearLayout linearLayout;
        RelativeLayout relativeLayout;
        public ImageView imageView;
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
            imageView = itemView.findViewById(R.id.curriculum_study);
        }
    }
}
