package com.islomar.parrotter.services;

import com.islomar.parrotter.infrastructure.Console;
import com.islomar.parrotter.infrastructure.repositories.MessageRepository;
import com.islomar.parrotter.infrastructure.repositories.UserRepository;
import com.islomar.parrotter.model.message.Message;
import com.islomar.parrotter.model.message.MessageFormatter;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ShowUserWallService {

  private final MessageRepository messageRepository;
  private final UserRepository userRepository;
  private final Console console;
  private MessageFormatter messageFormatter;

  public ShowUserWallService(MessageRepository messageRepository, UserRepository userRepository, Console console, MessageFormatter messageFormatter) {
    this.messageRepository = messageRepository;
    this.userRepository = userRepository;
    this.console = console;
    this.messageFormatter = messageFormatter;
  }

  public void execute(String username) {
    List<Message> personalTimelineMessages = messageRepository.findAllMessagesForUser(username);
    List<Message> followedUserMessages = getFollowedUserMessages(username);

    Stream.concat(personalTimelineMessages.stream(), followedUserMessages.stream())
        .sorted()
        .forEach(message -> console.printMessage(messageFormatter.formatForTheWall(message)));
  }

  private List<Message> getFollowedUserMessages(String username) {
    Set<String> followedUsers = userRepository.getUser(username).getFollowedUsers();

    return followedUsers.stream()
        .map(user -> messageRepository.findAllMessagesForUser(user))
        .flatMap(messages -> messages.stream())
        .collect(Collectors.toList());
  }
}
