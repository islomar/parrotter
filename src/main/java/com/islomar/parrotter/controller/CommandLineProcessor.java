package com.islomar.parrotter.controller;

import com.islomar.parrotter.actions.PublishMessage;
import com.islomar.parrotter.commands.Command;
import com.islomar.parrotter.commands.CommandGenerator;


public class CommandLineProcessor {

  private final PublishMessage publishMessage;

  public CommandLineProcessor(PublishMessage publishMessage) {

    this.publishMessage = publishMessage;
  }

  public void execute(String inputCommandLine) {

    CommandGenerator commandGenerator = new CommandGenerator(publishMessage);
    Command command = commandGenerator.createCommandFromInputLine(inputCommandLine);

    command.execute();
  }
}
