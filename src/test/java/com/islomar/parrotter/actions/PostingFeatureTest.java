package com.islomar.parrotter.actions;

import com.islomar.parrotter.infrastructure.MessageOutput;
import com.islomar.parrotter.model.Message;
import com.islomar.parrotter.services.CommandLineProcessor;

import org.mockito.Mock;
import org.testng.annotations.Test;

import java.time.Instant;

import static org.mockito.Mockito.verify;


@Test
public class PostingFeatureTest {

  private static final String ALICE = "Alice";
  private static final String MESSAGE = "I love the weather today";

  @Mock
  private MessageOutput messageOutput;

  public void a_user_posting_a_message_gets_printed_in_the_console() {

    CommandLineProcessor commandLineProcessor = new CommandLineProcessor(messageOutput);

    commandLineProcessor.execute(ALICE + " -> " + MESSAGE);

    Message expectedMessage = new Message(ALICE, MESSAGE, Instant.now());
    verify(messageOutput).printMessage(expectedMessage);
  }

}
