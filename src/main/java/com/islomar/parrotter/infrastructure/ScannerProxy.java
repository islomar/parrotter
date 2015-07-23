package com.islomar.parrotter.infrastructure;

import java.util.Scanner;

public class ScannerProxy {

  private final Scanner scanner;

  public ScannerProxy(Scanner scanner) {

    this.scanner = scanner;
  }

  public String nextLine() {
    return scanner.nextLine();
  }

}
