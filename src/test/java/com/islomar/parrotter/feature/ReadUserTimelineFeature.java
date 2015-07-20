package com.islomar.parrotter.feature;

import com.islomar.parrotter.infrastructure.formatters.MessageFormatter;
import com.islomar.parrotter.services.MessageService;
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
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

@Test
public class ReadUserTimelineFeature {

  private static final String ALICE = "Alice";
  private static final String MESSAGE_TEXT = "I love the weather today";
  private static final int FIVE = 5;
  private static final java.time.Instant FIVE_SECONDS_AGO = Instant.now().minus(FIVE, ChronoUnit.SECONDS);

  @Mock Console console;
  @Mock Clock clock;

  private MessageService messageService;
  private ShowUserWallService showUserWallService;
  private UserService userService;


  @BeforeMethod
  public void setUpMethod() {
    initMocks(this);

    MessageRepository messageRepository = new InMemoryMessageRepository(clock);
    MessageFormatter messageFormatter = new MessageFormatter(clock);
    messageService = new MessageService(messageRepository, console, messageFormatter);

    UserRepository userRepository = new InMemoryUserRepository();
    userService = new UserService(userRepository);

    showUserWallService = new ShowUserWallService(messageRepository, userRepository, console, messageFormatter);
  }

  public void a_user_publishes_a_message_to_her_personal_timeline() {

    CommandLineProcessor commandLineProcessor = new CommandLineProcessor(userService, messageService, showUserWallService);
    given(clock.instant()).willReturn(FIVE_SECONDS_AGO, Instant.now());
    commandLineProcessor.execute(ALICE + " -> " + MESSAGE_TEXT);

    commandLineProcessor.execute(ALICE);

    verify(console).printMessage(MESSAGE_TEXT + " (" + FIVE + " seconds ago)");
  }

}
