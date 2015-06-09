package com.rafidka.notification;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class DisplayNotificationActivity extends ActionBarActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_display_notification);

    Intent intent = getIntent();
    if (intent != null) {
      String title = intent.getStringExtra("title");
      String content = intent.getStringExtra("content");
      setTitle(title);
      setContent(content);
    }
  }

  private void setTitle(String title) {
    TextView titleTextView = (TextView) findViewById(R.id.titleTextView);
    if (title != null)
      titleTextView.setText(title);
    else
      titleTextView.setText("N/A");
  }

  private void setContent(String content) {
    TextView contentTextView = (TextView) findViewById(R.id.contentTextView);
    if (content != null)
      contentTextView.setText(content);
    else
      contentTextView.setText("N/A");
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_display_notification, menu);
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
