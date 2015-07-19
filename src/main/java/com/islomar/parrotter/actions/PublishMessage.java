package com.islomar.parrotter.actions;

import com.islomar.parrotter.infrastructure.repositories.MessageRepository;
import com.islomar.parrotter.model.Message;
import com.islomar.parrotter.model.MessageOutput;

public class PublishMessage {

  private MessageRepository messageRepository;
  private MessageOutput messageOutput;

  public PublishMessage(MessageRepository messageRepository, MessageOutput messageOutput) {

    this.messageRepository = messageRepository;
    this.messageOutput = messageOutput;
  }

  public void execute(Message message) {
    messageRepository.saveMessage(message);
    messageOutput.printMessage(message.toString());
  }
}
