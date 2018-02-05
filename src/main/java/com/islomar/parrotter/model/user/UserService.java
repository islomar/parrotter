package com.islomar.parrotter.model.user;

import com.islomar.parrotter.infrastructure.repositories.UserRepository;

public class UserService {

  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public void saveUser(String username) {
    User user = new User(username);
    userRepository.saveUser(user);
  }

  public User findUserByUsername(String username) {
    return userRepository.findUserByUsername(username);
  }
}
