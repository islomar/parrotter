package com.islomar.parrotter.commands;


import com.islomar.parrotter.actions.PublishMessage;
import com.islomar.parrotter.commands.Command;
import com.islomar.parrotter.model.Message;

/**
 *
 */
public class PublishMessageCommand implements Command {

  private final PublishMessage publishMessage;
  private final Message message;

  public PublishMessageCommand(final PublishMessage publishMessage, final Message message) {

    this.publishMessage = publishMessage;
    this.message = message;
  }

  @Override
  public void execute() {
    publishMessage.execute(message);
  }
}
