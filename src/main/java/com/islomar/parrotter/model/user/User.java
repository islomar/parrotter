package com.islomar.parrotter.model.user;

import com.google.common.collect.Sets;

import java.util.Collections;
import java.util.Set;

public class User {

  private final String username;
  private Set<String> followedUsers = Sets.newHashSet();

  public User(final String username) {

    this.username = username;
  }

  public String getUsername() {
    return username;
  }

  public Set<String> getFollowedUsers() {
    return Collections.unmodifiableSet(followedUsers);
  }

  public void follow(String username) {
    this.followedUsers.add(username);
  }
}
