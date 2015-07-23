package com.islomar.parrotter.infrastructure.repositories;


import com.islomar.parrotter.model.User;

public interface UserRepository {

  public void saveUser(String username);

  public User findUserByUsername(String username);
}
