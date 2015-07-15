package com.islomar.parrotter.infrastructure.repositories;

import java.util.List;

/**
 *
 */
public class InMemoryMessageRepository implements MessageRepository {

  @Override
  public List<String> findAllMessagesForUser(String username) {
    return null;
  }

  @Override
  public void saveMessage(String username, String textMessage) {

  }
}
