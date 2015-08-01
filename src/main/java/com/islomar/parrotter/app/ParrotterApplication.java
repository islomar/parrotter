package com.islomar.parrotter.app;

import com.islomar.parrotter.actions.Command;
import com.islomar.parrotter.actions.utils.CommandSelector;
import com.islomar.parrotter.infrastructure.Console;


public class ParrotterApplication {

  private final Console console;
  private final CommandSelector commandSelector;

  public ParrotterApplication(CommandSelector commandSelector, Console console) {
    this.commandSelector = commandSelector;
    this.console = console;
  }

  public void run() {

    while (true) {
      String inputCommandLine = console.nextLine();

      Command command = commandSelector.selectCommandForInputCommandLine(inputCommandLine);
      command.execute(inputCommandLine);
    }
  }
}
