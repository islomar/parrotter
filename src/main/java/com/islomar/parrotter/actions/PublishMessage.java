package com.islomar.parrotter.actions;

import com.islomar.parrotter.infrastructure.repositories.MessageRepository;
import com.islomar.parrotter.model.Message;

public class PublishMessage {

  private MessageRepository messageRepository;

  public PublishMessage(MessageRepository messageRepository) {

    this.messageRepository = messageRepository;
  }

  public void execute(Message message) {
    messageRepository.saveMessage(message);
  }
}
