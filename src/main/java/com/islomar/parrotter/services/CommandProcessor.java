package com.islomar.parrotter.services;

import com.islomar.parrotter.infrastructure.MessageOutput;

/**
 *
 */
public class CommandProcessor {

  private final MessageOutput messageOutput;

  public CommandProcessor(MessageOutput messageOutput) {

    this.messageOutput = messageOutput;
  }

  public void executeCommandLine(String inputCommandLine) {

    MessageParser messageParser = new MessageParser();
    Command command = messageParser.parse(inputCommandLine);

    command.execute();
  }
}
