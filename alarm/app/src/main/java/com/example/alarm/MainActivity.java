package com.example.alarm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TimePicker;
import android.widget.ToggleButton;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    Button btn;
    TimePicker timePicker;
    ToggleButton toggleButton;
    MediaPlayer mp;
    PendingIntent pendingIntent;

    private static final String CHANNEL_ID="My Channel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.button);
        timePicker = findViewById(R.id.timePicker);
        toggleButton = findViewById(R.id.toggleButton);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNotification("this is title", "this is text", "this is subtext");
            }
        });

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    Calendar alarmTime = Calendar.getInstance();
                    alarmTime.set(Calendar.HOUR_OF_DAY,timePicker.getHour());
                    alarmTime.set(Calendar.MINUTE,timePicker.getMinute());
                    alarmTime.set(Calendar.SECOND,0);

                    Timer timer  = new Timer();
                    Intent intent = new Intent(MainActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    pendingIntent = PendingIntent.getActivity(MainActivity.this,0,intent,PendingIntent.FLAG_IMMUTABLE);

//                    long t1 = alarmTime.getTime().getTime();
//                    long t2 = System.currentTimeMillis();
                    Calendar currentTime = Calendar.getInstance();

                    timer.scheduleAtFixedRate(new Notify(),alarmTime.getTimeInMillis()-currentTime.getTimeInMillis(),24*60*60*1000);
                }else{
                    mp = MediaPlayer.create(MainActivity.this, Settings.System.DEFAULT_RINGTONE_URI);
                    mp.stop();
                }
            }
        });
    }
    private class Notify extends TimerTask {
        @Override
        public void run() {
            showNotification("alarm this is title", "alarm this is text", "alarm this is subtext");

            mp = MediaPlayer.create(MainActivity.this, Settings.System.DEFAULT_RINGTONE_URI);
            mp.setLooping(true);
            mp.start();
        }
    }

    private void showNotification(String title, String text, String subtext) {
//        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification notification;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            notification = new Notification.Builder(MainActivity.this)
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setContentTitle(title)
                    .setContentText(text)
                    .setSubText(subtext)
                    .setChannelId(CHANNEL_ID)
                    .build();
            nm.createNotificationChannel(new NotificationChannel(CHANNEL_ID, "New Channel", NotificationManager.IMPORTANCE_HIGH));
        } else {
            notification = new Notification.Builder(MainActivity.this)
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setContentTitle(title)
                    .setContentText(text)
                    .setSubText(subtext)
                    .build();
        }
        nm.notify(1, notification);
    }
}
