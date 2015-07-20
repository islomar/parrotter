package com.islomar.parrotter.services;

import com.islomar.parrotter.infrastructure.repositories.UserRepository;

/**
 *
 */
public class FollowUserService {

  private final UserRepository userRepository;

  public FollowUserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }
}
