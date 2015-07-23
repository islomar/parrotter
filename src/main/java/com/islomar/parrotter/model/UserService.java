package com.islomar.parrotter.model;

import com.islomar.parrotter.infrastructure.Console;
import com.islomar.parrotter.infrastructure.formatters.MessageFormatter;
import com.islomar.parrotter.infrastructure.repositories.MessageRepository;
import com.islomar.parrotter.infrastructure.repositories.UserRepository;

import java.util.List;

public class UserService {

  private UserRepository userRepository;
  private MessageRepository messageRepository;
  private Console console;
  private MessageFormatter messageFormatter;

  public UserService(UserRepository userRepository, MessageRepository messageRepository, Console console, MessageFormatter messageFormatter) {

    this.userRepository = userRepository;
    this.messageRepository = messageRepository;
    this.console = console;
    this.messageFormatter = messageFormatter;
  }

  public void saveUser(String username) {
    userRepository.saveUser(username);
  }

  public User findUserByUsername(String username) {
    return userRepository.findUserByUsername(username);
  }

  public void saveMessage(String username, String textMessage) {
    messageRepository.saveMessage(username, textMessage);
  }

  public void printTimelineFor(String username) {

    List<Message> personalMessages = findPersonalMessagesFor(username);
    printListOfMessages(personalMessages);
  }

  public List<Message> findPersonalMessagesFor(String username) {
    return messageRepository.findAllMessagesForUser(username);
  }

  private void printListOfMessages(List<Message> messages) {
    messages.stream().forEach(message -> printMessage(message));
  }

  private void printMessage(Message message) {
    console.printMessage(messageFormatter.formatForViewUserTimeline(message));
  }
}
