package com.islomar.parrotter.model.user;

import com.google.common.collect.Sets;

import java.util.Set;

public class NullUser extends User {

  private String username = null;

  public NullUser() {
    super(null);
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public Set<String> getFollowedUsers() {
    return Sets.newHashSet();
  }

  @Override
  public void follow(String username) {
  }
}
