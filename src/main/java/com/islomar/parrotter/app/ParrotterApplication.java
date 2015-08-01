package com.islomar.parrotter.app;

import com.islomar.parrotter.actions.Command;
import com.islomar.parrotter.actions.utils.CommandRunner;
import com.islomar.parrotter.infrastructure.Console;


public class ParrotterApplication {

  private final Console console;
  private final CommandRunner commandRunner;

  public ParrotterApplication(CommandRunner commandRunner, Console console) {
    this.commandRunner = commandRunner;
    this.console = console;
  }

  public void run() {

    while (true) {
      String inputCommandLine = console.nextLine();
      commandRunner.execute(inputCommandLine);
    }
  }
}
