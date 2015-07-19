package com.islomar.parrotter.actions;

import com.islomar.parrotter.infrastructure.Console;
import com.islomar.parrotter.infrastructure.repositories.MessageRepository;
import com.islomar.parrotter.model.Message;

import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

@Test
public class PostMessageShould {

  private static final String ALICE = "Alice";
  private static final String MESSAGE = "I love the weather today";
  private static final int FIVE = 5;

  @Mock MessageRepository messageRepository;
  @Mock Console console;

  private PostMessage postMessage;


  @BeforeMethod
  public void setUpMethod() {

    initMocks(this);
    postMessage = new PostMessage(messageRepository);
  }

  public void save_a_message_and_show_it_on_timeline() {

    Message postedMessage = new Message(ALICE, MESSAGE, Instant.now().minus(FIVE, ChronoUnit.SECONDS));

    postMessage.execute(postedMessage);

    verify(messageRepository).saveMessage(postedMessage);
  }
}
