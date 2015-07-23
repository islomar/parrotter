package com.islomar.parrotter.model;

import com.islomar.parrotter.infrastructure.Console;
import com.islomar.parrotter.infrastructure.formatters.MessageFormatter;
import com.islomar.parrotter.model.Message;
import com.islomar.parrotter.model.UserService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ShowUserWallService {

  private UserService userService;

  private Console console;
  private MessageFormatter messageFormatter;

  public ShowUserWallService(UserService userService, Console console, MessageFormatter messageFormatter) {

    this.userService = userService;
    this.console = console;
    this.messageFormatter = messageFormatter;
  }

  public void printUserWallFor(String username) {
    List<Message> personalTimelineMessages = userService.findPersonalMessagesFor(username);
    List<Message> followedUserMessages = getFollowedUserMessages(username);

    printMessages(personalTimelineMessages, followedUserMessages);
  }

  private void printMessages(List<Message> personalTimelineMessages, List<Message> followedUserMessages) {
    Stream.concat(personalTimelineMessages.stream(), followedUserMessages.stream())
        .sorted()
        .forEach(message -> console.printMessage(messageFormatter.formatForTheWall(message)));
  }

  private List<Message> getFollowedUserMessages(String username) {
    Set<String> followedUsers = userService.findUserByUsername(username).getFollowedUsers();

    return followedUsers.stream()
        .map(user -> userService.findPersonalMessagesFor(user))
        .flatMap(messages -> messages.stream())
        .collect(Collectors.toList());
  }
}
