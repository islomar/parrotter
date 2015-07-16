package com.islomar.parrotter.model;

import java.time.Instant;


public class Message {

  private final String textMessage;
  private final Instant publicationInstant;

  public Message(final String textMessage, final Instant publicationInstant) {
    this.textMessage = textMessage;
    this.publicationInstant = publicationInstant;
  }
}
