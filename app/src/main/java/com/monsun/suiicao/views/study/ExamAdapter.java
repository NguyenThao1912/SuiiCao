package com.monsun.suiicao.views.study;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.monsun.suiicao.R;
import com.monsun.suiicao.models.CourseExam;

import java.util.List;

public class ExamAdapter extends RecyclerView.Adapter<ExamAdapter.ViewHolder> {

    private List<CourseExam> courseExamList;

    public ExamAdapter(List<CourseExam> courseExamList) {
        this.courseExamList = courseExamList;
    }

    @NonNull
    @Override
    public ExamAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.examday_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExamAdapter.ViewHolder holder, int position) {
        holder.coursename.setText(courseExamList.get(position).getCourseName());
        holder.examday.setText("Ngày Thi : "+courseExamList.get(position).getDate());
        holder.examshift.setText("Ca thi : "+courseExamList.get(position).getShift());
        holder.examformat.setText("Hình thức thi : " + courseExamList.get(position).getFormat());
        holder.exam_student_id.setText("Số Báo Danh : " + courseExamList.get(position).getSBD());
        holder.exam_location.setText("Địa Điểm :" + courseExamList.get(position).getLocation());
    }

    @Override
    public int getItemCount() {
        return courseExamList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView coursename;
        public final TextView examday;
        public final TextView examshift;
        public final TextView examformat;
        public final TextView exam_student_id;
        public final TextView exam_location;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            coursename = (TextView) itemView.findViewById(R.id.coursename);
            examday = (TextView) itemView.findViewById(R.id.examday);
            examshift = (TextView) itemView.findViewById(R.id.examshift);
            examformat = (TextView) itemView.findViewById(R.id.examformat);
            exam_student_id = (TextView) itemView.findViewById(R.id.exam_student_id);
            exam_location = (TextView) itemView.findViewById(R.id.exam_location);
        }
    }
}
