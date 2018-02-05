package com.islomar.parrotter.actions.utils;

import com.islomar.parrotter.actions.Command;

import java.util.List;


public class CommandRunner {

  private final List<Command> commands;

  public CommandRunner(List<Command> commands) {

    this.commands = commands;
  }

  public void execute(final String inputCommandLine) {

    commands.forEach(command -> command.execute(inputCommandLine));
  }
}
