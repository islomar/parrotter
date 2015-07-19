package com.islomar.parrotter.model;

import com.islomar.parrotter.actions.PublishMessage;
import com.islomar.parrotter.commands.Command;
import com.islomar.parrotter.commands.NothingToExecuteCommand;
import com.islomar.parrotter.commands.PublishMessageCommand;

import java.time.Instant;

/**
 *
 */
public class CommandGenerator {

  private final PublishMessage publishMessage;

  public CommandGenerator(PublishMessage publishMessage) {

    this.publishMessage = publishMessage;
  }

  public Command createCommandFromInputLine(String inputCommandLine) {

    // Use regex? only name, -> , wall, follow
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
    return null;
  }

  private Command generateFollowUserCommand(String inputCommandLine) {
    return null;
  }

  private Command generateShowUserWallCommand(String inputCommandLine) {
    return null;
  }

  private Command generatePublishMessageCommand(String inputCommandLine) {
    String[] inputArguments = inputCommandLine.split("->");
    String username = inputArguments[0].trim();
    String textMessage = inputArguments[1].trim();
    Message message = new Message(username, textMessage, Instant.now());
    return new PublishMessageCommand(publishMessage, message);
  }
}
