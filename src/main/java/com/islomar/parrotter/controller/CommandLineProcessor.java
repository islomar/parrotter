package com.islomar.parrotter.controller;

import com.islomar.parrotter.actions.Command;
import com.islomar.parrotter.controller.utils.CommandGenerator;
import com.islomar.parrotter.model.message.MessageService;
import com.islomar.parrotter.model.user.ShowUserWallService;
import com.islomar.parrotter.model.user.UserService;


public class CommandLineProcessor {

  private final MessageService messageService;
  private final UserService userService;
  private ShowUserWallService showUserWallService;

  public CommandLineProcessor(UserService userService, MessageService messageService,
                              ShowUserWallService showUserWallService) {

    this.userService = userService;
    this.messageService = messageService;
    this.showUserWallService = showUserWallService;
  }

  public void execute(String inputCommandLine) {

    CommandGenerator commandGenerator = new CommandGenerator(userService, messageService, showUserWallService);
    Command command = commandGenerator.createCommandFromInputLine(inputCommandLine);

    command.execute();
  }
}
