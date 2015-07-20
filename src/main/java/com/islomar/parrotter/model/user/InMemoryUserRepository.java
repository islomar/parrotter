package com.islomar.parrotter.model.user;

import com.islomar.parrotter.infrastructure.repositories.UserRepository;

public class InMemoryUserRepository implements UserRepository{

  @Override
  public void saveUser(String username) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void updateUser(String username) {
    throw new UnsupportedOperationException();
  }
}
