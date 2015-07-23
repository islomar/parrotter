package com.islomar.parrotter.model;

import com.islomar.parrotter.infrastructure.Console;
import com.islomar.parrotter.infrastructure.formatters.MessageFormatter;
import com.islomar.parrotter.infrastructure.repositories.UserRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserService {

  private UserRepository userRepository;
  private Console console;
  private MessageFormatter messageFormatter;

  public UserService(UserRepository userRepository, Console console, MessageFormatter messageFormatter) {

    this.userRepository = userRepository;
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
    createNewUserIfItDoesNotExist(username);
    userRepository.saveMessage(username, textMessage);
  }

  public void printTimelineFor(String username) {

    List<Message> personalMessages = findPersonalMessagesFor(username);
    printListOfMessages(personalMessages);
  }

  public List<Message> findPersonalMessagesFor(String username) {
    return userRepository.findAllMessagesForUser(username);
  }

  public void printUserWallFor(String username) {
    List<Message> personalTimelineMessages = findPersonalMessagesFor(username);
    List<Message> followedUserMessages = getFollowedUserMessages(username);

    printMessages(personalTimelineMessages, followedUserMessages);
  }

  private void printMessages(List<Message> personalTimelineMessages, List<Message> followedUserMessages) {
    Stream.concat(personalTimelineMessages.stream(), followedUserMessages.stream())
        .sorted()
        .forEach(message -> console.printMessage(messageFormatter.formatForTheWall(message)));
  }

  private void createNewUserIfItDoesNotExist(String username) {
    if (userDoesNotExist(username)) {
      userRepository.saveUser(username);
    }
  }

  private boolean userDoesNotExist(String username) {
    return userRepository.findUserByUsername(username) instanceof NullUser;
  }

  private List<Message> getFollowedUserMessages(String username) {
    Set<String> followedUsers = findUserByUsername(username).getFollowedUsers();

    return followedUsers.stream()
        .map(user -> findPersonalMessagesFor(user))
        .flatMap(messages -> messages.stream())
        .collect(Collectors.toList());
  }

  private void printListOfMessages(List<Message> messages) {
    messages.stream().forEach(message -> printMessage(message));
  }

  private void printMessage(Message message) {
    console.printMessage(messageFormatter.formatForViewUserTimeline(message));
  }
}
