package com.islomar.parrotter.app;

import com.google.common.base.Charsets;

import com.islomar.parrotter.infrastructure.Console;
import com.islomar.parrotter.infrastructure.ScannerProxy;

import java.time.Clock;
import java.util.Scanner;

public class ParrotterApplicationLauncher {

  public static void main(String[] args) {

    ScannerProxy scannerProxy = generateScanner();
    Console console = new Console();
    Clock clock = Clock.systemUTC();

    new ParrotterApplication(scannerProxy, console, clock).run();
  }

  private static ScannerProxy generateScanner() {
    Scanner scanner = new Scanner(System.in, Charsets.UTF_8.name());
    return new ScannerProxy(scanner);
  }

}
