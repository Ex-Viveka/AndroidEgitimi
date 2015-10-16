package com.kanilturgut.notificationdenemeleri;

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

public class MainActivity extends AppCompatActivity {

    private final String TAG = MainActivity.class.getSimpleName();
    private Context mContext = this;

    private Button bNormal, bAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bNormal = (Button) findViewById(R.id.bNormalNotification);
        bNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNormalNotification();
            }
        });

        bAction = (Button) findViewById(R.id.bActionNotification);
        bAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createActionNotification();
            }
        });
    }

    private void createNormalNotification() {

        Intent normalIntent = new Intent(mContext, NormalNotificaitonActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 0, normalIntent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext)
                .setAutoCancel(true)
                .setContentTitle("Başlık")
                .setContentText("Gösterilecek mesaj")
                .setContentIntent(pendingIntent)
                .setStyle(new NotificationCompat.BigTextStyle().bigText("asdasdsad sad sad sad sad sadsad s aasdsadsadsagdsadas"))
                .setSmallIcon(R.drawable.ic_accessibility_white_24dp);

        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(1000);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, builder.build());
    }

    private void createActionNotification() {

        Intent normalIntent = new Intent(mContext, ActionNotificationActivity.class);
        PendingIntent normalPendingIntent = PendingIntent.getActivity(mContext, 0, normalIntent, 0);

        Intent actionIntent1 = new Intent(mContext, FirstActionNotificationActivity.class);
        PendingIntent pendingIntent1 = PendingIntent.getActivity(mContext, 0, actionIntent1, 0);


        Intent actionIntent2 = new Intent(mContext, SecondActionNotificationActivity.class);
        PendingIntent pendingIntent2 = PendingIntent.getActivity(mContext, 0, actionIntent2, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext)
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.ic_accessibility_white_24dp)
                .setContentTitle("Actionlı Bildirim")
                .setContentText("Bu bir action bildirimdir :)")
                .setContentIntent(normalPendingIntent)
                .addAction(R.drawable.ic_account_balance_white_24dp, "Onayla", pendingIntent1)
                .addAction(0, "Reddet", pendingIntent2);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, builder.build());
    }
}
