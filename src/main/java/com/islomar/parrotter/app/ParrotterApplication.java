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
import com.islomar.parrotter.infrastructure.repositories.MessageRepository;
import com.islomar.parrotter.infrastructure.repositories.UserRepository;
import com.islomar.parrotter.model.message.InMemoryMessageRepository;
import com.islomar.parrotter.model.message.MessageService;
import com.islomar.parrotter.model.user.InMemoryUserRepository;
import com.islomar.parrotter.model.user.ShowUserWallService;
import com.islomar.parrotter.model.user.UserService;

import java.time.Clock;
import java.util.ArrayList;
import java.util.List;


public class ParrotterApplication {

  private final ScannerProxy scanner;
  private final CommandSelector commandSelector;

  public ParrotterApplication(CommandSelector commandSelector, ScannerProxy scanner) {
    this.commandSelector = commandSelector;
    this.scanner = scanner;
  }

  public void run() {

    while (true) {
      String inputCommandLine = scanner.nextLine();

      Command command = commandSelector.selectCommandForInputCommandLine(inputCommandLine);
      command.execute(inputCommandLine);
    }
  }
}
