package com.kanilturgut.samsungegitim.notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.kanilturgut.samsungegitim.R;

public class NotificationActivity extends AppCompatActivity {

    private Context mContext = this;

    private Button bNormalNotification, bActionNotification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        bNormalNotification = (Button) findViewById(R.id.bNormalNotification);
        bNormalNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                normalNotification();
            }
        });

        bActionNotification = (Button) findViewById(R.id.bActionNotification);
        bActionNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionNotification();
            }
        });
    }

    private void normalNotification() {

        Intent notificationIntent = new Intent(mContext, NormalNotificationActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 0, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setAutoCancel(true)
                        .setSmallIcon(R.drawable.ic_accessibility_white_24dp)
                        .setContentTitle("My notification")
                        .setContentIntent(pendingIntent)
                        .setContentText("Hello World!");

        // permission
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(1000);

        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // mId allows you to update the notification later on.
        mNotificationManager.notify(0, mBuilder.build());

    }

    private void actionNotification() {

        Intent notificationIntent = new Intent(mContext, ActionNotitificationAcitivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 0, notificationIntent, 0);

        Intent actionIntent = new Intent(mContext, FirstActionNotificationActivity.class);
        PendingIntent actionPendingIntent = PendingIntent.getActivity(mContext, 0, actionIntent, 0);

        Intent actionIntent2 = new Intent(mContext, SecondActionNotificationActivity.class);
        PendingIntent actionPendingIntent2 = PendingIntent.getActivity(mContext, 0, actionIntent2, 0);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setAutoCancel(true)
                        .setSmallIcon(R.drawable.ic_accessibility_white_24dp)
                        .setContentTitle("My notification")
                        .setContentIntent(pendingIntent)
                        .addAction(R.drawable.ic_account_balance_white_24dp, "Deneme", actionPendingIntent)
                        .addAction(R.drawable.ic_accessibility_white_24dp, "bla", actionPendingIntent2)
                        .setContentText("Hello World!");

        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // mId allows you to update the notification later on.
        mNotificationManager.notify(1, mBuilder.build());
    }
}
