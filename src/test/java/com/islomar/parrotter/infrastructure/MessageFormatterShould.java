package com.islomar.parrotter.infrastructure;

import com.islomar.parrotter.model.message.Message;
import com.islomar.parrotter.model.message.MessageFormatter;

import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;

@Test
public class MessageFormatterShould {

  private static final String MESSAGE = "Hello world";
  private static final String ALICE = "Alice";

  private static final java.time.Instant NOW = Instant.now();

  @Mock Clock clock;

  private MessageFormatter messageFormatter;

  @BeforeMethod
  public void setUpMethod() {

    initMocks(this);
    messageFormatter = new MessageFormatter(clock);

    given(clock.instant()).willReturn(NOW);
  }


  public void format_a_message_for_user_timeline_with_text_published_32_seconds_ago() {

    Message message = new Message(ALICE, MESSAGE, NOW.minus(32, ChronoUnit.SECONDS));

    String formattedMessage = messageFormatter.formatForViewUserTimeline(message);

    assertThat(formattedMessage, is(MESSAGE + " (32 seconds ago)"));
  }

  public void format_a_message_for_user_timeline_with_text_published_321_seconds_ago_to_show_5_minutes_ago() {

    Message message = new Message(ALICE, MESSAGE, NOW.minus(321, ChronoUnit.SECONDS));

    String formattedMessage = messageFormatter.formatForViewUserTimeline(message);

    assertThat(formattedMessage, is(MESSAGE + " (5 minutes ago)"));
  }

  public void format_a_message_for_user_timeline_with_text_published_23_hours_ago() {

    Message message = new Message(ALICE, MESSAGE, NOW.minus(23 * 60, ChronoUnit.MINUTES));

    String formattedMessage = messageFormatter.formatForViewUserTimeline(message);

    assertThat(formattedMessage, is(MESSAGE + " (23 hours ago)"));
  }

  public void format_a_message_for_user_timeline_with_text_published_7_hours_ago_to_show_3_days_ago() {

    Message message = new Message(ALICE, MESSAGE, NOW.minus(78, ChronoUnit.HOURS));

    String formattedMessage = messageFormatter.formatForViewUserTimeline(message);

    assertThat(formattedMessage, is(MESSAGE + " (3 days ago)"));
  }

  public void format_a_message_for_wall_published_32_seconds_ago() {

    Message message = new Message(ALICE, MESSAGE, NOW.minus(32, ChronoUnit.SECONDS));

    String formattedMessage = messageFormatter.formatForTheWall(message);

    assertThat(formattedMessage, is(ALICE + " - " + MESSAGE + " (32 seconds ago)"));
  }

  public void format_a_message_for_wall_with_text_published_321_seconds_ago_to_show_5_minutes_ago() {

    Message message = new Message(ALICE, MESSAGE, NOW.minus(321, ChronoUnit.SECONDS));

    String formattedMessage = messageFormatter.formatForTheWall(message);

    assertThat(formattedMessage, is(ALICE + " - " + MESSAGE + " (5 minutes ago)"));
  }

  public void format_a_message_for_wall_with_text_published_23_hours_ago() {

    Message message = new Message(ALICE, MESSAGE, NOW.minus(23 * 60, ChronoUnit.MINUTES));

    String formattedMessage = messageFormatter.formatForTheWall(message);

    assertThat(formattedMessage, is(ALICE + " - " + MESSAGE + " (23 hours ago)"));
  }

  public void format_a_message_for_wall_with_text_published_7_hours_ago_to_show_3_days_ago() {

    Message message = new Message(ALICE, MESSAGE, NOW.minus(78, ChronoUnit.HOURS));

    String formattedMessage = messageFormatter.formatForTheWall(message);

    assertThat(formattedMessage, is(ALICE + " - " + MESSAGE + " (3 days ago)"));
  }

}
