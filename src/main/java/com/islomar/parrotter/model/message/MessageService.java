package com.islomar.parrotter.model.message;

import com.islomar.parrotter.infrastructure.Console;
import com.islomar.parrotter.infrastructure.formatters.MessageFormatter;
import com.islomar.parrotter.infrastructure.repositories.MessageRepository;

import java.time.Clock;
import java.util.List;
import java.util.stream.Collectors;

public class MessageService {

  private final Clock clock;
  private MessageRepository messageRepository;
  private Console console;
  private MessageFormatter messageFormatter;

  public MessageService(final Clock clock, MessageRepository messageRepository, Console console, MessageFormatter messageFormatter) {

    this.clock = clock;
    this.messageRepository = messageRepository;
    this.console = console;
    this.messageFormatter = messageFormatter;
  }

  public void saveMessage(String username, String textMessage) {
    Message message = new Message(username, textMessage, clock.instant());
    messageRepository.saveMessage(message);
  }

  public void printTimelineFor(String username) {

    List<Message> personalMessages = findPersonalMessagesFor(username);
    List<Message> sortedPersonalMessages = personalMessages.stream().sorted().collect(Collectors.toList());
    printListOfMessages(sortedPersonalMessages);
  }

  public List<Message> findPersonalMessagesFor(String username) {
    return messageRepository.findAllMessagesForUser(username);
  }

  private void printListOfMessages(List<Message> messages) {
    messages.forEach(this::printMessage);
  }

  private void printMessage(Message message) {
    console.printMessage(messageFormatter.formatForViewUserTimeline(message));
  }
}
