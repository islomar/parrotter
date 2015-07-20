package com.islomar.parrotter.commands;

import com.islomar.parrotter.actions.FollowUser;
import com.islomar.parrotter.actions.PostMessage;
import com.islomar.parrotter.actions.ReadUserTimeline;
import com.islomar.parrotter.model.user.User;


public class CommandGenerator {

  private enum CommandAction {
    POST ("->"),
    FOLLOWS("follows"),
    WALL ("wall");

    private final String symbol;

    private CommandAction(String symbol) {
      this.symbol = symbol;
    }

    public String symbol() {
      return symbol;
    }
  }

  private final PostMessage postMessage;
  private final ReadUserTimeline readUserTimeline;
  private final FollowUser followUser;

  public CommandGenerator(PostMessage postMessage, ReadUserTimeline readUserTimeline, FollowUser followUser) {

    this.postMessage = postMessage;
    this.readUserTimeline = readUserTimeline;
    this.followUser = followUser;
  }

  public Command createCommandFromInputLine(String inputCommandLine) {

    Command command = new NothingToExecuteCommand();
    if (inputCommandLine.contains(CommandAction.POST.symbol())) {
      command = generatePublishMessageCommand(inputCommandLine);
    } else if (inputCommandLine.contains(CommandAction.WALL.symbol())) {
      command = generateShowUserWallCommand(inputCommandLine);
    } else if (inputCommandLine.contains(CommandAction.FOLLOWS.symbol())) {
      command = generateFollowUserCommand(inputCommandLine, followUser);
    } else {
      command = generateViewUserTimelineCommand(inputCommandLine);
    }

    return command;
  }

  private Command generateViewUserTimelineCommand(String username) {
    return new ReadUserTimelineCommand(readUserTimeline, username);
  }

  private Command generateFollowUserCommand(String inputCommandLine, FollowUser followUser) {
    String[] inputArguments = inputCommandLine.split(CommandAction.FOLLOWS.symbol());
    String followingUsername = inputArguments[0].trim();
    String followedUsername = inputArguments[1].trim();

    User followingUser = new User(followingUsername);

    followingUser.follow(followedUsername);

    return new FollowUserCommand(followUser, followingUsername, followedUsername);
  }

  private Command generateShowUserWallCommand(String inputCommandLine) {
    throw new UnsupportedOperationException();
  }

  private Command generatePublishMessageCommand(String inputCommandLine) {

    String[] inputArguments = inputCommandLine.split(CommandAction.POST.symbol());
    String username = inputArguments[0].trim();
    String textMessage = inputArguments[1].trim();
    return new PostMessageCommand(postMessage, username, textMessage);
  }
}
