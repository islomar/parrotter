package com.islomar.parrotter.services;


import com.islomar.parrotter.infrastructure.MessageOutput;

/**
 *
 */
public class PublishMessageCommand implements Command {

  private final MessageOutput messageOutput;

  public PublishMessageCommand(MessageOutput messageOutput) {

    this.messageOutput = messageOutput;
  }

  @Override
  public void execute() {
    messageOutput.printMessage(null);
  }
}
