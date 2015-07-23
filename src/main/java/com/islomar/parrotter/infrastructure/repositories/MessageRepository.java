package com.islomar.parrotter.infrastructure.repositories;

import com.islomar.parrotter.model.Message;

import java.util.List;


public interface MessageRepository {

  public List<Message> findAllMessagesForUser(String username);

  public void saveMessage(String username, String messageText);

}
