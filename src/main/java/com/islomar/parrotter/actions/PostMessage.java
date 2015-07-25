package com.islomar.parrotter.actions;


import com.islomar.parrotter.model.message.MessageService;
import com.islomar.parrotter.model.user.UserService;

public class PostMessage implements Command {

  public static final String POST = " -> ";

  private MessageService messageService;
  private String username;
  private String message;
  private UserService userService;

  public PostMessage(UserService userService, MessageService messageService) {
    this.userService = userService;
    this.messageService = messageService;
  }

  @Override
  public void execute(String inputCommandLine) {

    extractUsernameAndMessage(inputCommandLine);

    userService.saveUser(username);
    messageService.saveMessage(username, message);
  }

  @Override
  public boolean canExecuteCommandline(String inputCommandLine) {
    return inputCommandLine.contains(POST);
  }

  private void extractUsernameAndMessage(String inputCommandLine) {

    String[] inputArguments = inputCommandLine.split(POST);
    this.username = inputArguments[0].trim();
    this.message = inputArguments[1].trim();
  }
}
