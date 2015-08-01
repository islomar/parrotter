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
  public void saveUser(User user) {
    users.add(user);
  }

  @Override
  public User findUserByUsername(String username) {
    return users.stream().filter(user -> user.getUsername().equalsIgnoreCase(username)).findFirst().orElse(new NullUser());
  }
}
