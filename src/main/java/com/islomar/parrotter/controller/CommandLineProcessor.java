package com.islomar.parrotter.controller;

import com.islomar.parrotter.actions.Command;
import com.islomar.parrotter.controller.utils.CommandGenerator;
import com.islomar.parrotter.model.UserService;
import com.islomar.parrotter.model.ShowUserWallService;


public class CommandLineProcessor {

  private final CommandGenerator commandGenerator;

  public CommandLineProcessor(UserService userService, ShowUserWallService showUserWallService) {

    commandGenerator = new CommandGenerator(userService, showUserWallService);
  }

  public void execute(String inputCommandLine) {

    Command command = commandGenerator.createCommandFromInputLine(inputCommandLine);
    command.execute();
  }
}
