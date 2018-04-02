package com.islomar.parrotter.model.user;


import com.islomar.parrotter.model.user.User;

public interface UserRepository {

  void saveUser(User user);

  User findUserByUsername(String username);
}
