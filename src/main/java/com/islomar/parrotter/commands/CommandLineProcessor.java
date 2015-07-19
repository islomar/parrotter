package com.islomar.parrotter.commands;

import com.islomar.parrotter.actions.PublishMessage;
import com.islomar.parrotter.model.MessageOutput;
import com.islomar.parrotter.model.CommandGenerator;

/**
 *
 */
public class CommandLineProcessor {

  private final MessageOutput messageOutput;
  private final PublishMessage publishMessage;

  public CommandLineProcessor(MessageOutput messageOutput, PublishMessage publishMessage) {

    this.messageOutput = messageOutput;
    this.publishMessage = publishMessage;
  }

  public void execute(String inputCommandLine) {

    CommandGenerator commandGenerator = new CommandGenerator(publishMessage);
    Command command = commandGenerator.createCommandFromInputLine(inputCommandLine);

    command.execute();
  }
}
