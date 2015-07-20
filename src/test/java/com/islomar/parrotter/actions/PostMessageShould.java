package com.islomar.parrotter.actions;

import com.islomar.parrotter.infrastructure.Console;
import com.islomar.parrotter.infrastructure.repositories.MessageRepository;

import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

@Test
public class PostMessageShould {

  private static final String ALICE = "Alice";
  private static final String MESSAGE_TEXT = "I love the weather today";

  @Mock MessageRepository messageRepository;
  @Mock Console console;

  private PostMessage postMessage;


  @BeforeMethod
  public void setUpMethod() {

    initMocks(this);
    postMessage = new PostMessage(messageRepository);
  }

  public void save_a_message_and_show_it_on_timeline() {

    postMessage.execute(ALICE, MESSAGE_TEXT);

    verify(messageRepository).saveMessage(ALICE, MESSAGE_TEXT);
  }
}
