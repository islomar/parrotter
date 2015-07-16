package com.islomar.parrotter.model;

import java.time.Instant;


public class Message {

  private final String username;
  private final String textMessage;
  private final Instant publicationInstant;

  public Message(final String username, final String textMessage, final Instant publicationInstant) {
    this.username = username;
    this.textMessage = textMessage;
    this.publicationInstant = publicationInstant;
  }

  public String getUsername() {
    return username;
  }
}
