package com.islomar.parrotter.services;

import com.islomar.parrotter.infrastructure.Console;
import com.islomar.parrotter.infrastructure.repositories.MessageRepository;
import com.islomar.parrotter.model.Message;

import org.mockito.Mock;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Instant;

import static java.util.Arrays.asList;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;


@Test
public class MessageServiceShould {

  private static final String ALICE = "Alice";

  @Mock
  MessageRepository messageRepository;

  @Mock
  Console console;

  private MessageService messageService;

  @BeforeClass
  public void setUp() {
    initMocks(this);
    messageService = new MessageService(console, messageRepository);
  }

  public void view_the_timeline_for_a_user() {

    String helloWorldText = "Hello world";
    Message helloWorldMessage = new Message(ALICE, helloWorldText, Instant.now());
    given(messageRepository.findAllMessagesForUser(ALICE)).willReturn(asList(helloWorldMessage));

    messageService.viewTimelineFor(ALICE);

    verify(messageRepository).findAllMessagesForUser(ALICE);
    verify(console).printMessage(helloWorldMessage.toString());
  }

  public void publish_a_message_for_a_user() {

    Message message = new Message(ALICE, "I love the weather today", Instant.now());

    messageService.publishMessage(message);

    verify(messageRepository).saveMessage(message);
    verify(console).printMessage(message.toString());
  }

}
