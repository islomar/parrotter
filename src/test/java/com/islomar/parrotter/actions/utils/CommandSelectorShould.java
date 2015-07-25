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
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;

@Test
public class CommandSelectorShould {

  private static final String ALICE = "Alice";
  private static final String BOB = "Bob";
  private static final String ALICE_MESSAGE_TEXT = "I love the weather today";
  private static final java.time.Instant NOW = Instant.now();

  @Mock Console console;
  @Mock Clock clock;
  @Mock Clock clockForMessageFormatter;

  private MessageService messageService;
  private ShowUserWallService showUserWallService;
  private UserService userService;

  private CommandSelector commandSelector;


  @BeforeMethod
  public void setUpMethod() {
    initMocks(this);

    given(clockForMessageFormatter.instant()).willReturn(NOW);
    List<Command> commands = generateCommands();
    commandSelector = new CommandSelector(commands);
  }

  public void create_a_postmessage_command_for_post_commandline() {

    Command command = commandSelector.selectCommandForInputCommandLine(ALICE + POST + ALICE_MESSAGE_TEXT);

    assertThat(command, instanceOf(PostMessage.class));
  }

  public void create_a_readuserpersonaltimeline_command_for_read_personal_timeline_commandline() {

    Command command = commandSelector.selectCommandForInputCommandLine(ALICE);

    assertThat(command, instanceOf(ReadUserPersonalTimeline.class));
  }

  public void follow_another_user() {

    Command command = commandSelector.selectCommandForInputCommandLine(ALICE + FOLLOWS + BOB);

    assertThat(command, instanceOf(FollowUser.class));
  }

  public void show_a_user_wall() {

    Command command = commandSelector.selectCommandForInputCommandLine(ALICE + ShowUserWall.WALL);

    assertThat(command, instanceOf(ShowUserWall.class));
  }

  private List<Command> generateCommands() {
    MessageFormatter messageFormatter = new MessageFormatter(clockForMessageFormatter);

    MessageRepository messageRepository = new InMemoryMessageRepository(clock);
    messageService = new MessageService(messageRepository, console, messageFormatter);

    UserRepository userRepository = new InMemoryUserRepository();
    userService = new UserService(userRepository);
    showUserWallService = new ShowUserWallService(messageService, userService, console, messageFormatter);

    List<Command> commands = new ArrayList<>();
    commands.add(new PostMessage(userService, messageService));
    commands.add(new ReadUserPersonalTimeline(messageService));
    commands.add(new FollowUser(userService));
    commands.add(new ShowUserWall(showUserWallService));

    return commands;
  }
}
