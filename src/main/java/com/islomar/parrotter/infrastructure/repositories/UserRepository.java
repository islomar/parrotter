package com.islomar.parrotter.infrastructure.repositories;


import com.islomar.parrotter.model.user.User;

public interface UserRepository {

  void saveUser(User user);

  User findUserByUsername(String username);
}
