package com.islomar.parrotter.infrastructure.repositories;


import com.islomar.parrotter.model.user.User;

public interface UserRepository {

  public void saveUser(User user);

  public User findUserByUsername(String username);
}
