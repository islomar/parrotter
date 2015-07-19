package com.islomar.parrotter.model;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import com.islomar.parrotter.infrastructure.repositories.MessageRepository;
import com.islomar.parrotter.model.Message;

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

  @Override
  public List<Message> findAllMessagesForUser(String username) {
    return Collections.unmodifiableList(new ArrayList<>(messages.get(username)));
  }

  @Override
  public void saveMessage(Message message) {
    messages.put(message.getUsername(), message);
  }
}