package com.islomar.parrotter.commands;

import com.islomar.parrotter.actions.FollowUser;
import com.islomar.parrotter.model.user.User;

/**
 *
 */
public class FollowUserCommand implements Command {

  private final FollowUser followUser;
  private final String followingUsername;
  private final String followedUsername;

  public FollowUserCommand(FollowUser followUser, String followingUsername, String followedUsername) {

    this.followUser = followUser;
    this.followingUsername = followingUsername;
    this.followedUsername = followedUsername;
  }

  @Override
  public void execute() {
    User followingUser = new User(followingUsername);

    followingUser.follow(followedUsername);
  }
}
