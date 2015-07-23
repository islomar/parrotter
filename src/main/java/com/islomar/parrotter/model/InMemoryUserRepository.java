package com.islomar.parrotter.model;

import com.google.common.collect.Sets;

import com.islomar.parrotter.infrastructure.repositories.UserRepository;

import java.time.Clock;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class InMemoryUserRepository implements UserRepository {

  private Set<User> users;
  private final Clock clock;

  public InMemoryUserRepository(final Clock clock) {
    users = Sets.newHashSet();
    this.clock = clock;
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
    User user = users.stream().filter(u -> u.getUsername().equals(username)).findFirst().orElse(new NullUser());
    return Collections.unmodifiableList(new ArrayList<>(user.getPersonalMessages().stream().sorted().collect(Collectors.toList())));
  }

  @Override
  public void saveMessage(final String username, final String messageText) {
    User user = users.stream().filter(u -> u.getUsername().equals(username)).findFirst().get();
    Message message = new Message(username, messageText, clock.instant());
    user.getPersonalMessages().add(message);
  }
}
