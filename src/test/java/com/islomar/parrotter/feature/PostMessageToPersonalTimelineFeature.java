package com.islomar.parrotter.feature;

import com.islomar.parrotter.actions.utils.CommandRunner;
import com.islomar.parrotter.app.ParrotterApplication;
import com.islomar.parrotter.infrastructure.Console;

import org.mockito.InOrder;
import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Clock;
import java.time.Instant;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.never;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.testng.Assert.fail;


@Test
public class PostMessageToPersonalTimelineFeature extends BaseFeature {

  private static final java.time.Instant POST_ALICE_MESSAGE_TIME = Instant.now();

  @Mock private Console console;
  @Mock private Clock clock;

  @BeforeMethod
  public void setUpMethod() {
    initMocks(this);
  }

  public void users_post_message_to_their_personal_timeline() {

    given(clock.instant()).willReturn(POST_ALICE_MESSAGE_TIME);

    given(console.nextLine())
        .willReturn("Alice -> hello")
        .willThrow(InterruptedException.class);
    CommandRunner commandRunner = generateCommandSelector(clock, console);

    try {
      ParrotterApplication parrotterApplication = new ParrotterApplication(commandRunner, console);
      parrotterApplication.run();
      fail();
    } catch (Exception ex) {
      InOrder inOrder = inOrder(console);
      inOrder.verify(console, never()).printMessage(anyString());
    }
  }

}
