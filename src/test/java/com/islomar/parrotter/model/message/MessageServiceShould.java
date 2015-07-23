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
  private static final String MESSAGE_TEXT = "I love the weather today";
  private static final java.time.Instant NOW = Instant.now();
  private static final java.time.Instant TWO_MINUTES_AGO = NOW.minus(2, ChronoUnit.MINUTES);

  @Mock MessageRepository messageRepository;
  @Mock Console console;
  @Mock private Clock clock;

  private MessageService messageService;


  @BeforeMethod
  public void setUpMethod() {

    initMocks(this);

    given(clock.instant()).willReturn(NOW);
    MessageFormatter messageFormatter = new MessageFormatter(clock);
    messageService = new MessageService(messageRepository, console, messageFormatter);
  }

  public void save_a_message_and_show_it_on_timeline() {

    messageService.saveMessage(ALICE, MESSAGE_TEXT);

    verify(messageRepository).saveMessage(ALICE, MESSAGE_TEXT);
  }

  public void a_user_can_read_any_user_timeline() {

    Message postedMessaged = new Message(ALICE, MESSAGE_TEXT, TWO_MINUTES_AGO);
    given(messageRepository.findAllMessagesForUser(ALICE)).willReturn(Arrays.asList(postedMessaged));

    messageService.printTimelineFor(ALICE);

    verify(console).printMessage(MESSAGE_TEXT + " (2 minutes ago)");
  }

  public void find_a_user_personal_messages() {

    messageService.findPersonalMessagesFor(ALICE);

    verify(messageRepository).findAllMessagesForUser(ALICE);
  }
}
