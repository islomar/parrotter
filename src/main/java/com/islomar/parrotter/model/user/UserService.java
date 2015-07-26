package com.islomar.parrotter.model.user;

import com.islomar.parrotter.infrastructure.repositories.UserRepository;

public class UserService {

  private UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public void saveUser(String username) {
    userRepository.saveUser(username);
  }

  public User findUserByUsername(String username) {
    return userRepository.findUserByUsername(username);
  }
}