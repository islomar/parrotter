package com.islomar.parrotter.model;

import java.time.Instant;


public class Message {

  private final String username;
  private final String textMessage;
  private final Instant publicationInstant;
  private final MessageFormatter messageFormatter;

  public Message(final String username, final String textMessage, final Instant publicationInstant) {

    this.username = username;
    this.textMessage = textMessage;
    this.publicationInstant = publicationInstant;

    this.messageFormatter = new MessageFormatter();
  }

  public String getUsername() {
    return username;
  }

  public String getTextMessage() {
    return textMessage;
  }

  public Instant getPublicationInstant() {
    return publicationInstant;
  }

  @Override
  public String toString() {
    return messageFormatter.formatForViewUserTimeline(this);
  }

  public String formatForViewUserTimeline() {
    return messageFormatter.formatForViewUserTimeline(this);
  }
}
