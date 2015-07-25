package com.islomar.parrotter.actions.utils;

import com.islomar.parrotter.actions.Command;

import java.util.List;


public class CommandSelector {

  private final List<Command> commands;

  public CommandSelector(List<Command> commands) {

    this.commands = commands;
  }

  public Command selectCommandForInputCommandLine(final String inputCommandLine) {

    return commands.stream().filter(command -> command.canExecuteCommandline(inputCommandLine)).findFirst().get();
  }
}
