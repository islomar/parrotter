package com.islomar.parrotter.infrastructure;

import com.islomar.parrotter.model.MessageOutput;

/**
 *
 */
public class ConsoleUserInterface implements MessageOutput {

  @Override
  public void printMessage(String textMessage) {
    System.out.println(textMessage);
  }
}
