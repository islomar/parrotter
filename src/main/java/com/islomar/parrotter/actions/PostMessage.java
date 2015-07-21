package com.islomar.parrotter.actions;


import com.islomar.parrotter.model.message.MessageService;
import com.islomar.parrotter.model.user.UserService;

public class PostMessage implements Command {

  private MessageService messageService;
  private final String username;
  private final String message;
  private UserService userService;

  public PostMessage(UserService userService, MessageService messageService, final String username, final String message) {
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
