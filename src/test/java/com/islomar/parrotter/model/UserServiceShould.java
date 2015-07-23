package com.islomar.parrotter.model;

import com.islomar.parrotter.infrastructure.Console;
import com.islomar.parrotter.infrastructure.formatters.MessageFormatter;
import com.islomar.parrotter.infrastructure.repositories.MessageRepository;
import com.islomar.parrotter.infrastructure.repositories.UserRepository;

import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

@Test
public class UserServiceShould {

  private static final String CHARLIE = "Charlie";
  private static final String ALICE = "Alice";
  private static final String MESSAGE_TEXT = "I love the weather today";
  private static final java.time.Instant NOW = Instant.now();
  private static final java.time.Instant TWO_MINUTES_AGO = NOW.minus(2, ChronoUnit.MINUTES);

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

    userService.saveMessage(ALICE, MESSAGE_TEXT);

    verify(messageRepository).saveMessage(ALICE, MESSAGE_TEXT);
  }

  public void a_user_can_read_any_user_timeline() {

    Message postedMessaged = new Message(ALICE, MESSAGE_TEXT, TWO_MINUTES_AGO);
    given(messageRepository.findAllMessagesForUser(ALICE)).willReturn(Arrays.asList(postedMessaged));

    userService.printTimelineFor(ALICE);

    verify(console).printMessage(MESSAGE_TEXT + " (2 minutes ago)");
  }

  public void find_a_user_personal_messages() {

    userService.findPersonalMessagesFor(ALICE);

    verify(messageRepository).findAllMessagesForUser(ALICE);
  }

}
