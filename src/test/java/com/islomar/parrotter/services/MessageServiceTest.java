package com.islomar.parrotter.services;

import com.islomar.parrotter.infrastructure.Console;
import com.islomar.parrotter.infrastructure.repositories.MessageRepository;

import org.mockito.Mock;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static java.util.Arrays.asList;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;


@Test
public class MessageServiceTest {

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

    given(messageRepository.findAllMessagesForUser(ALICE)).willReturn(asList("Hello world"));

    messageService.viewTimelineFor(ALICE);

    verify(messageRepository).findAllMessagesForUser(ALICE);
    verify(console).printLine("Hello world");
  }

}
