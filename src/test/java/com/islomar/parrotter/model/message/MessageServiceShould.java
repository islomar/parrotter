package com.islomar.parrotter.model.message;

import com.islomar.parrotter.infrastructure.Console;
import com.islomar.parrotter.infrastructure.formatters.MessageFormatter;
import com.islomar.parrotter.infrastructure.repositories.MessageRepository;

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
public class MessageServiceShould {

  private static final String ALICE = "Alice";
  private static final String MESSAGE_TEXT_1 = "I love the weather today";
  private static final String MESSAGE_TEXT_2 = "I'm in New York today! Anyone wants to have a coffee?";
  private static final java.time.Instant NOW = Instant.now();
  private static final java.time.Instant FIVE_SECONDS_AGO = NOW.minus(5, ChronoUnit.SECONDS);
  private static final java.time.Instant TWO_MINUTES_AGO = NOW.minus(2, ChronoUnit.MINUTES);

  @Mock MessageRepository messageRepository;
  @Mock Console console;
  @Mock Clock clock;
  @Mock MessageFormatter messageFormatter;

  private MessageService messageService;


  @BeforeMethod
  public void setUpMethod() {

    initMocks(this);

    given(clock.instant()).willReturn(NOW);
    messageService = new MessageService(clock, messageRepository, console, messageFormatter);
  }

  public void save_a_message_and_show_it_on_timeline() {

    messageService.saveMessage(ALICE, MESSAGE_TEXT_1);

    Message message = new Message(ALICE, MESSAGE_TEXT_1, clock.instant());
    verify(messageRepository).saveMessage(message);
  }

  public void a_user_can_read_any_user_timeline() {

    Message messageFromTwoMinutesAgo = new Message(ALICE, MESSAGE_TEXT_1, TWO_MINUTES_AGO);
    Message messageFromFiveSecondsAgo = new Message(ALICE, MESSAGE_TEXT_2, FIVE_SECONDS_AGO);
    given(messageRepository.findAllMessagesForUser(ALICE)).willReturn(Arrays.asList(messageFromFiveSecondsAgo, messageFromTwoMinutesAgo));
    String printedmessageFromTwoMinutesAgo = MESSAGE_TEXT_2 + " (5 seconds ago)";
    String printedmessageFromFiveSecondsAgo = MESSAGE_TEXT_1 + " (2 minutes ago)";
    given(messageFormatter.formatForViewUserTimeline(messageFromTwoMinutesAgo)).willReturn(printedmessageFromTwoMinutesAgo);
    given(messageFormatter.formatForViewUserTimeline(messageFromFiveSecondsAgo)).willReturn(printedmessageFromFiveSecondsAgo);

    messageService.printTimelineFor(ALICE);

    verify(console).printMessage(printedmessageFromFiveSecondsAgo);
    verify(console).printMessage(printedmessageFromTwoMinutesAgo);
  }

  public void find_a_user_personal_messages() {

    messageService.findPersonalMessagesFor(ALICE);

    verify(messageRepository).findAllMessagesForUser(ALICE);
  }
}
