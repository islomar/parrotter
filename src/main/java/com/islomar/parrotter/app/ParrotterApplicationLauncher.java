package com.islomar.parrotter.app;

import com.islomar.parrotter.controller.CommandLineProcessor;
import com.islomar.parrotter.infrastructure.Console;
import com.islomar.parrotter.infrastructure.ScannerProxy;
import com.islomar.parrotter.infrastructure.formatters.MessageFormatter;
import com.islomar.parrotter.infrastructure.repositories.MessageRepository;
import com.islomar.parrotter.infrastructure.repositories.UserRepository;
import com.islomar.parrotter.model.message.InMemoryMessageRepository;
import com.islomar.parrotter.model.message.MessageService;
import com.islomar.parrotter.model.user.InMemoryUserRepository;
import com.islomar.parrotter.model.user.ShowUserWallService;
import com.islomar.parrotter.model.user.UserService;

import java.time.Clock;


public class ParrotterApplicationLauncher {

  private final Console console;
  private final Clock clock;
  private final ScannerProxy scanner;
  protected boolean running;

  public ParrotterApplicationLauncher(ScannerProxy scanner, Console console, Clock clock) {
    this.scanner = scanner;
    this.console = console;
    this.clock = clock;
    this.running = true;
  }

  public void run() {

    showWelcomeMessageAndInstructions();

    CommandLineProcessor commandLineProcessor = createCommandLineProcessor();

    while (true) {
      String inputCommandLine = scanner.nextLine();
      commandLineProcessor.execute(inputCommandLine);
    }
  }

  private void showWelcomeMessageAndInstructions() {
    console.printMessage("Welcome to Parroter!");
    console.printMessage("You can execute any of these commands:\n");
    console.printMessage("posting:\t <user name> -> <message>");
    console.printMessage("reading:\t <user name>");
    console.printMessage("following:\t <user name> follows <another user>");
    console.printMessage("wall:\t\t <user name> wall");
    console.printMessage("\n");
    console.printMessage("So now, just start parrotting!!");
    console.printMessage("\n");
  }

  private CommandLineProcessor createCommandLineProcessor() {

    MessageRepository messageRepository = new InMemoryMessageRepository(clock);
    UserRepository userRepository = new InMemoryUserRepository();
    MessageFormatter messageFormatter = new MessageFormatter(clock);

    UserService userService = new UserService(userRepository);
    MessageService messageService = new MessageService(messageRepository, console, messageFormatter);
    ShowUserWallService showUserWallService = new ShowUserWallService(messageService, userService, console, messageFormatter);

    return new CommandLineProcessor(userService, messageService, showUserWallService);
  }
}
