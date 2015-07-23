package com.islomar.parrotter.controller.utils;

import com.islomar.parrotter.actions.Command;
import com.islomar.parrotter.actions.FollowUser;
import com.islomar.parrotter.actions.PostMessage;
import com.islomar.parrotter.actions.ReadUserPersonalTimeline;
import com.islomar.parrotter.actions.ShowUserWall;
import com.islomar.parrotter.model.User;
import com.islomar.parrotter.model.UserService;
import com.islomar.parrotter.model.ShowUserWallService;

import static com.islomar.parrotter.controller.utils.CommandType.FOLLOWS;
import static com.islomar.parrotter.controller.utils.CommandType.POST;
import static com.islomar.parrotter.controller.utils.CommandType.WALL;

public class CommandGenerator {

  private final ShowUserWallService showUserWallService;
  private final UserService userService;

  public CommandGenerator(UserService userService,
                          ShowUserWallService showUserWallService) {

    this.userService = userService;
    this.showUserWallService = showUserWallService;
  }

  public Command createCommandFromInputLine(String inputCommandLine) {

    Command command = null;
    if (inputCommandLine.contains(POST.symbol())) {
      command = generatePublishMessageCommand(inputCommandLine);
    } else if (inputCommandLine.contains(WALL.symbol())) {
      command = generateShowUserWallCommand(inputCommandLine);
    } else if (inputCommandLine.contains(FOLLOWS.symbol())) {
      command = generateFollowUserCommand(inputCommandLine);
    } else {
      command = generateViewUserTimelineCommand(inputCommandLine);
    }

    return command;
  }

  private Command generateViewUserTimelineCommand(String username) {
    return new ReadUserPersonalTimeline(userService, username);
  }

  private Command generateFollowUserCommand(String inputCommandLine) {
    String[] inputArguments = inputCommandLine.split(FOLLOWS.symbol());
    String followingUsername = inputArguments[0].trim();
    String followedUsername = inputArguments[1].trim();

    User followingUser = new User(followingUsername);

    followingUser.follow(followedUsername);

    return new FollowUser(userService, followingUsername, followedUsername);
  }

  private Command generateShowUserWallCommand(String inputCommandLine) {
    String[] inputArguments = inputCommandLine.split(WALL.symbol());
    String username = inputArguments[0].trim();
    return new ShowUserWall(showUserWallService, username);
  }

  private Command generatePublishMessageCommand(String inputCommandLine) {

    String[] inputArguments = inputCommandLine.split(POST.symbol());
    String username = inputArguments[0].trim();
    String textMessage = inputArguments[1].trim();
    return new PostMessage(userService, username, textMessage);
  }
}
