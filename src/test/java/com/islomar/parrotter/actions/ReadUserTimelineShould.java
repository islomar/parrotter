package com.islomar.parrotter.actions;

import com.islomar.parrotter.infrastructure.Console;
import com.islomar.parrotter.infrastructure.repositories.MessageRepository;
import com.islomar.parrotter.model.Message;

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
public class ReadUserTimelineShould {

  private static final String ALICE = "Alice";
  private static final String MESSAGE_TEXT = "I love the weather today";
  private static final int TWO = 2;
  private static final java.time.Instant TWO_MINUTES_AGO = Instant.now().minus(TWO, ChronoUnit.MINUTES);

  @Mock private Console console;
  @Mock private Clock clock;
  @Mock private MessageRepository messageRepository;

  private ReadUserTimeline readUserTimeline;


  @BeforeMethod
  public void setUpMethod() {
    initMocks(this);

    readUserTimeline = new ReadUserTimeline(messageRepository, console);
  }

  public void a_user_can_read_any_user_timeline() {

    Message postedMessaged = new Message(ALICE, MESSAGE_TEXT, TWO_MINUTES_AGO);
    given(messageRepository.findAllMessagesForUser(ALICE)).willReturn(Arrays.asList(postedMessaged));

    readUserTimeline.execute(ALICE);

    verify(console).printMessage(MESSAGE_TEXT + " (" + TWO + " minutes ago)");
  }

}
