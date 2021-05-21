package com.monsun.suiicao.service;


import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface IFCMService {
    @Headers({
            "Content-Type:application/json",
            "Authorization:key=AAAAYFQ3EGE:APA91bGZJizQM3ixdFmLa6ii7MaMrMsz7PjoC5X0IeHEUnIQz_J5TSgSAhZSrJT-BX-TrImFgyn_DBdudINXp2D13SRLgZfOPOztX3H9N24-tdtHyxRP7vSMmxJffNk9ZpY26KJ2qWXX"
    })
    @POST("fcm/send")
    Observable<FCMResponse> sendNotification(@Body FCMSendData data);

}
