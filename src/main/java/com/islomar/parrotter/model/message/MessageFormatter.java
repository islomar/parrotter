package com.islomar.parrotter.model.message;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;


public class MessageFormatter {


  private Clock clock;

  public MessageFormatter(Clock clock) {

    this.clock = clock;
  }

  /**
   * Formats a Message to be shown in user timeline.
   *
   * @param message
   * @return message with time elapsed, e.g. "I love the weather today (5 minutes ago)"
   */
  public String formatForViewUserTimeline(Message message) {
    return message.getTextMessage() + " " + renderTimeElapsed(message.getPublicationInstant());
  }

  /**
   * Formats a Message to be shown in a user wall.
   *
   * @param message
   * @return message with username and time elapsed, e.g. "Alice - I love the weather today (5 minutes ago)"
   */
  public String formatForTheWall(Message message) {
    return message.getUsername() + " - " + formatForViewUserTimeline(message);
  }

  private String renderTimeElapsed(Instant publicationInstant) {

    Duration timeElapsed = Duration.between(publicationInstant, clock.instant());

    if (timeElapsed.toMinutes() == 0) {
      return "(" + timeElapsed.toMillis() / 1000 + " seconds ago)";
    } else if (timeElapsed.toHours() == 0) {
      return "(" + timeElapsed.toMinutes() + " minutes ago)";
    } else if (timeElapsed.toDays() == 0) {
      return "(" + timeElapsed.toHours() + " hours ago)";
    } else {
      return "(" + timeElapsed.toDays() + " days ago)";
    }
  }
}
