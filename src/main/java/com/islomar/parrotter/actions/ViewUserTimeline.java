package com.islomar.parrotter.actions;

import com.islomar.parrotter.infrastructure.Console;

/**
 *
 */
public class ViewUserTimeline {

  private Console console;

  public ViewUserTimeline(Console console) {
    this.console = console;
  }

  public void view(String nonExistingUser) {
    console.printLine("");
  }
}
