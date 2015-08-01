package com.islomar.parrotter.infrastructure;


import java.util.Scanner;

/**
 * Class representing the View of the application with both read and write responsibilities.
 */
public class Console {

  private final Scanner scanner;

  public Console(Scanner scanner) {

    this.scanner = scanner;
  }

  public void printMessage(final String text) {
    System.out.println(text);
  }

  public String nextLine() {
    return scanner.nextLine();
  }
}
