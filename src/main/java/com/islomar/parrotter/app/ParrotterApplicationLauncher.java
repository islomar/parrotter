package com.islomar.parrotter.app;

import com.google.common.base.Charsets;

import com.islomar.parrotter.actions.FollowUser;
import com.islomar.parrotter.actions.PostMessage;
import com.islomar.parrotter.actions.ReadUserTimeline;
import com.islomar.parrotter.controller.CommandLineProcessor;
import com.islomar.parrotter.infrastructure.Console;
import com.islomar.parrotter.infrastructure.repositories.MessageRepository;
import com.islomar.parrotter.infrastructure.repositories.UserRepository;
import com.islomar.parrotter.model.message.InMemoryMessageRepository;
import com.islomar.parrotter.model.user.InMemoryUserRepository;

import java.time.Clock;
import java.util.Scanner;


public class ParrotterApplicationLauncher {

  private final Console console;

  public ParrotterApplicationLauncher(Console console) {
    this.console = console;
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
    PostMessage postMessage = new PostMessage(messageRepository);
    ReadUserTimeline readUserTimeline = new ReadUserTimeline(messageRepository, console);

    UserRepository userRepository = new InMemoryUserRepository();
    FollowUser followUser = new FollowUser(userRepository);

    CommandLineProcessor commandLineProcessor = new CommandLineProcessor(postMessage, readUserTimeline, followUser);

    return commandLineProcessor;
  }
}
