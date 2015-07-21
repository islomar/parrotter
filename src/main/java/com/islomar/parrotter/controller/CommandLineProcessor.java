package com.islomar.parrotter.controller;

import com.islomar.parrotter.actions.Command;
import com.islomar.parrotter.controller.utils.CommandGenerator;
import com.islomar.parrotter.model.message.MessageService;
import com.islomar.parrotter.model.user.ShowUserWallService;
import com.islomar.parrotter.model.user.UserService;


public class CommandLineProcessor {

  private final CommandGenerator commandGenerator;

  public CommandLineProcessor(UserService userService, MessageService messageService,
                              ShowUserWallService showUserWallService) {

    commandGenerator = new CommandGenerator(userService, messageService, showUserWallService);
  }

  public void execute(String inputCommandLine) {

    Command command = commandGenerator.createCommandFromInputLine(inputCommandLine);
    command.execute();
  }
}
