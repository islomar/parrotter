package com.islomar.parrotter.commands;

import com.islomar.parrotter.actions.PostMessage;
import com.islomar.parrotter.model.Message;

import java.time.Instant;


public class CommandGenerator {

  private final PostMessage postMessage;

  public CommandGenerator(PostMessage postMessage) {

    this.postMessage = postMessage;
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

  private Command generateViewUserTimelineCommand(String inputCommandLine) {
    throw new UnsupportedOperationException();
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
    Message message = new Message(username, textMessage, Instant.now());
    return new PublishMessageCommand(postMessage, message);
  }
}
