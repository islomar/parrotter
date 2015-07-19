package com.islomar.parrotter.actions;

import com.islomar.parrotter.infrastructure.Console;
import com.islomar.parrotter.model.Message;
import com.islomar.parrotter.commands.CommandLineProcessor;

import org.mockito.Mock;
import org.testng.annotations.Test;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static org.mockito.Mockito.verify;


@Test
public class PostMessageToPersonalTimelineFeature {

  private static final String ALICE = "Alice";
  private static final String MESSAGE = "I love the weather today";

  @Mock private Console messageOutput;
  @Mock private PublishMessage publishMessage;

  public void a_user_publishing_a_message_gets_printed_in_the_console() {

    CommandLineProcessor commandLineProcessor = new CommandLineProcessor(messageOutput, publishMessage);

    commandLineProcessor.execute(ALICE + " -> " + MESSAGE);

    Message expectedMessage = new Message(ALICE, MESSAGE, Instant.now().minus(2, ChronoUnit.MINUTES));
    verify(messageOutput).printMessage(MESSAGE + " (2 minutes ago)");
  }

}
