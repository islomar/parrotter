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

import static com.islomar.parrotter.controller.utils.CommandType.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.inOrder;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.testng.Assert.fail;


@Test
public class ShowUserWallFeature {

  private static final java.time.Instant NOW = Instant.now();
  private static final java.time.Instant POST_CHARLIE_MESSAGE_TIME = NOW.minus(15, ChronoUnit.SECONDS);
  private static final Instant POST_BOB_MESSAGE_1_TIME = NOW.minus(1, ChronoUnit.MINUTES);
  private static final Instant POST_BOB_MESSAGE_2_TIME = NOW.minus(2, ChronoUnit.MINUTES);
  private static final Instant POST_ALICE_MESSAGE_TIME = NOW.minus(5, ChronoUnit.MINUTES);
  private static final Instant VIEW_BOB_WALL_TIME = NOW;
  private static final String ALICE = "Alice";
  private static final String CHARLIE = "Charlie";
  private static final String BOB = "Bob";
  private static final String CHARLIE_MESSAGE_TEXT = "I'm in New York today! Anyone wants to have a coffee?";
  private static final String ALICE_MESSAGE_TEXT = "I love the weather today";
  private static final String BOB_MESSAGE_TEXT_1 = "Damn! We lost!";
  private static final String BOB_MESSAGE_TEXT_2 = "Good game though.";

  @Mock private ScannerProxy scannerProxy;
  @Mock private Console console;
  @Mock private Clock clock;

  @BeforeMethod
  public void setUpMethod() {
    initMocks(this);
  }

  public void when_charlie_follows_alice_then_his_wall_shows_both_his_personal_timeline_and_alice_timeline() {

    given(clock.instant()).willReturn(POST_CHARLIE_MESSAGE_TIME,
                                      POST_BOB_MESSAGE_1_TIME,
                                      POST_BOB_MESSAGE_2_TIME,
                                      POST_ALICE_MESSAGE_TIME,
                                      VIEW_BOB_WALL_TIME);

    given(scannerProxy.nextLine())
        .willReturn(CHARLIE + POST.symbol() + CHARLIE_MESSAGE_TEXT)
        .willReturn(BOB + POST.symbol() + BOB_MESSAGE_TEXT_1)
        .willReturn(BOB + POST.symbol() + BOB_MESSAGE_TEXT_2)
        .willReturn(ALICE + POST.symbol() + ALICE_MESSAGE_TEXT)
        .willReturn(CHARLIE + FOLLOWS.symbol() + ALICE)
        .willReturn(CHARLIE + FOLLOWS.symbol() + BOB)
        .willReturn(CHARLIE + WALL.symbol())
        .willThrow(InterruptedException.class);

    try {
      ParrotterApplicationLauncher parrotterApplicationLauncher = new ParrotterApplicationLauncher(scannerProxy, console, clock);
      parrotterApplicationLauncher.run();
      fail();
    } catch (Exception ex) {
      InOrder inOrder = inOrder(console);
      inOrder.verify(console).printMessage("Charlie - I'm in New York today! Anyone wants to have a coffee? (15 seconds ago)");
      inOrder.verify(console).printMessage("Bob - Damn! We lost! (1 minute ago)");
      inOrder.verify(console).printMessage("Bob - Good game though. (2 minutes ago)");
      inOrder.verify(console).printMessage("Alice - I love the weather today (5 minutes ago)");
    }
  }

}
