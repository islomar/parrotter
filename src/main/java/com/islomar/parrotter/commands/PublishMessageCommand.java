package com.islomar.parrotter.commands;


import com.islomar.parrotter.actions.PostMessage;
import com.islomar.parrotter.model.Message;

/**
 *
 */
public class PublishMessageCommand implements Command {

  private final PostMessage postMessage;
  private final Message message;

  public PublishMessageCommand(final PostMessage postMessage, final Message message) {

    this.postMessage = postMessage;
    this.message = message;
  }

  @Override
  public void execute() {
    postMessage.execute(message);
  }
}
