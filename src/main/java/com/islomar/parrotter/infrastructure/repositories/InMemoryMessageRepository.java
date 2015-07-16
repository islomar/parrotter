package com.islomar.parrotter.infrastructure.repositories;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import com.islomar.parrotter.model.Message;

import java.time.Clock;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 */
public class InMemoryMessageRepository implements MessageRepository {

  private final Clock clock;
  private Multimap<String, Message> messages;

  public InMemoryMessageRepository(final Clock clock) {
    this.clock = clock;
    this.messages = ArrayListMultimap.create();
  }

  @Override
  public List<Message> findAllMessagesForUser(String username) {
    return new ArrayList<>(messages.get(username));
  }

  @Override
  public void saveMessage(String username, String textMessage) {
    Message message = new Message(textMessage, clock.instant());
    messages.put(username, message);
  }
}