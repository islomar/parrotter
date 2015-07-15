package com.islomar.parrotter.actions;

import com.islomar.parrotter.infrastructure.Console;
import com.islomar.parrotter.infrastructure.repositories.InMemoryMessageRepository;
import com.islomar.parrotter.infrastructure.repositories.MessageRepository;
import com.islomar.parrotter.services.MessageService;

import org.mockito.Mock;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

@Test
public class ViewUserTimelineTest {

  private static final String ALICE = "Alice";
  private static final String NON_EXISTING_USER = "NonExistingUser";
  public static final String EMPTY_MESSAGE = "";

  @Mock
  Console console;

  @Mock
  MessageRepository messageRepository;

  private ViewUserTimeline viewUserTimeline;
  private PublishMessage publishMessage;

  @BeforeClass
  public void setUp() {
    initMocks(this);

    MessageService messageService = new MessageService(console, new InMemoryMessageRepository());

    viewUserTimeline = new ViewUserTimeline(console, messageService);
    publishMessage = new PublishMessage();
  }

  public void no_message_shown_for_non_existing_user() {

    viewUserTimeline.view(NON_EXISTING_USER);

    verify(console).printLine(EMPTY_MESSAGE);
  }

  public void given_that_Alice_published_one_message_When_I_view_her_messages_Then_I_see_it() {

    String messageText = "Hello world";
    publishMessage.publishMessage(ALICE, messageText);

    viewUserTimeline.view(ALICE);

    verify(console).printLine(messageText);
  }
}
