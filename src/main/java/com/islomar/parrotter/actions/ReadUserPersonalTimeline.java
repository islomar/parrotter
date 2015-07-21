package com.islomar.parrotter.actions;

import com.islomar.parrotter.model.message.MessageService;


public class ReadUserPersonalTimeline implements Command {

  private final MessageService messageService;
  private final String username;

  public ReadUserPersonalTimeline(final MessageService messageService, final String username) {

    this.messageService = messageService;
    this.username = username;
  }

  @Override
  public void execute() {
    messageService.printTimelineFor(username);
  }
}
