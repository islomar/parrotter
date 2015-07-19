package com.islomar.parrotter.services;

import com.islomar.parrotter.model.MessageOutput;
import com.islomar.parrotter.infrastructure.repositories.MessageRepository;
import com.islomar.parrotter.model.Message;

import java.util.List;


public class MessageService {

  private final MessageOutput messageOutput;
  private final MessageRepository messageRepository;


  public MessageService(MessageOutput messageOutput, MessageRepository messageRepository) {
    this.messageOutput = messageOutput;
    this.messageRepository = messageRepository;
  }

  public void viewTimelineFor(String username) {
    List<Message> userMessages = messageRepository.findAllMessagesForUser(username);
    //userMessages.forEach(messageOutput::printMessage);
  }

  public void publishMessage(Message message) {
    messageRepository.saveMessage(message);
    messageOutput.printMessage(message.toString());
  }

}
