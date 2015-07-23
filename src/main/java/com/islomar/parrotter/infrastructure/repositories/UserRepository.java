package com.islomar.parrotter.infrastructure.repositories;


import com.islomar.parrotter.model.Message;
import com.islomar.parrotter.model.User;

import java.util.List;

public interface UserRepository {

  public void saveUser(String username);

  public User findUserByUsername(String username);

  public List<Message> findAllMessagesForUser(String username);

  public void saveMessage(String username, String messageText);
}
