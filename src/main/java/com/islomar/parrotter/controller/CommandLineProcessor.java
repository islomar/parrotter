package com.islomar.parrotter.controller;

import com.islomar.parrotter.actions.Command;
import com.islomar.parrotter.controller.utils.CommandGenerator;
import com.islomar.parrotter.model.UserService;


public class CommandLineProcessor {

  private final CommandGenerator commandGenerator;

  public CommandLineProcessor(UserService userService) {

    commandGenerator = new CommandGenerator(userService);
  }

  public void execute(String inputCommandLine) {

    Command command = commandGenerator.createCommandFromInputLine(inputCommandLine);
    command.execute();
  }
}
