package com.monsun.suiicao.repositories;

import com.monsun.suiicao.models.CourseExam;
import com.monsun.suiicao.models.Curriculum;
import com.monsun.suiicao.models.Semester;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ILocalServices {
    @GET("api/student/semester")
    Call<List<Semester>> getSemester();

    @GET("api/login/{username}/{password}")
    Call<Boolean> GetLoginResult(@Path("username") String username,@Path("password") String pass);

    @GET("api/student/exam/{studentid}/{semester}")
    Call<List<CourseExam>> getExamList(@Path("studentid") int sid,@Path("semester")String semester);

    @GET("api/student/lecture")
    Call<List<Curriculum>> getStudentLecture();
}
