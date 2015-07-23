package com.islomar.parrotter.app;

import com.islomar.parrotter.controller.CommandLineProcessor;
import com.islomar.parrotter.infrastructure.Console;
import com.islomar.parrotter.infrastructure.ScannerProxy;
import com.islomar.parrotter.infrastructure.formatters.MessageFormatter;
import com.islomar.parrotter.infrastructure.repositories.MessageRepository;
import com.islomar.parrotter.infrastructure.repositories.UserRepository;
import com.islomar.parrotter.model.InMemoryMessageRepository;
import com.islomar.parrotter.model.InMemoryUserRepository;
import com.islomar.parrotter.model.UserService;

import java.time.Clock;


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

    CommandLineProcessor commandLineProcessor = createCommandLineProcessor();

    while (true) {
      String inputCommandLine = scanner.nextLine();
      commandLineProcessor.execute(inputCommandLine);
    }
  }

  private CommandLineProcessor createCommandLineProcessor() {

    MessageRepository messageRepository = new InMemoryMessageRepository(clock);
    UserRepository userRepository = new InMemoryUserRepository();
    MessageFormatter messageFormatter = new MessageFormatter(clock);

    UserService userService = new UserService(userRepository, messageRepository, console, messageFormatter);
    return new CommandLineProcessor(userService);
  }
}
