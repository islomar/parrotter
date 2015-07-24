package com.islomar.parrotter.feature;

import com.islomar.parrotter.app.ParrotterApplicationLauncher;
import com.islomar.parrotter.infrastructure.Console;
import com.islomar.parrotter.infrastructure.ScannerProxy;

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
import static org.testng.Assert.fail;

@Test
public class ReadUserTimelineFeature {

  private static final java.time.Instant NOW = Instant.now();
  private static final Instant VIEW_ALICE_TIMELINE_TIME = NOW;
  private static final java.time.Instant SAVED_ALICE_MESSAGE_1_TIME = VIEW_ALICE_TIMELINE_TIME.minus(45, ChronoUnit.SECONDS);
  private static final java.time.Instant SAVED_ALICE_MESSAGE_2_TIME = VIEW_ALICE_TIMELINE_TIME.minus(15, ChronoUnit.SECONDS);

  private static final Instant VIEW_BOB_TIMELINE_TIME = NOW;
  private static final Instant SAVED_BOB_MESSAGE_TIME = VIEW_BOB_TIMELINE_TIME.minus(2, ChronoUnit.MINUTES);

  @Mock private ScannerProxy scannerProxy;
  @Mock private Console console;
  @Mock private Clock clock;

  @BeforeMethod
  public void setUpMethod() {
    initMocks(this);
  }

  public void users_see_their_published_messages_into_their_personal_timeline() {

    given(clock.instant()).willReturn(SAVED_ALICE_MESSAGE_1_TIME,
                                      SAVED_ALICE_MESSAGE_2_TIME,
                                      VIEW_ALICE_TIMELINE_TIME,
                                      VIEW_ALICE_TIMELINE_TIME,
                                      SAVED_BOB_MESSAGE_TIME,
                                      VIEW_BOB_TIMELINE_TIME);

    given(scannerProxy.nextLine())
        .willReturn("Alice")
        .willReturn("Alice -> hello")
        .willReturn("Alice -> hello again")
        .willReturn("Alice")
        .willReturn("Bob -> bye bye")
        .willReturn("Bob")
        .willThrow(InterruptedException.class);

    try {
      ParrotterApplicationLauncher parrotterApplicationLauncher = new ParrotterApplicationLauncher(scannerProxy, console, clock);
      parrotterApplicationLauncher.run();
      fail();
    } catch (Exception ex) {
      InOrder inOrder = inOrder(console);
      inOrder.verify(console).printMessage("hello again (15 seconds ago)");
      inOrder.verify(console).printMessage("hello (45 seconds ago)");
      inOrder.verify(console).printMessage("bye bye (2 minutes ago)");
    }
  }


}
