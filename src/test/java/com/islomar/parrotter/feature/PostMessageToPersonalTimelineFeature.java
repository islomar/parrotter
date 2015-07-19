package com.islomar.parrotter.feature;

import com.islomar.parrotter.actions.PublishMessage;
import com.islomar.parrotter.controller.CommandLineProcessor;
import com.islomar.parrotter.infrastructure.Console;
import com.islomar.parrotter.infrastructure.repositories.MessageRepository;
import com.islomar.parrotter.model.InMemoryMessageRepository;

import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Clock;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;


@Test
public class PostMessageToPersonalTimelineFeature {

  private static final String ALICE = "Alice";
  private static final String MESSAGE = "I love the weather today";
  //private static final java.time.Instant TWO_MINUTES_AGO = Instant.now().minus(2, ChronoUnit.MINUTES);

  @Mock private Console console;
  @Mock private Clock clock;

  private PublishMessage publishMessage;


  @BeforeMethod
  public void setUpMethod() {
    initMocks(this);

    MessageRepository messageRepository = new InMemoryMessageRepository(clock);
    publishMessage = new PublishMessage(messageRepository);
  }

  public void a_user_publishes_a_message_to_her_personal_timeline() {

    //when(clock.instant()).thenReturn(TWO_MINUTES_AGO);

    CommandLineProcessor commandLineProcessor = new CommandLineProcessor(publishMessage);
    commandLineProcessor.execute(ALICE + " -> " + MESSAGE);

    verify(console, never()).printMessage(anyString());
  }

}
