package com.islomar.parrotter.model.message;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;


public class MessageFormatter {

  private static final int MILLISECONDS_IN_A_SECOND = 1000;

  private Clock clock;

  public MessageFormatter(Clock clock) {

    this.clock = clock;
  }

  /**
   * Formats a Message to be shown in user timeline.
   *
   * @return message with time elapsed, e.g. "I love the weather today (5 minutes ago)"
   */
  public String formatForViewUserTimeline(Message message) {
    return message.getTextMessage() + " " + renderTimeElapsed(message.getPublicationInstant());
  }

  /**
   * Formats a Message to be shown in a user wall.
   *
   * @return message with username and time elapsed, e.g. "Alice - I love the weather today (5 minutes ago)"
   */
  public String formatForTheWall(Message message) {
    return message.getUsername() + " - " + formatForViewUserTimeline(message);
  }

  private String renderTimeElapsed(Instant publicationInstant) {

    Duration timeElapsed = Duration.between(publicationInstant, clock.instant());

    if (lessThanOneMinuteElapsed(timeElapsed)) {

      return generateMessage(timeElapsed.toMillis()/MILLISECONDS_IN_A_SECOND, "second");

    } else if (timeElapsed.toHours() == 0) {

      return generateMessage(timeElapsed.toMinutes(), "minute");

    } else if (timeElapsed.toDays() == 0) {

      return generateMessage(timeElapsed.toHours(), "hour");

    } else {
      return generateMessage(timeElapsed.toDays(), "day");
    }
  }

  private boolean lessThanOneMinuteElapsed(Duration timeElapsed) {
    return timeElapsed.toMinutes() == 0;
  }

  private String generateMessage(long numberOfUnits, String unitInSingular) {
    return "(" +
           numberOfUnits +
           " " +
           (numberOfUnits == 1 ? unitInSingular : unitInSingular + "s") +
           " ago)";
  }

}
