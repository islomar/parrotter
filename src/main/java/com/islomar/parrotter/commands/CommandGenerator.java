package com.islomar.parrotter.commands;

import com.islomar.parrotter.actions.PostMessage;
import com.islomar.parrotter.actions.ReadUserTimeline;

import java.time.Clock;


public class CommandGenerator {

  private final Clock clock;
  private final PostMessage postMessage;
  private final ReadUserTimeline readUserTimeline;

  public CommandGenerator(Clock clock, PostMessage postMessage, ReadUserTimeline readUserTimeline) {
    this.clock = clock;

    this.postMessage = postMessage;
    this.readUserTimeline = readUserTimeline;
  }

  public Command createCommandFromInputLine(String inputCommandLine) {

    Command command = new NothingToExecuteCommand();
    if (inputCommandLine.contains("->")) {
      command = generatePublishMessageCommand(inputCommandLine);
    } else if (inputCommandLine.contains("wall")) {
      command = generateShowUserWallCommand(inputCommandLine);
    }  else if (inputCommandLine.contains("follow")) {
      command = generateFollowUserCommand(inputCommandLine);
    } else {
      command = generateViewUserTimelineCommand(inputCommandLine);
    }

    return command;
  }

  private Command generateViewUserTimelineCommand(String username) {
    return new ReadUserTimelineCommand(readUserTimeline, username);
  }

  private Command generateFollowUserCommand(String inputCommandLine) {
    throw new UnsupportedOperationException();
  }

  private Command generateShowUserWallCommand(String inputCommandLine) {
    throw new UnsupportedOperationException();
  }

  private Command generatePublishMessageCommand(String inputCommandLine) {

    String[] inputArguments = inputCommandLine.split("->");
    String username = inputArguments[0].trim();
    String textMessage = inputArguments[1].trim();
    return new PostMessageCommand(postMessage, username, textMessage);
  }
}
