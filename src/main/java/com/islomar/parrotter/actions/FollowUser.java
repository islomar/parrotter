package com.islomar.parrotter.actions;

import com.islomar.parrotter.model.user.User;
import com.islomar.parrotter.model.user.UserService;

public class FollowUser implements Command {

  public static final String FOLLOWS = " follows ";

  private final UserService userService;

  public FollowUser(final UserService userService) {

    this.userService = userService;
  }

  @Override
  public void execute(String inputCommandLine) {
    if (canNotExecute(inputCommandLine)) {
      return;
    }

    String[] inputArguments = inputCommandLine.split(FOLLOWS);
    String followingUsername = inputArguments[0].trim();
    String followedUsername = inputArguments[1].trim();

    User followingUser = userService.findUserByUsername(followingUsername);
    followingUser.follow(followedUsername);
  }

  public boolean canNotExecute(String inputCommandLine) {
    return !inputCommandLine.contains(FOLLOWS);
  }

}
