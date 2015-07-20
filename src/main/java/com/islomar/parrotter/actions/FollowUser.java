package com.islomar.parrotter.actions;

import com.islomar.parrotter.infrastructure.repositories.UserRepository;

/**
 *
 */
public class FollowUser {

  private final UserRepository userRepository;

  public FollowUser(UserRepository userRepository) {
    this.userRepository = userRepository;
  }
}
