package com.islomar.parrotter.feature;

import com.islomar.parrotter.actions.Command;
import com.islomar.parrotter.app.ParrotterApplication;
import com.islomar.parrotter.infrastructure.Console;
import com.islomar.parrotter.infrastructure.ScannerProxy;
import com.sun.xml.internal.rngom.parse.host.Base;

import org.mockito.InOrder;
import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Clock;
import java.time.Instant;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.never;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.testng.Assert.fail;


@Test
public class PostMessageToPersonalTimelineFeature extends BaseFeature {

  private static final java.time.Instant POST_ALICE_MESSAGE_TIME = Instant.now();

  @Mock private ScannerProxy scannerProxy;
  @Mock private Console console;
  @Mock private Clock clock;

  @BeforeMethod
  public void setUpMethod() {
    initMocks(this);
  }

  public void users_post_message_to_their_personal_timeline() {

    given(clock.instant()).willReturn(POST_ALICE_MESSAGE_TIME);

    given(scannerProxy.nextLine())
        .willReturn("Alice -> hello")
        .willThrow(InterruptedException.class);
    List<Command> commandList = generateCommands(clock, console);

    try {
      ParrotterApplication parrotterApplication = new ParrotterApplication(commandList, scannerProxy);
      parrotterApplication.run();
      fail();
    } catch (Exception ex) {
      InOrder inOrder = inOrder(console);
      inOrder.verify(console, never()).printMessage(anyString());
    }
  }

}
