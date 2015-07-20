package com.islomar.parrotter.feature;

import com.islomar.parrotter.controller.CommandLineProcessor;
import com.islomar.parrotter.infrastructure.Console;
import com.islomar.parrotter.infrastructure.formatters.MessageFormatter;
import com.islomar.parrotter.infrastructure.repositories.MessageRepository;
import com.islomar.parrotter.infrastructure.repositories.UserRepository;
import com.islomar.parrotter.model.message.InMemoryMessageRepository;
import com.islomar.parrotter.model.user.InMemoryUserRepository;
import com.islomar.parrotter.services.MessageService;
import com.islomar.parrotter.services.ShowUserWallService;
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
public class FollowUserTimelineFeature {

  private static final String CHARLIE = "Charlie";
  private static final String BOB = "Bob";

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

  public void a_user_follows_another_user() {

    CommandLineProcessor commandLineProcessor = new CommandLineProcessor(userService, messageService, showUserWallService);
    commandLineProcessor.execute(CHARLIE + " follows " + BOB);

    verify(console, never()).printMessage(anyString());
  }


}
