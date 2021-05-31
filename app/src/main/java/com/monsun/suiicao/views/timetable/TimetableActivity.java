package com.monsun.suiicao.views.timetable;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.DatePicker;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.builders.DatePickerBuilder;
import com.applandeo.materialcalendarview.listeners.OnSelectDateListener;
import com.monsun.suiicao.AppVar;
import com.monsun.suiicao.R;
import com.monsun.suiicao.databinding.ActivityTimetableBinding;
import com.monsun.suiicao.databinding.DialogSelectSemesterBinding;
import com.monsun.suiicao.models.Schedule;
import com.monsun.suiicao.models.Semester;
import com.monsun.suiicao.repositories.ApiInstance;
import com.monsun.suiicao.views.base.BaseActivity;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TimetableActivity extends BaseActivity implements OnSelectDateListener,ITimetable,SemesterAdapter.onItemListener,View.OnClickListener{

    private static final String TAG = "TimetableActivity";
    ActivityTimetableBinding binding;
    TimetableViewModel viewModel;
    DialogSelectSemesterBinding dialogbinding;
    private Dialog dialog;
    private List<Schedule> listschedules = new ArrayList<>();

    private List<Semester> semesters = new ArrayList<>();

    private Map<Calendar,List<Schedule>> scheduleMap = new HashMap<>();

    private List<Calendar> calendarSchedule = new ArrayList<>();
    private Semester currentSemester;

    public static Intent newIntent(Context context) {
        return new Intent(context, TimetableActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_timetable);
        viewModel = new ViewModelProvider(this).get(TimetableViewModel.class);
        viewModel.setNavigator(this);
        binding.setLifecycleOwner(this);
        binding.setViewModel(viewModel);
        setSupportActionBar(binding.timetableToolbar);
        setData();
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(TimetableActivity.this,LinearLayoutManager.VERTICAL,false);
        binding.listTimetable.setLayoutManager(linearLayoutManager);

        binding.calendarView.setOnDayClickListener(eventDay -> {
            Calendar calendar = eventDay.getCalendar();
            List<Schedule> scheduleList = scheduleMap.get(calendar);
            if (scheduleList != null){
                binding.listTimetable.setVisibility(View.VISIBLE);
                binding.listTimetable.setAdapter(null);
                binding.listTimetable.setAdapter(new SubjectAdapter(scheduleList,calendar));
                Log.d(TAG, "onCreate: " + scheduleList.size());
                Toast.makeText(TimetableActivity.this, "có " + scheduleList.size() + " môn", Toast.LENGTH_SHORT).show();
            }
            else
            {

                binding.listTimetable.setVisibility(View.GONE);
                Toast.makeText(TimetableActivity.this, "Hôm nay chả có gì cả ", Toast.LENGTH_SHORT).show();
            }

        });

        //Sau khi lấy lịch học về thì xử lý dữ liệu lịch học hàm tự gọi mỗi khi lịch học trong viewmodel thay đổi
        viewModel.getListSchedule().observe(this, schedules -> {
                    listschedules = schedules;
                    HandlerSchedule();
                    setEventSchedule();
                });
    }

    private void setEventSchedule() {
        List<EventDay> events = new ArrayList<>();
        for (Calendar calendar : calendarSchedule){
            //neeus lich nho hon ngay hien tai
            EventDay eventDay = new EventDay(calendar,R.drawable.star);
            events.add(eventDay);
           /* if (!events.contains(eventDay))
                events.add(eventDay);*/
        }
        binding.calendarView.setEvents(events);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.timetable_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void goBack() {
        finish();
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                finish();
                return true;
            }
            case R.id.select_timetable_semester:{
                OpenDialogSelectSemester();
                return true;
            }
            case R.id.select_day:{
                DatePickerBuilder builder = new DatePickerBuilder(this, this)
                        .pickerType(CalendarView.ONE_DAY_PICKER);

                DatePicker datePicker = builder.build();
                datePicker.show();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private  void OpenDialogSelectSemester()
    {
        dialog = new Dialog(this);
        dialogbinding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.dialog_select_semester, null, false);
        dialog.setContentView(dialogbinding.getRoot());
        dialog.setTitle("Chọn Kỳ học");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(TimetableActivity.this,
                LinearLayoutManager.VERTICAL,
                false);
        Log.d(TAG, "OpenDialogSelectSemester: " + semesters.size());
        dialogbinding.selectSemesterRv.setLayoutManager(linearLayoutManager);
        dialogbinding.selectSemesterRv.setAdapter(new SemesterAdapter(semesters,TimetableActivity.this));
        dialogbinding.submitSemester.setOnClickListener(this);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        dialog.getWindow().setAttributes(lp);
        dialog.create();
        dialog.show();
    }

    public void setData()
    {
        ApiInstance apiInstance = new ApiInstance();
        Call<List<Semester>> lsemester = apiInstance.getServices().getSemester();
        lsemester.enqueue(new Callback<List<Semester>>() {
            @Override
            public void onResponse(Call<List<Semester>> call, Response<List<Semester>> response) {
                if(!response.isSuccessful())
                {
                    Log.d(TAG, "onResponse: Get semester failed");
                }
                else
                {
                    semesters = response.body();

                }
            }
            @Override
            public void onFailure(Call<List<Semester>> call, Throwable t) {
                Log.d(TAG, "onResponse: Get semester failed " + t.getMessage() );
            }
        });
    }

    private void HandlerSchedule()
    {
        List<Date> exitsdate = new ArrayList<>();
        SimpleDateFormat formatter1 =new SimpleDateFormat("yyyy-MM-dd");
        try {
            for (Schedule schedule : listschedules){
                //Lấy ra ngày bắt đầu , ngày kết thúc

                String start = schedule.getDayStart().substring(0,10);
                String end = schedule.getDayEnd().substring(0,10);
                //Chuyển thành kiểu date
                Date startDate = formatter1.parse(start);
                Date endDate = formatter1.parse(end);
                //Tiếp tục chuyển sang kiểu calender
                Calendar startCalender = getDatePart(startDate);
                Calendar endCalender = getDatePart(endDate);
                //Lấy danh sách ngày giữa (Ngày bắt đầu) và (Ngày kết thúc)
                /* List<Calendar> daysRange = CalendarUtils.getDatesRange(startCalender,endCalender);*/

                if (schedule.getDayinweek() == null && schedule.getShift() == null)
                    continue;

                int day = Integer.parseInt(schedule.getDayinweek()) - 2 ;
                startCalender.add(Calendar.DATE,day);

                Calendar currentDate = startCalender;
                while (currentDate.before(endCalender)){
                    Date date = currentDate.getTime();
                    Calendar calendar = (Calendar) currentDate.clone();
                    //danh sách các ngày có lịch học
                    if (!exitsdate.contains(date))
                    {
                        calendarSchedule.add(calendar);
                        exitsdate.add(date);
                    }

                    //dach sách lịch học trong ngày
                    addToMap(calendar,schedule);

                    currentDate.add(Calendar.DATE,7);
                }

            }
        } catch (Exception e) {
            Toast.makeText(this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }


    }
    public static Calendar getDatePart(Date date){
        Calendar cal = Calendar.getInstance();       // get calendar instance
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);            // set hour to midnight
        cal.set(Calendar.MINUTE, 0);                 // set minute in hour
        cal.set(Calendar.SECOND, 0);                 // set second in minute
        cal.set(Calendar.MILLISECOND, 0);            // set millisecond in second

        return cal;                                  // return the date part
    }
    //Map chứa lịch học trong ngày của ngày x
    private void addToMap(Calendar calendar, Schedule schedule)
    {
        Calendar cal = (Calendar) calendar.clone();
        if (scheduleMap.containsKey(cal)){
            List<Schedule> scheduleList = scheduleMap.get(cal);
            if (scheduleList != null)
                scheduleList.add(schedule);
            else {
                scheduleList = new ArrayList<>();
                scheduleList.add(schedule);
            }
            scheduleMap.put(cal,scheduleList);
        }
        else {
            List<Schedule> scheduleList = new ArrayList<>();
            scheduleList.add(schedule);
            scheduleMap.put(cal,scheduleList);
        }
    }

    @Override
    public void onRadioButtonClick(int position) {
        Toast.makeText(this, "Semester " + semesters.get(position).getSemesterName(), Toast.LENGTH_SHORT).show();
        AppVar.semester = semesters.get(position);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.submit_semester:
            {
                viewModel.setSemester(AppVar.semester);
                dialog.dismiss();
                finish();
                overridePendingTransition(0, 0);
                startActivity(getIntent());
                overridePendingTransition(0, 0);
            }
            break;
        }
    }

    @Override
    public void onSelect(@NotNull List<Calendar> list) {

    }
}