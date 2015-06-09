package com.rafidka.notification;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.NotificationCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
  private Boolean repeatingNotification = false;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    // Handler for the "Once" radio button.
    RadioButton onceRadioButton = (RadioButton)findViewById(R.id.onceRadioButton);
    onceRadioButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        MainActivity.this.repeatingNotification = false;
        // Disable the period edit text.
        EditText periodEditText = (EditText)findViewById(R.id.periodEditText);
        periodEditText.setEnabled(false);
      }
    });

    // Handler for the "Repeating every" radio button.
    RadioButton repeatingRadioButton = (RadioButton)findViewById(R.id.repeatingRadioButton);
    repeatingRadioButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        MainActivity.this.repeatingNotification = true;
        // Enable the period edit text.
        EditText periodEditText = (EditText)findViewById(R.id.periodEditText);
        periodEditText.setEnabled(true);
      }
    });


    final Button createNotification = (Button)findViewById(R.id.createNotification);
    createNotification.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        EditText title = (EditText)findViewById(R.id.titleEditText);
        EditText content = (EditText)findViewById(R.id.contentEditText);
        if (repeatingNotification) {
          EditText periodEditText = (EditText)findViewById(R.id.periodEditText);
          int period = Integer.parseInt(periodEditText.getText().toString());
          createRepeatingNotification(period, "(Repeating) " + title.getText().toString(), content.getText().toString());
        } else {
          createNotification("(Once) " + title.getText().toString(), content.getText().toString());
        }
      }
    });
  }

  private static int uniqueId = 0;

  private int getUniqueId() {
    return uniqueId++;
  }

  /**
   * Creates a notification that repeats itself every periodInSeconds.
   * @param periodInSeconds The period of repitition in seconds.
   * @param title The title of the notification.
   * @param content The content of the notification.
   */
  private void createRepeatingNotification(long periodInSeconds, String title, String content) {
    Intent intent = new Intent(this, AlarmReceiver.class);
    intent.putExtra("notificationId", getUniqueId());
    intent.putExtra("title", title);
    intent.putExtra("content", content);
    PendingIntent pendIntent = PendingIntent.getBroadcast(
        this.getApplicationContext(),
        0,
        intent,
        PendingIntent.FLAG_UPDATE_CURRENT);

    AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
    alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME,
        periodInSeconds * 1000,
        periodInSeconds * 1000,
        pendIntent);

    Toast.makeText(this,
        "Creating a notification that repeats every " + periodInSeconds + " seconds.",
        Toast.LENGTH_LONG).show();
  }

  /**
   * Creates a notification.
   * @param title The title of the notification.
   * @param content The content of the notification.
   */
  private void createNotification(String title, String content) {
    Intent intent = new Intent(this, DisplayNotificationActivity.class);
    intent.putExtra("title", title);
    intent.putExtra("content", content);
    PendingIntent pendIntent = PendingIntent.getActivity(
        this,
        0, // A code to be sent to the activity
        intent, // The intent to be started
        PendingIntent.FLAG_UPDATE_CURRENT);

    NotificationCompat.Builder builder =
        new NotificationCompat.Builder(MainActivity.this);
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

    NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
    manager.notify(getUniqueId(), builder.build());
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }
}
