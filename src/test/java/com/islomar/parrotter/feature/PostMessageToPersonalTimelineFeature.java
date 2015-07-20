package com.islomar.parrotter.feature;

import com.islomar.parrotter.infrastructure.formatters.MessageFormatter;
import com.islomar.parrotter.services.PostMessageService;
import com.islomar.parrotter.services.ReadUserTimelineService;
import com.islomar.parrotter.services.ShowUserWallService;
import com.islomar.parrotter.controller.CommandLineProcessor;
import com.islomar.parrotter.infrastructure.Console;
import com.islomar.parrotter.infrastructure.repositories.MessageRepository;
import com.islomar.parrotter.infrastructure.repositories.UserRepository;
import com.islomar.parrotter.model.message.InMemoryMessageRepository;
import com.islomar.parrotter.model.user.InMemoryUserRepository;
import com.islomar.parrotter.services.UserService;

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
  private static final String MESSAGE_TEXT = "I love the weather today";

  @Mock private Console console;
  @Mock private Clock clock;

  private PostMessageService postMessageService;
  private ReadUserTimelineService readUserTimelineService;
  private ShowUserWallService showUserWallService;
  private UserService userService;


  @BeforeMethod
  public void setUpMethod() {
    initMocks(this);

    MessageRepository messageRepository = new InMemoryMessageRepository(clock);
    postMessageService = new PostMessageService(messageRepository);
    MessageFormatter messageFormatter = new MessageFormatter(clock);
    readUserTimelineService = new ReadUserTimelineService(messageRepository, console, messageFormatter);
    UserRepository userRepository = new InMemoryUserRepository();
    userService = new UserService(userRepository);
    showUserWallService = new ShowUserWallService(messageRepository, userRepository, console, messageFormatter);
  }

  public void a_user_publishes_a_message_to_her_personal_timeline() {

    CommandLineProcessor commandLineProcessor = new CommandLineProcessor(userService, postMessageService, readUserTimelineService, showUserWallService);
    commandLineProcessor.execute(ALICE + " -> " + MESSAGE_TEXT);

    verify(console, never()).printMessage(anyString());
  }

}
