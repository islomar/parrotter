package com.islomar.parrotter.actions;

import com.islomar.parrotter.model.User;
import com.islomar.parrotter.model.UserService;

public class FollowUser implements Command {

  public static final String FOLLOWS = " follows ";

  private final UserService userService;
  private String followingUsername;
  private String followedUsername;

  public FollowUser(final UserService userService) {

    this.userService = userService;
  }

  @Override
  public void execute(String inputCommandLine) {

    extractFollowingAndFollowedUsers(inputCommandLine);

    User followingUser = userService.findUserByUsername(followingUsername);
    followingUser.follow(followedUsername);
  }

  @Override
  public boolean canExecuteCommandline(String inputCommandLine) {
    return inputCommandLine.contains(FOLLOWS);
  }

  private void extractFollowingAndFollowedUsers(String inputCommandLine) {
    String[] inputArguments = inputCommandLine.split(FOLLOWS);
    this.followingUsername = inputArguments[0].trim();
    this.followedUsername = inputArguments[1].trim();
  }
}
