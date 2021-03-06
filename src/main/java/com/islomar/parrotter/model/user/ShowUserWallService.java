package com.islomar.parrotter.model.user;

import com.islomar.parrotter.infrastructure.Console;
import com.islomar.parrotter.infrastructure.formatters.MessageFormatter;
import com.islomar.parrotter.model.message.Message;
import com.islomar.parrotter.model.message.MessageService;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ShowUserWallService {

  private final MessageService messageService;
  private final UserService userService;

  private final Console console;
  private final MessageFormatter messageFormatter;

  public ShowUserWallService(MessageService messageService, UserService userService, Console console, MessageFormatter messageFormatter) {

    this.messageService = messageService;
    this.userService = userService;
    this.console = console;
    this.messageFormatter = messageFormatter;
  }

  public void printUserWallFor(String username) {
    List<Message> personalTimelineMessages = messageService.findPersonalMessagesFor(username);
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
        .map(messageService::findPersonalMessagesFor)
        .flatMap(Collection::stream)
        .collect(Collectors.toList());
  }
}
