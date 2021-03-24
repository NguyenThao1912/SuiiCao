package com.monsun.suiicao.repositories;

import com.monsun.suiicao.models.CourseExam;
import com.monsun.suiicao.models.Curriculum;
import com.monsun.suiicao.models.Mentor;
import com.monsun.suiicao.models.Schedule;
import com.monsun.suiicao.models.Semester;
import com.monsun.suiicao.models.Users;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ILocalServices {
    //Student
    @GET("api/student/semester")
    Call<List<Semester>> getSemester();

    @GET("api/login/{username}/{password}")
    Call<Boolean> GetLoginResult(@Path("username") String username,@Path("password") String pass);

    @GET("api/student/exam/{studentid}/{semester}")
    Call<List<CourseExam>> getExamList(@Path("studentid") int sid,@Path("semester") int semester);

    @GET("api/student/lecture/{classid}")
    Call<List<Curriculum>> getStudentLecture(@Path("classid") int cid);

    @GET("api/student/{username}")
    Call<Users> getStudentbyUsername(@Path("username") String username);

    @GET("api/student/schedule/{studentid}/{semester}")
    Call<List<Schedule>> GetStudentSchedule(@Path("studentid") int sid, @Path("semester")int semester);

    //================================================================
    //Mentor
    @GET("api/mentor/class/{classid}")
    Call<Mentor> getMentorByClassId(@Path("classid") int cid);

    @GET("api/mentor/id/{mentorid}")
    Call<Mentor> getMentorByMentorid(@Path("mentorid") int cid);

    @GET("api/mentor/user/{username}")
    Call<Mentor> getMentorByUserName(@Path("username") String username);

    @GET("api/student/class/{classid}")
    Call<List<Users>> getAllStudentByClassId(@Path("classid") int cid);
}
