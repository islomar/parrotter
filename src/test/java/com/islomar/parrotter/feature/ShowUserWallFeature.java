package com.islomar.parrotter.feature;

import com.islomar.parrotter.controller.CommandLineProcessor;
import com.islomar.parrotter.infrastructure.Console;
import com.islomar.parrotter.infrastructure.repositories.MessageRepository;
import com.islomar.parrotter.infrastructure.repositories.UserRepository;
import com.islomar.parrotter.model.message.InMemoryMessageRepository;
import com.islomar.parrotter.infrastructure.formatters.MessageFormatter;
import com.islomar.parrotter.model.user.InMemoryUserRepository;
import com.islomar.parrotter.services.PostMessageService;
import com.islomar.parrotter.services.ReadUserTimelineService;
import com.islomar.parrotter.services.ShowUserWallService;
import com.islomar.parrotter.services.UserService;

import org.mockito.InOrder;
import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static com.islomar.parrotter.actions.utils.CommandType.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.inOrder;
import static org.mockito.MockitoAnnotations.initMocks;


@Test
public class ShowUserWallFeature {

  private static final String ALICE = "Alice";
  private static final String CHARLIE = "Charlie";
  private static final String CHARLIE_MESSAGE_TEXT = "I'm in New York today! Anyone wants to have a coffee?";
  private static final String ALICE_MESSAGE_TEXT = "I love the weather today";
  private static final int TWO = 2;
  private static final int FIVE = 5;
  private static final java.time.Instant NOW = Instant.now();
  private static final java.time.Instant FIVE_MINUTES_AGO = NOW.minus(FIVE, ChronoUnit.MINUTES);
  private static final java.time.Instant TWO_SECONDS_AGO = NOW.minus(TWO, ChronoUnit.SECONDS);

  @Mock Console console;
  @Mock Clock clockForMessageFormatter;
  @Mock Clock clock;

  private ReadUserTimelineService readUserTimelineService;
  private PostMessageService postMessageService;
  private ShowUserWallService showUserWallService;
  private UserService userService;


  @BeforeMethod
  public void setUpMethod() {
    initMocks(this);

    MessageRepository messageRepository = new InMemoryMessageRepository(clock);
    UserRepository userRepository = new InMemoryUserRepository();
    postMessageService = new PostMessageService(messageRepository);

    given(clockForMessageFormatter.instant()).willReturn(NOW);
    MessageFormatter messageFormatter = new MessageFormatter(clockForMessageFormatter);
    showUserWallService = new ShowUserWallService(messageRepository, userRepository, console, messageFormatter);

    userService = new UserService(userRepository);
    readUserTimelineService = new ReadUserTimelineService(messageRepository, console, messageFormatter);
  }


  public void when_charlie_follows_alice_then_his_wall_shows_both_his_personal_timeline_and_alice_timeline() {

    CommandLineProcessor commandLineProcessor = new CommandLineProcessor(userService, postMessageService, readUserTimelineService, showUserWallService);
    given(clock.instant()).willReturn(FIVE_MINUTES_AGO, TWO_SECONDS_AGO);

    commandLineProcessor.execute(ALICE + POST.symbol() + ALICE_MESSAGE_TEXT);
    commandLineProcessor.execute(CHARLIE + POST.symbol() + CHARLIE_MESSAGE_TEXT);
    commandLineProcessor.execute(CHARLIE + FOLLOWS.symbol() + ALICE);

    commandLineProcessor.execute(CHARLIE + WALL.symbol());

    InOrder inOrder = inOrder(console);
    inOrder.verify(console).printMessage("Charlie - I'm in New York today! Anyone wants to have a coffee? (2 seconds ago)");
    inOrder.verify(console).printMessage("Alice - I love the weather today (5 minutes ago)");
  }
}
