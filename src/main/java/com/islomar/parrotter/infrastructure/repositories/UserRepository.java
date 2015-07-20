package com.islomar.parrotter.infrastructure.repositories;


import com.islomar.parrotter.model.user.User;

public interface UserRepository {

  public void saveUser(String username);
  public void updateUser(String username);
  public User getUser(String username);
}
