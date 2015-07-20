package com.islomar.parrotter.model.user;

import com.google.common.collect.Sets;

import com.islomar.parrotter.infrastructure.repositories.UserRepository;

import java.util.Set;

public class InMemoryUserRepository implements UserRepository {

  private Set<User> users;

  public InMemoryUserRepository() {
    users = Sets.newHashSet();
  }

  @Override
  public void saveUser(String username) {
    users.add(new User(username));
  }

  @Override
  public void updateUser(String username) {
    throw new UnsupportedOperationException();
  }

  @Override
  public User getUser(String username) {
    return users.stream().filter(user -> user.getUsername().equalsIgnoreCase(username)).findFirst().orElse(new NullUser());
  }
}
