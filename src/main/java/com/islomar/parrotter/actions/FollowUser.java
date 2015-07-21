package com.islomar.parrotter.actions;

import com.islomar.parrotter.model.user.User;
import com.islomar.parrotter.model.user.UserService;

public class FollowUser implements Command {

  private final UserService userService;
  private final String followingUsername;
  private final String followedUsername;

  public FollowUser(UserService userService, String followingUsername, String followedUsername) {

    this.userService = userService;
    this.followingUsername = followingUsername;
    this.followedUsername = followedUsername;
  }

  @Override
  public void execute() {

    User followingUser = userService.findUserByUsername(followingUsername);
    followingUser.follow(followedUsername);
  }
}
