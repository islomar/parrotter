package com.islomar.parrotter.actions;


import com.islomar.parrotter.services.PostMessageService;
import com.islomar.parrotter.services.UserService;

/**
 *
 */
public class PostMessageCommand implements Command {

  private final PostMessageService postMessageService;
  private final String username;
  private final String message;
  private UserService userService;

  public PostMessageCommand(UserService userService, PostMessageService postMessageService, final String username, final String message) {
    this.userService = userService;
    this.postMessageService = postMessageService;

    this.username = username;
    this.message = message;
  }

  @Override
  public void execute() {
    userService.saveUser(username);
    postMessageService.execute(username, message);
  }
}
