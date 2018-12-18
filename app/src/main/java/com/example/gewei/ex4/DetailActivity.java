package com.example.gewei.ex4;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DetailActivity extends AppCompatActivity {//主线程可能会影响运行速度，新建线程？定时函数错误？

    TimePicker timepicker;//时间拾取器
    Calendar c;           //日历对象
    TextView txt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {                                                 //跳转定时页面。。。
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        c = Calendar.getInstance();                                                                      //日历对象
        timepicker = (TimePicker) findViewById(R.id.timePicker1);                                        //时间拾取组件
        timepicker.setIs24HourView(true);                                                                //24小时制

        Button button1 = (Button) findViewById(R.id.btn_settime);                                        //闹钟按钮
        txt1=(TextView)findViewById(R.id.txt_time);
        button1.setOnClickListener(new View.OnClickListener() {    //为“设置闹钟”按钮添加单击事件监听器
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this, AlarmActivity.class);     //Intent对象
                PendingIntent pendingIntent = PendingIntent.getActivity(DetailActivity.this, 0, intent, 0);
                //显示闹钟的PendingIntent对象
                AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);            //AlarmManager对象
                c.set(Calendar.HOUR_OF_DAY,timepicker.getCurrentHour());
                c.set(Calendar.MINUTE,timepicker.getCurrentMinute());
                c.set(Calendar.SECOND,0);
                alarm.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);                 //设置此闹钟
                Toast.makeText(DetailActivity.this, "设置成功", Toast.LENGTH_SHORT).show();//显示此消息提示
                new Thread(new Runnable(){
                    @Override
                    public void run() {
                        try {
                            SimpleDateFormat sdf = new SimpleDateFormat("HH时mm分");
                            String dateStr = sdf.format(c.getTime());
                            txt1.setText(dateStr);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
        //btn2.setOnClickListener(new View.OnClickListener() {//闪退...
        //            @Override
        //            public void onClick(View v) {
        //                alarm.cancel();
        //                txt1.setText("");
        //                Toast.makeText(DetailActivity.this, "取消定时", Toast.LENGTH_SHORT).show();//显示此消息提示
        //            }
        //        });
    }
}
