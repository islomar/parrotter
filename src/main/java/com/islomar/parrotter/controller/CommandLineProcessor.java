package com.islomar.parrotter.controller;

import com.islomar.parrotter.actions.PostMessage;
import com.islomar.parrotter.commands.Command;
import com.islomar.parrotter.commands.CommandGenerator;


public class CommandLineProcessor {

  private final PostMessage postMessage;

  public CommandLineProcessor(PostMessage postMessage) {

    this.postMessage = postMessage;
  }

  public void execute(String inputCommandLine) {

    CommandGenerator commandGenerator = new CommandGenerator(postMessage);
    Command command = commandGenerator.createCommandFromInputLine(inputCommandLine);

    command.execute();
  }
}
