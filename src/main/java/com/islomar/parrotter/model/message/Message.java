package com.islomar.parrotter.model.message;

import java.time.Instant;


public class Message implements Comparable {

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

  public String getTextMessage() {
    return textMessage;
  }

  public Instant getPublicationInstant() {
    return publicationInstant;
  }


  @Override
  public int compareTo(Object o) {
    Message anotherMessage = (Message) o;
    return anotherMessage.getPublicationInstant().compareTo(publicationInstant);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Message message = (Message) o;

    if (publicationInstant != null ? !publicationInstant.equals(message.publicationInstant) : message.publicationInstant != null) {
      return false;
    }
    if (textMessage != null ? !textMessage.equals(message.textMessage) : message.textMessage != null) {
      return false;
    }
    if (username != null ? !username.equals(message.username) : message.username != null) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int result = username != null ? username.hashCode() : 0;
    result = 31 * result + (textMessage != null ? textMessage.hashCode() : 0);
    result = 31 * result + (publicationInstant != null ? publicationInstant.hashCode() : 0);
    return result;
  }
}
