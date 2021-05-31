package com.monsun.suiicao.views.timetable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.monsun.suiicao.R;
import com.monsun.suiicao.models.Schedule;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.ViewHolder> {

    private List<Schedule> scheduleList;
    private Calendar calendar;

    public SubjectAdapter(List<Schedule> scheduleList,Calendar calendar) {
        this.scheduleList = scheduleList;
        this.calendar = calendar;
    }

    private List<Boolean> isexpandable ;

    @NonNull
    @Override
    public SubjectAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.timetable_item,parent,false);
        return new SubjectAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectAdapter.ViewHolder holder, int position) {
        String shift = scheduleList.get(position).getShift();
        int start = Integer.parseInt(shift);
        String time = "";
        switch (start){
            case 1:
                time = "07:00";
                break;
            case 2:
                time = "09:30";
                break;
            case 3:
                time = "13:00";
                break;
            case 4:
                time = "15:30";
                break;
            case 5:
                time = "10:20";
                break;
            case 6:
                time = "11:10";
                break;
            case 7:
                time = "13:00";
                break;
            case 8:
                time = "13:50";
                break;
            case 9:
                time = "14:45";
                break;
            case 10:
                time = "15:25";
                break;
            case 11:
                time = "16:15";
                break;
            case 12:
                time = "17:05";
                break;
        }
        String hour = time.substring(0,2);
        String minute = time.substring(3,5);
        Calendar date = calendar;
        int day = date.get(Calendar.DAY_OF_MONTH);
        int month = date.get(Calendar.MONTH);
        int year = date.get(Calendar.YEAR);
        int day_in_week = date.get(Calendar.DAY_OF_WEEK);
        String txt = "Thứ " + day_in_week + ", " + day + "/" + month + "/" + year;
        holder.date.setText(txt);
        holder.subjectTitle.setText(scheduleList.get(position).getName());
        holder.subjectRoom.setText(scheduleList.get(position).getLocation());
        holder.subjectHour.setText(hour);
        holder.subjectMinute.setText(minute);
        holder.linearLayout2.setVisibility(scheduleList.get(position).isIsexpandable()?View.VISIBLE:View.GONE);
        holder.linearLayout1.setOnClickListener(view -> {
            Schedule c =  scheduleList.get(position);
            c.setIsexpandable(!c.isIsexpandable());
            notifyItemChanged(position);
        });

    }

    @Override
    public int getItemCount() {
        //thụt thò
        int n = scheduleList.size();
        isexpandable = new ArrayList<>(Collections.nCopies(n, true));
        return scheduleList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView subjectTitle;
        public final TextView subjectRoom;
        public final TextView subjectHour;
        public final TextView subjectMinute;
        public final TextView date;
        public final LinearLayout linearLayout1;
        public final LinearLayout linearLayout2;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            subjectTitle =  itemView.findViewById(R.id.tvSubjectTitle1);
            subjectRoom =  itemView.findViewById(R.id.tvSubjectRoom1);
            subjectHour =  itemView.findViewById(R.id.tvSubjectHour1);
            subjectMinute =  itemView.findViewById(R.id.tvSubjectMinute1);
            date =  itemView.findViewById(R.id.tvDate);
            linearLayout1 =  itemView.findViewById(R.id.schedule_linear);
            linearLayout2 =  itemView.findViewById(R.id.layoutSubject);
        }
    }
    public interface GetThing{
        Calendar getdate(int position);
    }
}
