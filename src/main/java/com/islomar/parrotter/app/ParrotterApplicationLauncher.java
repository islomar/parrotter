package com.islomar.parrotter.app;

import com.google.common.base.Charsets;

import com.islomar.parrotter.actions.Command;
import com.islomar.parrotter.actions.FollowUser;
import com.islomar.parrotter.actions.PostMessage;
import com.islomar.parrotter.actions.ReadUserPersonalTimeline;
import com.islomar.parrotter.actions.ShowUserWall;
import com.islomar.parrotter.actions.utils.CommandRunner;
import com.islomar.parrotter.infrastructure.Console;
import com.islomar.parrotter.infrastructure.formatters.MessageFormatter;
import com.islomar.parrotter.model.message.MessageRepository;
import com.islomar.parrotter.model.user.UserRepository;
import com.islomar.parrotter.infrastructure.repositories.InMemoryMessageRepository;
import com.islomar.parrotter.model.message.MessageService;
import com.islomar.parrotter.infrastructure.repositories.InMemoryUserRepository;
import com.islomar.parrotter.model.user.ShowUserWallService;
import com.islomar.parrotter.model.user.UserService;

import java.time.Clock;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ParrotterApplicationLauncher {

  public static void main(String[] args) {

    Console console = generateConsole();
    Clock clock = Clock.systemUTC();
    List<Command> commands = generateCommands(clock, console);
    CommandRunner commandRunner = new CommandRunner(commands);

    new ParrotterApplication(commandRunner, console).run();
  }

  private static Console generateConsole() {
    Scanner scanner = new Scanner(System.in, Charsets.UTF_8.name());
    return new Console(scanner);
  }

  private static List<Command> generateCommands(Clock clock, Console console) {

    MessageRepository messageRepository = new InMemoryMessageRepository();
    UserRepository userRepository = new InMemoryUserRepository();
    MessageFormatter messageFormatter = new MessageFormatter(clock);

    UserService userService = new UserService(userRepository);
    MessageService messageService = new MessageService(clock, messageRepository, console, messageFormatter);
    ShowUserWallService showUserWallService = new ShowUserWallService(messageService, userService, console, messageFormatter);

    List<Command> commands = new ArrayList<>();
    commands.add(new PostMessage(userService, messageService));
    commands.add(new ReadUserPersonalTimeline(messageService));
    commands.add(new FollowUser(userService));
    commands.add(new ShowUserWall(showUserWallService));

    return commands;
  }

}
