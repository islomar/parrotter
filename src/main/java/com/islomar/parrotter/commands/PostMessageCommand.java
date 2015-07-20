package com.islomar.parrotter.commands;


import com.islomar.parrotter.actions.PostMessage;

/**
 *
 */
public class PostMessageCommand implements Command {

  private final PostMessage postMessage;
  private final String username;
  private final String message;

  public PostMessageCommand(PostMessage postMessage, final String username, final String message) {
    this.postMessage = postMessage;

    this.username = username;
    this.message = message;
  }

  @Override
  public void execute() {
    postMessage.execute(username, message);
  }
}
