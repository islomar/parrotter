package com.islomar.parrotter.app;

import com.islomar.parrotter.actions.Command;
import com.islomar.parrotter.actions.FollowUser;
import com.islomar.parrotter.actions.PostMessage;
import com.islomar.parrotter.actions.ReadUserPersonalTimeline;
import com.islomar.parrotter.actions.ShowUserWall;
import com.islomar.parrotter.actions.utils.CommandSelector;
import com.islomar.parrotter.infrastructure.Console;
import com.islomar.parrotter.infrastructure.ScannerProxy;
import com.islomar.parrotter.infrastructure.formatters.MessageFormatter;
import com.islomar.parrotter.infrastructure.repositories.UserRepository;
import com.islomar.parrotter.model.InMemoryUserRepository;
import com.islomar.parrotter.model.UserService;

import java.time.Clock;
import java.util.ArrayList;
import java.util.List;


public class ParrotterApplicationLauncher {

  private final Console console;
  private final Clock clock;
  private final ScannerProxy scanner;

  public ParrotterApplicationLauncher(ScannerProxy scanner, Console console, Clock clock) {
    this.scanner = scanner;
    this.console = console;
    this.clock = clock;
  }

  public void run() {

    List<Command> commands = generateCommands();
    CommandSelector commandSelector = new CommandSelector(commands);

    while (true) {
      String inputCommandLine = scanner.nextLine();

      Command command = commandSelector.selectCommandForInputCommandLine(inputCommandLine);
      command.execute(inputCommandLine);
    }
  }

  private List<Command> generateCommands() {

    UserRepository userRepository = new InMemoryUserRepository(clock);
    MessageFormatter messageFormatter = new MessageFormatter(clock);

    UserService userService = new UserService(userRepository, console, messageFormatter);

    List<Command> commands = new ArrayList<>();
    commands.add(new PostMessage(userService));
    commands.add(new ReadUserPersonalTimeline(userService));
    commands.add(new FollowUser(userService));
    commands.add(new ShowUserWall(userService));

    return commands;
  }
}
