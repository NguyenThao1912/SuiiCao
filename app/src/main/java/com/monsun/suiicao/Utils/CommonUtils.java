package com.monsun.suiicao.Utils;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.monsun.suiicao.R;
import com.monsun.suiicao.firebase.FirebaseSer;

public final class CommonUtils {
    public static final String MY_PREFERENCE = "Myprefer";
    public static final String TYPE_USER = "type";
    public static final String MY_USER = "user";;
    public static String NOTI_RECEIVER = "receiver";
    public static int FIREBASE_CREATE_SUCCESS = 8;
    public static int FIREBASE_PASSWORD_TOO_WEAK = 10;
    public static int FIREBASE_EMAIL_WRONG_FORMAT = 15;
    public static int FIREBASE_EMAIL_EXIST = 20;
    public static int GET_INFORMATION_SUCESS = 22;
    public static String NOTI_TITLE = "title";
    public static String NOTI_CONTENT = "content";
    public static String NOTI_SENDER = "sender";
    public static String NOTI_ROOMID = "room_id";
    public static String ROOM_SELECTED = "";
    private CommonUtils() {

    }
    @SuppressLint("all")
    public static String getDeviceId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }
    public static ProgressDialog showLoadingDialog(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.show();
        if (progressDialog.getWindow() != null) {
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        //set layout of dialog
        progressDialog.setContentView(R.layout.progress_dialog);
        //run progress dialog infinite
        progressDialog.setIndeterminate(true);
        //cannot cancel it
        progressDialog.setCancelable(false);
        //cannot cancel when touch out side
        progressDialog.setCanceledOnTouchOutside(false);
        return progressDialog;
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void showNotification(Context context,
                                        int id ,
                                        String title,
                                        String content, String sender, String room_id, String receiver, Intent intent) {
        PendingIntent pendingIntent = null;
        Uri urisound = Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.message_notification);
        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .setUsage(AudioAttributes.USAGE_NOTIFICATION_COMMUNICATION_INSTANT)
                .build();
        if (intent != null)
            pendingIntent = PendingIntent.getActivity(context,id,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        String Notification_Chanel_id = "Chanel 1";
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            //checkversion
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = new NotificationChannel(Notification_Chanel_id,
                    "TStudy",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription("New TStudy");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.setVibrationPattern(new long[]{0,1000,500,1000});
            notificationChannel.enableVibration(true);
            notificationChannel.setSound(urisound,audioAttributes);
            notificationManager.createNotificationChannel(notificationChannel);
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,Notification_Chanel_id);
        builder.setContentTitle(title)
                .setContentText(content)
                .setAutoCancel(true)
                .setSound(urisound)
                .setSmallIcon(R.drawable.tstudy);
        if (pendingIntent != null)
        {
            builder.setContentIntent(pendingIntent);
        }
        Notification notification =  builder.build();
        if (FirebaseSer.mAuth.getCurrentUser().getUid().equals(receiver))
        {
            notificationManager.notify(id,notification);
        }


    }
}
