package com.islomar.parrotter.commands;

import com.islomar.parrotter.actions.PublishMessage;
import com.islomar.parrotter.infrastructure.Console;
import com.islomar.parrotter.model.CommandGenerator;


public class CommandLineProcessor {

  private final Console console;
  private final PublishMessage publishMessage;

  public CommandLineProcessor(Console console, PublishMessage publishMessage) {

    this.console = console;
    this.publishMessage = publishMessage;
  }

  public void execute(String inputCommandLine) {

    CommandGenerator commandGenerator = new CommandGenerator(publishMessage);
    Command command = commandGenerator.createCommandFromInputLine(inputCommandLine);

    command.execute();
  }
}
