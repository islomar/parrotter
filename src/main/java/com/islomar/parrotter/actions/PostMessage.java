package com.islomar.parrotter.actions;


import com.islomar.parrotter.model.UserService;

public class PostMessage implements Command {

  private final String username;
  private final String message;
  private UserService userService;

  public PostMessage(UserService userService, final String username, final String message) {
    this.userService = userService;

    this.username = username;
    this.message = message;
  }

  @Override
  public void execute() {
    userService.saveUser(username);
    userService.saveMessage(username, message);
  }
}
