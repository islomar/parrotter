package com.islomar.parrotter.feature;

import com.islomar.parrotter.actions.PostMessage;
import com.islomar.parrotter.actions.ReadUserTimeline;
import com.islomar.parrotter.controller.CommandLineProcessor;
import com.islomar.parrotter.infrastructure.Console;
import com.islomar.parrotter.infrastructure.repositories.MessageRepository;
import com.islomar.parrotter.model.InMemoryMessageRepository;

import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

@Test
public class ReadUserTimelineFeature {

  private static final String ALICE = "Alice";
  private static final String MESSAGE_TEXT = "I love the weather today";
  private static final int FIVE = 5;
  private static final java.time.Instant FIVE_SECONDS_AGO = Instant.now().minus(FIVE, ChronoUnit.SECONDS);

  @Mock private Console console;
  @Mock private Clock clock;

  private ReadUserTimeline readUserTimeline;
  private PostMessage postMessage;


  @BeforeMethod
  public void setUpMethod() {
    initMocks(this);

    MessageRepository messageRepository = new InMemoryMessageRepository(clock);
    postMessage = new PostMessage(messageRepository);
    readUserTimeline = new ReadUserTimeline(messageRepository, console);
  }

  public void a_user_publishes_a_message_to_her_personal_timeline() {

    CommandLineProcessor commandLineProcessor = new CommandLineProcessor(clock, postMessage, readUserTimeline);
    given(clock.instant()).willReturn(FIVE_SECONDS_AGO, Instant.now());
    commandLineProcessor.execute(ALICE + " -> " + MESSAGE_TEXT);

    commandLineProcessor.execute(ALICE);

    verify(console).printMessage(MESSAGE_TEXT + " (" + FIVE + " seconds ago)");
  }

}
