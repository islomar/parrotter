package com.islomar.parrotter.actions;


import com.islomar.parrotter.services.MessageService;
import com.islomar.parrotter.services.UserService;

public class PostMessageCommand implements Command {

  private final MessageService messageService;
  private final String username;
  private final String message;
  private UserService userService;

  public PostMessageCommand(UserService userService, MessageService messageService, final String username, final String message) {
    this.userService = userService;
    this.messageService = messageService;

    this.username = username;
    this.message = message;
  }

  @Override
  public void execute() {
    userService.saveUser(username);
    messageService.saveMessage(username, message);
  }
}
