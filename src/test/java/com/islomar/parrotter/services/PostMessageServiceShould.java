package com.islomar.parrotter.services;

import com.islomar.parrotter.infrastructure.Console;
import com.islomar.parrotter.infrastructure.repositories.MessageRepository;

import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

@Test
public class PostMessageServiceShould {

  private static final String ALICE = "Alice";
  private static final String MESSAGE_TEXT = "I love the weather today";

  @Mock MessageRepository messageRepository;
  @Mock Console console;

  private PostMessageService postMessageService;



  @BeforeMethod
  public void setUpMethod() {

    initMocks(this);
    postMessageService = new PostMessageService(messageRepository);
  }

  public void save_a_message_and_show_it_on_timeline() {

    postMessageService.execute(ALICE, MESSAGE_TEXT);

    verify(messageRepository).saveMessage(ALICE, MESSAGE_TEXT);
  }

}
