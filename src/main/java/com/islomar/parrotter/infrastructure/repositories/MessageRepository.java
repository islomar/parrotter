package com.islomar.parrotter.infrastructure.repositories;

import java.util.List;


public interface MessageRepository {

      List<String> findAllMessagesForUser(String username);

}