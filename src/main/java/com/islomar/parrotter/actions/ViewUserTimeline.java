package com.islomar.parrotter.actions;

import com.islomar.parrotter.infrastructure.Console;
import com.islomar.parrotter.services.MessageService;

/**
 *
 */
public class ViewUserTimeline {

  private Console console;
  private MessageService messageService;

  public ViewUserTimeline(Console console, MessageService messageService) {
    this.console = console;
    this.messageService = messageService;
  }

  public void view(String username) {

    messageService.viewTimelineFor(username);
  }

}
