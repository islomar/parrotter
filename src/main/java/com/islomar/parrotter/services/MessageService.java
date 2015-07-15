package com.islomar.parrotter.services;

import com.islomar.parrotter.infrastructure.Console;
import com.islomar.parrotter.infrastructure.repositories.MessageRepository;

import java.util.List;


public class MessageService {

  private final Console console;
  private final MessageRepository messageRepository;


  public MessageService(Console console, MessageRepository messageRepository) {
    this.console = console;
    this.messageRepository = messageRepository;
  }

  public void viewTimelineFor(String username) {
    List<String> userMessages = messageRepository.findAllMessagesForUser(username);
    userMessages.forEach(console::printLine);
  }

  public void saveMessage(String username, String textMessage) {
    messageRepository.saveMessage(username, textMessage);
  }

}
