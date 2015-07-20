package com.islomar.parrotter.model.message;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import com.islomar.parrotter.infrastructure.repositories.MessageRepository;

import java.time.Clock;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class InMemoryMessageRepository implements MessageRepository {

  private final Clock clock;
  private Multimap<String, Message> messages;

  public InMemoryMessageRepository(final Clock clock) {
    this.clock = clock;
    this.messages = ArrayListMultimap.create();
  }

  public List<Message> findAllMessagesForUser(final String username) {
    return Collections.unmodifiableList(new ArrayList<>(messages.get(username)));
  }

  public void saveMessage(final String username, final String messageText) {
    Message message = new Message(username, messageText, clock.instant());
    messages.put(message.getUsername(), message);
  }
}
