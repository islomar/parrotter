package com.islomar.parrotter.services;

import com.islomar.parrotter.infrastructure.Console;
import com.islomar.parrotter.infrastructure.formatters.MessageFormatter;
import com.islomar.parrotter.infrastructure.repositories.MessageRepository;
import com.islomar.parrotter.model.message.Message;

import java.util.List;


public class ReadUserTimelineService {

  private final MessageRepository messageRepository;
  private final Console console;
  private final MessageFormatter messageFormatter;

  public ReadUserTimelineService(MessageRepository messageRepository, Console console, MessageFormatter messageFormatter) {

    this.messageRepository = messageRepository;
    this.console = console;
    this.messageFormatter = messageFormatter;
  }

  public void execute(String username) {

    List<Message> allMessagesForUser = messageRepository.findAllMessagesForUser(username);
    allMessagesForUser.stream().forEach(message -> printMessage(message));
  }

  private void printMessage(Message message) {
    console.printMessage(messageFormatter.formatForViewUserTimeline(message));
  }
}
