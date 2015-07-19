package com.islomar.parrotter.actions;

import com.islomar.parrotter.infrastructure.Console;
import com.islomar.parrotter.infrastructure.repositories.MessageRepository;
import com.islomar.parrotter.model.Message;

public class PublishMessage {

  private MessageRepository messageRepository;
  private Console console;

  public PublishMessage(MessageRepository messageRepository, Console console) {

    this.messageRepository = messageRepository;
    this.console = console;
  }

  public void execute(Message message) {
    messageRepository.saveMessage(message);
    console.printMessage(message.toString());
  }
}
