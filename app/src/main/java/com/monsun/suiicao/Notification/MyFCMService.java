package com.monsun.suiicao.Notification;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.monsun.suiicao.Utils.CommonUtils;

import java.util.Map;
import java.util.Random;

public class MyFCMService extends FirebaseMessagingService {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Map<String,String> data = remoteMessage.getData();
        if (data!= null){
            try {
                CommonUtils.showNotification(this,
                        new Random().nextInt(),
                        data.get(CommonUtils.NOTI_TITLE),
                        data.get(CommonUtils.NOTI_CONTENT),
                        data.get(CommonUtils.NOTI_SENDER),
                        data.get(CommonUtils.NOTI_ROOMID),
                        data.get(CommonUtils.NOTI_RECEIVER),
                        null
                );
            }
            catch (Exception e){

            }
        }
    }
}