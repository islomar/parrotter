package com.islomar.parrotter.infrastructure;

import com.islomar.parrotter.model.Message;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;


public class MessageFormatter {


  /**
   * Formats a Message.
   *
   * @param message
   * @return message with time elapsed, e.g. "I love the weather today (5 minutes ago)"
   */
  public String formatWithTextAndTimeElapsed(Message message) {
    return message.getTextMessage() + " " + renderTimeElapsed(message.getPublicationInstant());
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
