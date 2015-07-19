package com.islomar.parrotter.actions;

import com.islomar.parrotter.infrastructure.Console;
import com.islomar.parrotter.infrastructure.repositories.MessageRepository;
import com.islomar.parrotter.model.Message;

import java.util.List;


public class ReadUserTimeline {

  private final MessageRepository messageRepository;
  private final Console console;

  public ReadUserTimeline(MessageRepository messageRepository, Console console) {

    this.messageRepository = messageRepository;
    this.console = console;
  }

  public void execute(String username) {
    List<Message> allMessagesForUser = messageRepository.findAllMessagesForUser(username);
    allMessagesForUser.stream().forEach(m -> console.printMessage(m.formatForViewUserTimeline()));
  }
}
