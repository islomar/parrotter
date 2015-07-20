package com.islomar.parrotter.actions;

import com.islomar.parrotter.model.user.User;
import com.islomar.parrotter.services.FollowUserService;
import com.islomar.parrotter.services.UserService;

/**
 *
 */
public class FollowUserCommand implements Command {

  private final UserService userService;
  private final FollowUserService followUserService;
  private final String followingUsername;
  private final String followedUsername;

  public FollowUserCommand(UserService userService, FollowUserService followUserService, String followingUsername, String followedUsername) {

    this.userService = userService;
    this.followUserService = followUserService;
    this.followingUsername = followingUsername;
    this.followedUsername = followedUsername;
  }

  @Override
  public void execute() {

    User followingUser = userService.findUserByUsername(followingUsername);
    followingUser.follow(followedUsername);
  }
}
