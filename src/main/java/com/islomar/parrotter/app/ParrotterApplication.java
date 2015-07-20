package com.islomar.parrotter.app;

import com.islomar.parrotter.infrastructure.Console;


public class ParrotterApplication {

  public static void main(String[] args) {
    Console console = new Console();
    ParrotterApplicationLauncher parrotterApplicationLauncher = new ParrotterApplicationLauncher(console);

    parrotterApplicationLauncher.run();
  }

}
