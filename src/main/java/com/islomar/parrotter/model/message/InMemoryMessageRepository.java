package com.islomar.parrotter.model.message;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import com.islomar.parrotter.infrastructure.repositories.MessageRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class InMemoryMessageRepository implements MessageRepository {

  private final Multimap<String, Message> messages;

  public InMemoryMessageRepository() {
    this.messages = ArrayListMultimap.create();
  }

  public List<Message> findAllMessagesForUser(final String username) {
    return Collections.unmodifiableList(new ArrayList<>(messages.get(username)));
  }

  public void saveMessage(Message message) {
    messages.put(message.getUsername(), message);
  }
}
