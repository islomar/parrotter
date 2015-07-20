package com.islomar.parrotter.app;

import com.google.common.base.Charsets;

import com.islomar.parrotter.controller.CommandLineProcessor;
import com.islomar.parrotter.infrastructure.Console;
import com.islomar.parrotter.infrastructure.formatters.MessageFormatter;
import com.islomar.parrotter.infrastructure.repositories.MessageRepository;
import com.islomar.parrotter.infrastructure.repositories.UserRepository;
import com.islomar.parrotter.model.message.InMemoryMessageRepository;
import com.islomar.parrotter.model.user.InMemoryUserRepository;
import com.islomar.parrotter.services.MessageService;
import com.islomar.parrotter.services.ShowUserWallService;
import com.islomar.parrotter.services.UserService;

import java.time.Clock;
import java.util.Scanner;


public class ParrotterApplicationLauncher {

  private final Console console;

  public ParrotterApplicationLauncher() {
    this.console = new Console();
  }

  public void run() {

    showWelcomeMessageAndInstructions();

    Scanner scanner = new Scanner(System.in, Charsets.UTF_8.name());
    CommandLineProcessor commandLineProcessor = createCommandLineProcessor();

    while (true) {
      String inputCommandLine = scanner.nextLine();
      commandLineProcessor.execute(inputCommandLine);
    }
  }

  private void showWelcomeMessageAndInstructions() {
    console.printMessage("Welcome to Parroter Application!");
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

    MessageRepository messageRepository = new InMemoryMessageRepository(Clock.systemUTC());
    UserRepository userRepository = new InMemoryUserRepository();
    MessageFormatter messageFormatter = new MessageFormatter(Clock.systemUTC());

    MessageService messageService = new MessageService(messageRepository, console, messageFormatter);
    UserService userService = new UserService(userRepository);
    ShowUserWallService showUserWallService = new ShowUserWallService(messageRepository, userRepository, console, messageFormatter);

    return new CommandLineProcessor(userService, messageService, showUserWallService);
  }
}
