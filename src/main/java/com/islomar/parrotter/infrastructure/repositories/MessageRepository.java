package com.islomar.parrotter.infrastructure.repositories;

import com.islomar.parrotter.model.message.Message;

import java.util.List;


public interface MessageRepository {

  List<Message> findAllMessagesForUser(String username);

  void saveMessage(Message message);

}
