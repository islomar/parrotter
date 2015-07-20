package com.islomar.parrotter.services;

import com.islomar.parrotter.infrastructure.repositories.UserRepository;
import com.islomar.parrotter.model.user.User;

public class UserService {

  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public void saveUser(String username) {
    //TODO: check if it exists
    userRepository.saveUser(username);
  }

  public User findUserByUsername(String username) {
    return userRepository.getUser(username);
  }
}
