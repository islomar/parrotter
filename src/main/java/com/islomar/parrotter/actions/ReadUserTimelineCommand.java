package com.islomar.parrotter.actions;

import com.islomar.parrotter.services.MessageService;


public class ReadUserTimelineCommand implements Command {

  private final MessageService messageService;
  private final String username;

  public ReadUserTimelineCommand(final MessageService messageService, final String username) {

    this.messageService = messageService;
    this.username = username;
  }

  @Override
  public void execute() {
    messageService.printTimelineFor(username);
  }
}
