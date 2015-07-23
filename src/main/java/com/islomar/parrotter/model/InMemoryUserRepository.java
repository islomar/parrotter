package com.islomar.parrotter.model;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;

import com.islomar.parrotter.infrastructure.repositories.UserRepository;

import java.time.Clock;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class InMemoryUserRepository implements UserRepository {

  private Set<User> users;
  private final Clock clock;
  private Multimap<String, Message> messages;

  public InMemoryUserRepository(final Clock clock) {
    users = Sets.newHashSet();
    this.clock = clock;
    this.messages = ArrayListMultimap.create();
  }

  @Override
  public void saveUser(String username) {
    users.add(new User(username));
  }

  @Override
  public User findUserByUsername(String username) {
    return users.stream().filter(user -> user.getUsername().equalsIgnoreCase(username)).findFirst().orElse(new NullUser());
  }

  @Override
  public List<Message> findAllMessagesForUser(final String username) {
    return Collections.unmodifiableList(new ArrayList<>(messages.get(username)));
  }

  @Override
  public void saveMessage(final String username, final String messageText) {
    Message message = new Message(username, messageText, clock.instant());
    messages.put(message.getUsername(), message);
  }
}
