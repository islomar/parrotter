package com.islomar.parrotter.feature;

import com.islomar.parrotter.controller.CommandLineProcessor;
import com.islomar.parrotter.infrastructure.Console;
import com.islomar.parrotter.infrastructure.formatters.MessageFormatter;
import com.islomar.parrotter.infrastructure.repositories.MessageRepository;
import com.islomar.parrotter.infrastructure.repositories.UserRepository;
import com.islomar.parrotter.model.message.InMemoryMessageRepository;
import com.islomar.parrotter.model.message.MessageService;
import com.islomar.parrotter.model.user.InMemoryUserRepository;
import com.islomar.parrotter.model.user.ShowUserWallService;
import com.islomar.parrotter.model.user.UserService;

import org.mockito.InOrder;
import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.inOrder;
import static org.mockito.MockitoAnnotations.initMocks;

@Test
public class ReadUserTimelineFeature {

  private static final String BOB = "Bob";
  private static final String MESSAGE_TEXT_1 = "Damn! We lost!";
  private static final String MESSAGE_TEXT_2 = "Good game though.";
  private static final int TWO = 2;
  private static final int FIVE = 5;
  private static final java.time.Instant FIVE_SECONDS_AGO = Instant.now().minus(FIVE, ChronoUnit.SECONDS);
  private static final java.time.Instant TWO_MINUTES_AGO = Instant.now().minus(TWO, ChronoUnit.MINUTES);

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

    showUserWallService = new ShowUserWallService(messageService, userService, console, messageFormatter);
  }

  public void a_user_publishes_two_messages_to_her_personal_timeline() {

    CommandLineProcessor commandLineProcessor = new CommandLineProcessor(userService, messageService, showUserWallService);
    given(clock.instant()).willReturn(TWO_MINUTES_AGO, FIVE_SECONDS_AGO, Instant.now());
    commandLineProcessor.execute(BOB + " -> " + MESSAGE_TEXT_1);
    commandLineProcessor.execute(BOB + " -> " + MESSAGE_TEXT_2);

    commandLineProcessor.execute(BOB);

    InOrder inOrder = inOrder(console);
    inOrder.verify(console).printMessage(MESSAGE_TEXT_1 + " (" + 2 + " minutes ago)");
    inOrder.verify(console).printMessage(MESSAGE_TEXT_2 + " (" + FIVE + " seconds ago)");
  }

}
