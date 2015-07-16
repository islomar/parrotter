package com.islomar.parrotter.services;

import com.islomar.parrotter.infrastructure.MessageOutput;

/**
 *
 */
public class CommandLineProcessor {

  private final MessageOutput messageOutput;

  public CommandLineProcessor(MessageOutput messageOutput) {

    this.messageOutput = messageOutput;
  }

  public void execute(String inputCommandLine) {

    MessageParser messageParser = new MessageParser();
    Command command = messageParser.parse(inputCommandLine);

    command.execute();
  }
}
