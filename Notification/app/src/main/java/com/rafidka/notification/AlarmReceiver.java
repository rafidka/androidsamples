package com.rafidka.notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;

public class AlarmReceiver extends BroadcastReceiver {
  private void createNotification(Context context, int notifId, String title, String content) {
    Intent intent = new Intent(context, DisplayNotificationActivity.class);
    intent.putExtra("title", title);
    intent.putExtra("content", content);
    PendingIntent pendIntent = PendingIntent.getActivity(
        context,
        0, // A code to be sent to the activity
        intent, // The intent to be started
        PendingIntent.FLAG_UPDATE_CURRENT);

    NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
    builder.setSmallIcon(R.drawable.ic_menu_notifications);
    // Sets the title and content of the notification.
    builder.setContentTitle(title);
    builder.setContentText(content);
    // Adds some vibration.
    builder.setVibrate(new long[]{0, 500, 250, 500});
    // Ensure that the notification disappears after the user clicks on it.
    builder.setAutoCancel(true);
    builder.setOngoing(false);
    // Sets the intent to start when the user clicks on the notification.
    builder.setContentIntent(pendIntent);

    NotificationManager manager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
    manager.notify(notifId, builder.build());
  }

  @Override
  public void onReceive(Context context, Intent intent) {
    int notifId = 0;
    String title = "Routine Notification";
    String content = "This notification gets created every " +
        "certain period of time.";
    if (intent != null) {
      notifId = intent.getIntExtra("notificationId", 0);
      title = intent.getStringExtra("title");
      content = intent.getStringExtra("content");
    }
    createNotification(context, notifId, title, content);
  }
}
