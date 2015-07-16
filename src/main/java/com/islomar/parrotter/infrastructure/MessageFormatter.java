package com.islomar.parrotter.infrastructure;

import com.islomar.parrotter.model.Message;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;


public class MessageFormatter {


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

    Duration timeElapsed = Duration.between(publicationInstant, Instant.now());

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
