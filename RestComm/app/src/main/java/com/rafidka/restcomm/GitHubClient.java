package com.rafidka.restcomm;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

// Hint: Define the interface that dictates how Retrofit should communicate with the GitHub's
// RESTful service.
public interface GitHubClient {
  @GET("/users/{login}")
  GitHubUser getUser(@Path("login") String login);

  @GET("/users/{login}")
  void getUser(@Path("login") String login, Callback<GitHubUser> callback);
}
