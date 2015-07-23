package com.islomar.parrotter.app;

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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.testng.Assert.fail;

@Test
public class ParrotterApplicationLauncherShould {

  private static final java.time.Instant NOW = Instant.now();
  private static final java.time.Instant SAVED_ALICE_MESSAGE_TIME = NOW;
  private static final Instant VIEW_BOB_MESSAGE_IN_WALL_TIME = SAVED_ALICE_MESSAGE_TIME.plus(7, ChronoUnit.MINUTES);
  private static final Instant VIEW_ALICE_WALL_TIME = SAVED_ALICE_MESSAGE_TIME.plus(30, ChronoUnit.SECONDS);
  private static final Instant VIEW_ALICE_TIMELINE_TIME = SAVED_ALICE_MESSAGE_TIME.plus(15, ChronoUnit.SECONDS);
  private static final Instant SAVED_BOB_MESSAGE_TIME = NOW.plus(2, ChronoUnit.MINUTES);
  private static final Instant VIEW_ALICE_MESSAGE_IN_WALL_TIME = SAVED_BOB_MESSAGE_TIME.plus(5, ChronoUnit.MINUTES);
  private static final Instant VIEW_BOB_TIMELINE_TIME = SAVED_BOB_MESSAGE_TIME.plus(2, ChronoUnit.MINUTES);


  @Mock private ScannerProxy scannerProxy;
  @Mock private Console console;
  @Mock private Clock clock;

  @BeforeMethod
  public void setUpMethod() {
    initMocks(this);
  }

  public void show_a_user_personal_timeline() {

    given(clock.instant()).willReturn(SAVED_ALICE_MESSAGE_TIME,
                                      VIEW_ALICE_TIMELINE_TIME,
                                      SAVED_BOB_MESSAGE_TIME,
                                      VIEW_BOB_TIMELINE_TIME);

    given(scannerProxy.nextLine())
        .willReturn("Alice -> hello")
        .willReturn("Alice")
        .willReturn("Bob -> bye bye")
        .willReturn("Bob")
        .willThrow(InterruptedException.class);

    try {
      ParrotterApplicationLauncher parrotterApplicationLauncher = new ParrotterApplicationLauncher(scannerProxy, console, clock);
      parrotterApplicationLauncher.run();
      fail();
    } catch (Exception ex) {
      verifyWelcomeMessage();
      InOrder inOrder = inOrder(console);
      inOrder.verify(console).printMessage("hello (15 seconds ago)");
      inOrder.verify(console).printMessage("bye bye (2 minutes ago)");
    }
  }

  public void show_a_user_wall() {

    given(clock.instant()).willReturn(SAVED_ALICE_MESSAGE_TIME,
                                      VIEW_ALICE_WALL_TIME,
                                      SAVED_BOB_MESSAGE_TIME,
                                      VIEW_ALICE_MESSAGE_IN_WALL_TIME,
                                      VIEW_BOB_MESSAGE_IN_WALL_TIME);

    given(scannerProxy.nextLine())
        .willReturn("Alice -> hello")
        .willReturn("Alice wall")
        .willReturn("Bob -> bye bye")
        .willReturn("Alice follows Bob")
        .willReturn("Alice wall")
        .willThrow(InterruptedException.class);

    try {
      ParrotterApplicationLauncher parrotterApplicationLauncher = new ParrotterApplicationLauncher(scannerProxy, console, clock);
      parrotterApplicationLauncher.run();
      fail();
    } catch (Exception ex) {
      verifyWelcomeMessage();
      InOrder inOrder = inOrder(console);
      inOrder.verify(console).printMessage("Alice - hello (30 seconds ago)");
      inOrder.verify(console).printMessage("Bob - bye bye (5 minutes ago)");
      inOrder.verify(console).printMessage("Alice - hello (7 minutes ago)");
    }
  }

  private void verifyWelcomeMessage() {
    verify(console).printMessage("Welcome to Parroter!");
    verify(console).printMessage("You can execute any of these commands:\n");
    verify(console).printMessage("posting:\t <user name> -> <message>");
    verify(console).printMessage("reading:\t <user name>");
    verify(console).printMessage("following:\t <user name> follows <another user>");
    verify(console).printMessage("wall:\t\t <user name> wall");
    verify(console).printMessage("So now, just start parrotting!!");
    verify(console, times(2)).printMessage("\n");
  }

}
