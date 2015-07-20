package com.islomar.parrotter.services;

import com.islomar.parrotter.infrastructure.Console;
import com.islomar.parrotter.infrastructure.formatters.MessageFormatter;
import com.islomar.parrotter.infrastructure.repositories.MessageRepository;
import com.islomar.parrotter.model.message.Message;

import java.util.List;

public class MessageService {

  private MessageRepository messageRepository;
  private Console console;
  private MessageFormatter messageFormatter;

  public MessageService(MessageRepository messageRepository, Console console, MessageFormatter messageFormatter) {

    this.messageRepository = messageRepository;
    this.console = console;
    this.messageFormatter = messageFormatter;
  }

  public void saveMessage(String username, String textMessage) {
    messageRepository.saveMessage(username, textMessage);
  }

  public void printTimelineFor(String username) {

    List<Message> allMessagesForUser = findPersonalMessagesFor(username);
    allMessagesForUser.stream().forEach(message -> printMessage(message));
  }

  public List<Message> findPersonalMessagesFor(String username) {
    return messageRepository.findAllMessagesForUser(username);
  }

  private void printMessage(Message message) {
    console.printMessage(messageFormatter.formatForViewUserTimeline(message));
  }
}
