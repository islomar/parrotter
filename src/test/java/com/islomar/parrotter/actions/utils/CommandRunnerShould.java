package com.islomar.parrotter.actions.utils;

import com.islomar.parrotter.actions.Command;
import com.islomar.parrotter.actions.FollowUser;
import com.islomar.parrotter.actions.PostMessage;
import com.islomar.parrotter.actions.ReadUserPersonalTimeline;
import com.islomar.parrotter.actions.ShowUserWall;
import com.islomar.parrotter.infrastructure.Console;
import com.islomar.parrotter.infrastructure.formatters.MessageFormatter;
import com.islomar.parrotter.infrastructure.repositories.MessageRepository;
import com.islomar.parrotter.infrastructure.repositories.UserRepository;
import com.islomar.parrotter.model.message.InMemoryMessageRepository;
import com.islomar.parrotter.model.message.MessageService;
import com.islomar.parrotter.model.user.InMemoryUserRepository;
import com.islomar.parrotter.model.user.ShowUserWallService;
import com.islomar.parrotter.model.user.UserService;

import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Clock;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static com.islomar.parrotter.actions.FollowUser.FOLLOWS;
import static com.islomar.parrotter.actions.PostMessage.POST;
import static com.islomar.parrotter.actions.ShowUserWall.WALL;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

@Test
public class CommandRunnerShould {

  private static final String ALICE = "Alice";
  private static final String BOB = "Bob";
  private static final String ALICE_MESSAGE_TEXT = "I love the weather today";
  private static final java.time.Instant NOW = Instant.now();

  @Mock Console console;
  @Mock Clock clock;

  @Mock PostMessage postMessage;
  @Mock ReadUserPersonalTimeline readUserPersonalTimeline;
  @Mock FollowUser followUser;
  @Mock ShowUserWall showUserWall;

  private MessageService messageService;
  private ShowUserWallService showUserWallService;
  private UserService userService;

  private CommandRunner commandRunner;
  private List<Command> commands;


  @BeforeMethod
  public void setUpMethod() {
    initMocks(this);

    given(clock.instant()).willReturn(NOW);
    commands = generateCommands();
    commandRunner = new CommandRunner(commands);
  }

  public void execute_a_postmessage_command_for_post_commandline() {

    commandRunner.execute(ALICE + POST + ALICE_MESSAGE_TEXT);

    verify(postMessage).execute(ALICE + POST + ALICE_MESSAGE_TEXT);
  }

  public void execute_a_readuserpersonaltimeline_command_for_read_personal_timeline_commandline() {

    commandRunner.execute(ALICE);

    verify(readUserPersonalTimeline).execute(ALICE);
  }

  public void execute_another_user() {

    commandRunner.execute(ALICE + FOLLOWS + BOB);

    verify(followUser).execute(ALICE + FOLLOWS + BOB);
  }

  public void execute_a_user_wall() {

    commandRunner.execute(ALICE + WALL);

    verify(showUserWall).execute(ALICE + WALL);
  }

  private List<Command> generateCommands() {
    MessageFormatter messageFormatter = new MessageFormatter(clock);

    MessageRepository messageRepository = new InMemoryMessageRepository();
    messageService = new MessageService(clock, messageRepository, console, messageFormatter);

    UserRepository userRepository = new InMemoryUserRepository();
    userService = new UserService(userRepository);
    showUserWallService = new ShowUserWallService(messageService, userService, console, messageFormatter);

    List<Command> commands = new ArrayList<>();
    commands.add(postMessage);
    commands.add(readUserPersonalTimeline);
    commands.add(followUser);
    commands.add(showUserWall);

    return commands;
  }
}
