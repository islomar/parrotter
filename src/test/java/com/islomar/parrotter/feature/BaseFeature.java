package com.islomar.parrotter.feature;

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

public class BaseFeature {

  protected CommandRunner generateCommandSelector(Clock clock, Console console) {
    List<Command> commands = generateCommands(clock, console);
    return new CommandRunner(commands);
  }

  private List<Command> generateCommands(Clock clock, Console console) {

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
