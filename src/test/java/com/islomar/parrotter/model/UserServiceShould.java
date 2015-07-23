package com.islomar.parrotter.model;

import com.islomar.parrotter.infrastructure.Console;
import com.islomar.parrotter.infrastructure.formatters.MessageFormatter;
import com.islomar.parrotter.infrastructure.repositories.MessageRepository;
import com.islomar.parrotter.infrastructure.repositories.UserRepository;

import org.mockito.InOrder;
import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

@Test
public class UserServiceShould {

  private static final String CHARLIE = "Charlie";
  private static final String ALICE = "Alice";
  private static final String BOB = "Bob";
  private static final String MESSAGE_TEXT_1 = "I love the weather today";
  private static final String MESSAGE_TEXT_2 = "I'm in New York today! Anyone wants to have a coffee?";
  private static final String MESSAGE_TEXT_3 = "Damn! We lost!";
  private static final String MESSAGE_TEXT_4 = "Good game though.";
  private static final java.time.Instant NOW = Instant.now();
  private static final java.time.Instant TWO_MINUTES_AGO = NOW.minus(2, ChronoUnit.MINUTES);
  private static final java.time.Instant FIVE_MINUTES_AGO = NOW.minus(5, ChronoUnit.MINUTES);
  private static final java.time.Instant ONE_MINUTE_AGO = NOW.minus(1, ChronoUnit.MINUTES);
  private static final java.time.Instant FIFTEEN_SECONDS_AGO = NOW.minus(15, ChronoUnit.SECONDS);



  @Mock UserRepository userRepository;
  @Mock Console console;
  @Mock Clock clock;
  @Mock MessageRepository messageRepository;
  @Mock MessageFormatter messageFormatter;

  private UserService userService;

  @BeforeMethod
  public void setUpMethod() {

    initMocks(this);

    given(clock.instant()).willReturn(NOW);
    MessageFormatter messageFormatter = new MessageFormatter(clock);
    userService = new UserService(userRepository, messageRepository, console, messageFormatter);
  }

  public void save_a_user() {

    userService.saveUser(CHARLIE);

    verify(userRepository).saveUser(CHARLIE);
  }

  public void find_a_user() {

    userService.findUserByUsername(CHARLIE);

    verify(userRepository).findUserByUsername(CHARLIE);
  }

  public void save_a_message_and_show_it_on_timeline() {

    userService.saveMessage(ALICE, MESSAGE_TEXT_1);

    verify(messageRepository).saveMessage(ALICE, MESSAGE_TEXT_1);
  }

  public void a_user_can_read_any_user_timeline() {

    Message postedMessaged = new Message(ALICE, MESSAGE_TEXT_1, TWO_MINUTES_AGO);
    given(messageRepository.findAllMessagesForUser(ALICE)).willReturn(Arrays.asList(postedMessaged));

    userService.printTimelineFor(ALICE);

    verify(console).printMessage(MESSAGE_TEXT_1 + " (2 minutes ago)");
  }

  public void find_a_user_personal_messages() {

    userService.findPersonalMessagesFor(ALICE);

    verify(messageRepository).findAllMessagesForUser(ALICE);
  }

  public void a_user_wall_shows_her_personal_timeline_and_her_followed_user_timelines() {

    given(userService.findPersonalMessagesFor(CHARLIE)).willReturn(charlieMessages());
    given(userService.findPersonalMessagesFor(BOB)).willReturn(bobMessages());
    given(userService.findPersonalMessagesFor(ALICE)).willReturn(aliceMessages());
    givenThatCharlieFollowsAliceAndBob();

    userService.printUserWallFor(CHARLIE);

    InOrder inOrder = inOrder(console);
    inOrder.verify(console).printMessage("Charlie - I'm in New York today! Anyone wants to have a coffee? (15 seconds ago)");
    inOrder.verify(console).printMessage("Bob - Damn! We lost! (1 minute ago)");
    inOrder.verify(console).printMessage("Bob - Good game though. (2 minutes ago)");
    inOrder.verify(console).printMessage("Alice - I love the weather today (5 minutes ago)");
  }

  private void givenThatCharlieFollowsAliceAndBob() {
    User charlie = new User(CHARLIE);
    charlie.follow(ALICE);
    charlie.follow(BOB);
    given(userService.findUserByUsername(CHARLIE)).willReturn(charlie);
  }

  private List<Message> charlieMessages() {
    return Arrays.asList(new Message(CHARLIE, MESSAGE_TEXT_2, FIFTEEN_SECONDS_AGO));
  }

  private List<Message> bobMessages() {
    return Arrays.asList(new Message(BOB, MESSAGE_TEXT_3, ONE_MINUTE_AGO),
                         new Message(BOB, MESSAGE_TEXT_4, TWO_MINUTES_AGO));
  }

  private List<Message> aliceMessages() {
    return Arrays.asList(new Message(ALICE, MESSAGE_TEXT_1, FIVE_MINUTES_AGO));
  }

}
