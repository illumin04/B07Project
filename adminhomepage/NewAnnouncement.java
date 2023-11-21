package com.example.adminhomepage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.*;
import android.content.Intent;
import android.os.*;
import android.util.*;
import android.view.View;
import android.widget.*;

public class NewAnnouncement extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_announcement);

        EditText text = findViewById(R.id.text);
        text.setSelection(0);

        Button submitButton = findViewById(R.id.submit_btn);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    NotificationChannel channel = new NotificationChannel(
                            "channel_id",
                            "channel_name",
                            NotificationManager.IMPORTANCE_HIGH);
                    notificationManager.createNotificationChannel(channel);
                }

                Notification notification = new NotificationCompat.Builder(NewAnnouncement.this, "channel_id")
                        .setSmallIcon(android.R.drawable.ic_dialog_info)
                        .setContentTitle("Submitted!")
                        .setContentText("The announcement details has been submitted!")
                        .build();

                notificationManager.notify(1, notification);

                Intent intent = new Intent(NewAnnouncement.this, AnnouncementPage.class);
                startActivity(intent);
                // display new event to event page after returning to event page
            }
        });

    }
}