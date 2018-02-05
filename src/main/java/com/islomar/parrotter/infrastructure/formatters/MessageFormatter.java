package com.islomar.parrotter.infrastructure.formatters;

import com.islomar.parrotter.model.message.Message;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;


public class MessageFormatter {

  private static final int MILLISECONDS_IN_A_SECOND = 1000;

  private final Clock clock;

  public MessageFormatter(Clock clock) {

    this.clock = clock;
  }

  public String formatForTheWall(Message message) {
    return message.getUsername() + " - " + formatForViewUserTimeline(message);
  }

  public String formatForViewUserTimeline(Message message) {
    return message.getTextMessage() + " " + renderTimeElapsed(message.getPublicationInstant());
  }


  private String renderTimeElapsed(Instant publicationInstant) {

    Duration timeElapsed = Duration.between(publicationInstant, clock.instant());

    if (lessThanOneMinuteElapsed(timeElapsed)) {

      return generateMessage(timeElapsed.toMillis() / MILLISECONDS_IN_A_SECOND, "second");

    } else if (lessThanOneHourElapsed(timeElapsed)) {

      return generateMessage(timeElapsed.toMinutes(), "minute");

    } else if (lessThanOneDay(timeElapsed)) {

      return generateMessage(timeElapsed.toHours(), "hour");

    } else {
      return generateMessage(timeElapsed.toDays(), "day");
    }
  }

  private boolean lessThanOneDay(Duration timeElapsed) {
    return timeElapsed.toDays() == 0;
  }

  private boolean lessThanOneHourElapsed(Duration timeElapsed) {
    return timeElapsed.toHours() == 0;
  }

  private boolean lessThanOneMinuteElapsed(Duration timeElapsed) {
    return timeElapsed.toMinutes() == 0;
  }

  private String generateMessage(long numberOfUnits, String unitInSingular) {
    return "("
           + numberOfUnits
           + " "
           + (numberOfUnits == 1 ? unitInSingular : unitInSingular + "s")
           + " ago)";
  }

}
