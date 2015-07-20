package com.islomar.parrotter.services;

import com.islomar.parrotter.infrastructure.repositories.MessageRepository;

public class PostMessageService {

  private MessageRepository messageRepository;

  public PostMessageService(MessageRepository messageRepository) {

    this.messageRepository = messageRepository;
  }

  public void execute(String username, String textMessage) {
    messageRepository.saveMessage(username, textMessage);
  }
}
