package com.islomar.parrotter.actions;


import com.islomar.parrotter.model.message.MessageService;
import com.islomar.parrotter.model.user.UserService;

public class PostMessage implements Command {

  public static final String POST = " -> ";

  private MessageService messageService;
  private UserService userService;

  public PostMessage(UserService userService, MessageService messageService) {
    this.userService = userService;
    this.messageService = messageService;
  }

  @Override
  public void execute(String inputCommandLine) {

    String[] inputArguments = inputCommandLine.split(POST);
    String username = inputArguments[0].trim();
    String message = inputArguments[1].trim();

    userService.saveUser(username);
    messageService.saveMessage(username, message);
  }

  @Override
  public boolean canExecuteCommandline(String inputCommandLine) {
    return inputCommandLine.contains(POST);
  }

}
