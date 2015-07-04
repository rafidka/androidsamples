package com.rafidka.restcomm;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.DateTypeAdapter;

import java.util.Date;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;


public class MainActivity extends ActionBarActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    // Hint: Register a handler for the Fetch Details button.
    Button fetchDetails = (Button)findViewById(R.id.fetchDetailsButton);
    fetchDetails.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        EditText userNameEditText = (EditText)findViewById(R.id.loginEditText);
        String userName = userNameEditText.getText().toString();
        fetchUserDetails(userName);
      }
    });
  }

  private void fetchUserDetails(String userName) {
    // Hint: GitHub fields are lower_case_with_underscores, but to follow Java conventions
    // this has to be converted to lowerCaseWithUnderscores. This Gson configuration
    // dictate that.
    Gson gson = new GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .registerTypeAdapter(Date.class, new DateTypeAdapter())
        .create();
    // Hint: Creates an adapter that specifies the URL and use the Gson object above.
    RestAdapter adapter = new RestAdapter.Builder()
        .setEndpoint("https://api.github.com")
        .setConverter(new GsonConverter(gson))
        .build();
    // Hint: Create the client.
    GitHubClient client = adapter.create(GitHubClient.class);
    // Hint: Make a request to retrieve the user from GitHub.
    client.getUser(userName, new Callback<GitHubUser>() {
      @Override
      public void success(GitHubUser gitHubUser, Response response) {
        TextView userDetailsTextView = (TextView)findViewById(R.id.userDetailsTextView);
        userDetailsTextView.setText(gitHubUser.toString());
      }

      @Override
      public void failure(RetrofitError error) {
        Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_LONG).show();
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
