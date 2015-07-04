package com.rafidka.restcomm;

import java.lang.reflect.Field;
import java.util.Date;

// Hint: Define a class that serves as the container for user info retrieved
// from GitHub API.
public class GitHubUser {
  public int id;
  public String login;
  public String avatarUrl;
  public String gravatarId;
  public String url;
  public String htmlUrl;
  public String followersUrl;
  public String followingUrl;
  public String gistsUrl;
  public String starredUrl;
  public String subscriptionsUrl;
  public String organizationsUrl;
  public String reposUrl;
  public String eventsUrl;
  public String receivedEventsUrl;
  public String type;
  public boolean siteAdmin;
  public String name;
  public String company;
  public String blog;
  public String location;
  public String email;
  public boolean hireable;
  public String bio;
  public int publicRepos;
  public int publicGists;
  public int followers;
  public int following;
  public Date createdAt;
  public Date updatedAt;

  // Hint: Use reflection to convert the object to a string.
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (Field field : this.getClass().getDeclaredFields()) {
      try {
        Object obj = field.get(this);
        sb.append(field.getName());
        sb.append(": ");
        sb.append(obj != null ? obj.toString() : "null");
        sb.append("\n");
      } catch (IllegalAccessException e) {
        // Seriously Java?!
      }
    }
    return sb.toString();
  }
}
