package com.islomar.parrotter.services;

import com.islomar.parrotter.infrastructure.repositories.MessageRepository;

import java.util.List;

/**
 *
 */
public class MessageService {

  private MessageRepository messageRepository;


  public MessageService(MessageRepository messageRepository) {
    this.messageRepository = messageRepository;
  }

  public List<String> findAllMessagesFor(String username) {
    return messageRepository.findAllMessagesForUser(username);
  } }
