package com.rafidka.databinding;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.rafidka.databinding.databinding.MainActivityBinding;

public class MainActivity extends AppCompatActivity {
  User user = new User();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    // Hint: Bind the user object to the main activity.
    MainActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    binding.setUser(user);
    user.firstName.set("Albert");
    user.lastName.set("Einstein");

    findViewById(R.id.sayHelloButton).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        // Hint: Updating the fields in user object automatically updates the User Interface.
        String firstName = ((EditText)findViewById(R.id.firstNameEditText)).getText().toString();
        String lastName = ((EditText)findViewById(R.id.lastNameEditText)).getText().toString();
        user.firstName.set(firstName);
        user.lastName.set(lastName);
      }
    });
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
