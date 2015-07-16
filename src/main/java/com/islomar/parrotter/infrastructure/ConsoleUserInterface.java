package com.islomar.parrotter.infrastructure;

import com.islomar.parrotter.model.Message;

/**
 *
 */
public class ConsoleUserInterface implements MessageOutput {

  @Override
  public void printMessage(Message message) {
    System.out.println(message);
  }
}
