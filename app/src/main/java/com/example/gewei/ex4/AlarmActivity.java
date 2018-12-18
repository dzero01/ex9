package com.example.gewei.ex4;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AlarmActivity extends AppCompatActivity {//提醒界面：notification + alarm
    final int NOTIFYID = 0x001;                       //通知ID:notification

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AlertDialog alert = new AlertDialog.Builder(this).create();
        alert.setIcon(R.mipmap.alarm);                //图标
        alert.setTitle("购买提醒:");                  //标题
        alert.setMessage("现在就去购买吗？");         //内容

        alert.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {//添加确定按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        alert.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() { //添加确定按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(AlarmActivity.this, GoodsActivity.class);
                startActivity(intent);
            }
        });
        alert.show();                                //显示


        //notification管理器服务，发送通知
        final NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        NotificationChannel channel = new NotificationChannel("1", "Channel1", NotificationManager.IMPORTANCE_DEFAULT);//创建通知渠道
        notificationManager.createNotificationChannel(channel);                                                                   //
        Notification.Builder notification = new Notification.Builder(this, "1");  //Notification对象
        notification.setAutoCancel(true);                                                           //打开自动消失
        notification.setSmallIcon(R.mipmap.alarm);                                                 //通知的图标
        notification.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.packet));   //下拉列表大图标
        notification.setContentTitle("购买提醒！");                                                  //标题
        notification.setContentText("前往购买！");                                                   //内容
        notification.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE);        //使用默认声音震动
        notification.setWhen(System.currentTimeMillis());                                           //发送时间******************
        Intent intent = new Intent(AlarmActivity.this, AlarmActivity.class);          //启动其他Activity的Intent**
        PendingIntent pi = PendingIntent.getActivity(AlarmActivity.this, 0, intent, 0);
        notification.setContentIntent(pi);                                                          //通知栏单击跳转
        notificationManager.notify(NOTIFYID, notification.build());                                 //通知
    }
}
