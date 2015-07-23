package com.islomar.parrotter.controller;

import com.islomar.parrotter.infrastructure.Console;
import com.islomar.parrotter.infrastructure.formatters.MessageFormatter;
import com.islomar.parrotter.infrastructure.repositories.MessageRepository;
import com.islomar.parrotter.infrastructure.repositories.UserRepository;
import com.islomar.parrotter.model.message.InMemoryMessageRepository;
import com.islomar.parrotter.model.message.MessageService;
import com.islomar.parrotter.model.user.InMemoryUserRepository;
import com.islomar.parrotter.model.user.ShowUserWallService;
import com.islomar.parrotter.model.user.UserService;

import org.mockito.InOrder;
import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static com.islomar.parrotter.controller.utils.CommandType.FOLLOWS;
import static com.islomar.parrotter.controller.utils.CommandType.POST;
import static com.islomar.parrotter.controller.utils.CommandType.WALL;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

@Test
public class CommandLineProcessorShould {

  private static final String ALICE = "Alice";
  private static final String BOB = "Bob";
  private static final String CHARLIE = "Charlie";
  private static final String CHARLIE_MESSAGE_TEXT = "I'm in New York today! Anyone wants to have a coffee?";
  private static final String ALICE_MESSAGE_TEXT = "I love the weather today";
  private static final String BOB_MESSAGE_TEXT_1 = "Damn! We lost!";
  private static final String BOB_MESSAGE_TEXT_2 = "Good game though.";

  private static final int TWO = 2;
  private static final int FIVE = 5;
  private static final int ONE = 1;
  private static final int FIFTEEN = 15;

  private static final java.time.Instant NOW = Instant.now();
  private static final java.time.Instant FIVE_SECONDS_AGO = Instant.now().minus(FIVE, ChronoUnit.SECONDS);
  private static final java.time.Instant FIVE_MINUTES_AGO = NOW.minus(FIVE, ChronoUnit.MINUTES);
  private static final java.time.Instant TWO_MINUTES_AGO = NOW.minus(TWO, ChronoUnit.MINUTES);
  private static final java.time.Instant ONE_MINUTE_AGO = NOW.minus(ONE, ChronoUnit.MINUTES);
  private static final java.time.Instant FIFTEEN_SECONDS_AGO = NOW.minus(FIFTEEN, ChronoUnit.SECONDS);


  @Mock Console console;
  @Mock Clock clock;
  @Mock Clock clockForMessageFormatter;

  private MessageService messageService;
  private ShowUserWallService showUserWallService;
  private UserService userService;


  @BeforeMethod
  public void setUpMethod() {
    initMocks(this);

    given(clockForMessageFormatter.instant()).willReturn(NOW);
    MessageFormatter messageFormatter = new MessageFormatter(clockForMessageFormatter);

    MessageRepository messageRepository = new InMemoryMessageRepository(clock);
    messageService = new MessageService(messageRepository, console, messageFormatter);

    UserRepository userRepository = new InMemoryUserRepository();
    userService = new UserService(userRepository);
    showUserWallService = new ShowUserWallService(messageService, userService, console, messageFormatter);
  }

  public void post_a_user_message() {

    CommandLineProcessor commandLineProcessor = new CommandLineProcessor(userService, messageService, showUserWallService);
    commandLineProcessor.execute(ALICE + " -> " + ALICE_MESSAGE_TEXT);

    verify(console, never()).printMessage(anyString());
  }

  public void print_a_user_personal_timeline() {

    CommandLineProcessor commandLineProcessor = new CommandLineProcessor(userService, messageService, showUserWallService);
    given(clock.instant()).willReturn(TWO_MINUTES_AGO, FIVE_SECONDS_AGO, Instant.now());
    commandLineProcessor.execute(BOB + " -> " + BOB_MESSAGE_TEXT_1);
    commandLineProcessor.execute(BOB + " -> " + BOB_MESSAGE_TEXT_2);

    commandLineProcessor.execute(BOB);

    InOrder inOrder = inOrder(console);
    inOrder.verify(console).printMessage(BOB_MESSAGE_TEXT_1 + " (" + 2 + " minutes ago)");
    inOrder.verify(console).printMessage(BOB_MESSAGE_TEXT_2 + " (" + FIVE + " seconds ago)");
  }

  public void follow_another_user() {

    CommandLineProcessor commandLineProcessor = new CommandLineProcessor(userService, messageService, showUserWallService);
    commandLineProcessor.execute(ALICE + " follows " + BOB);

    verify(console, never()).printMessage(anyString());
  }

  public void show_a_user_wall() {

    CommandLineProcessor commandLineProcessor = new CommandLineProcessor(userService, messageService, showUserWallService);
    given(clock.instant()).willReturn(FIFTEEN_SECONDS_AGO, ONE_MINUTE_AGO, TWO_MINUTES_AGO, FIVE_MINUTES_AGO);
    commandLineProcessor.execute(CHARLIE + POST.symbol() + CHARLIE_MESSAGE_TEXT);
    commandLineProcessor.execute(BOB + POST.symbol() + BOB_MESSAGE_TEXT_1);
    commandLineProcessor.execute(BOB + POST.symbol() + BOB_MESSAGE_TEXT_2);
    commandLineProcessor.execute(ALICE + POST.symbol() + ALICE_MESSAGE_TEXT);
    commandLineProcessor.execute(CHARLIE + FOLLOWS.symbol() + ALICE);
    commandLineProcessor.execute(CHARLIE + FOLLOWS.symbol() + BOB);

    commandLineProcessor.execute(CHARLIE + WALL.symbol());

    InOrder inOrder = inOrder(console);
    inOrder.verify(console).printMessage("Charlie - I'm in New York today! Anyone wants to have a coffee? (15 seconds ago)");
    inOrder.verify(console).printMessage("Bob - Damn! We lost! (1 minute ago)");
    inOrder.verify(console).printMessage("Bob - Good game though. (2 minutes ago)");
    inOrder.verify(console).printMessage("Alice - I love the weather today (5 minutes ago)");
  }
}
