package com.islomar.parrotter.feature;

import com.islomar.parrotter.actions.FollowUser;
import com.islomar.parrotter.actions.PostMessage;
import com.islomar.parrotter.actions.ReadUserTimeline;
import com.islomar.parrotter.controller.CommandLineProcessor;
import com.islomar.parrotter.infrastructure.Console;
import com.islomar.parrotter.infrastructure.repositories.MessageRepository;
import com.islomar.parrotter.infrastructure.repositories.UserRepository;
import com.islomar.parrotter.model.message.InMemoryMessageRepository;
import com.islomar.parrotter.model.user.InMemoryUserRepository;

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

  @Mock private Console console;
  @Mock private Clock clock;

  private ReadUserTimeline readUserTimeline;
  private PostMessage postMessage;
  private FollowUser followUser;


  @BeforeMethod
  public void setUpMethod() {
    initMocks(this);

    MessageRepository messageRepository = new InMemoryMessageRepository(clock);
    postMessage = new PostMessage(messageRepository);
    readUserTimeline = new ReadUserTimeline(messageRepository, console);
    UserRepository userRepository = new InMemoryUserRepository();
    followUser = new FollowUser(userRepository);
  }

  public void a_user_follows_another_user() {

    CommandLineProcessor commandLineProcessor = new CommandLineProcessor(postMessage, readUserTimeline, followUser);
    commandLineProcessor.execute(CHARLIE + " follows " + BOB);

    verify(console, never()).printMessage(anyString());
  }


}
