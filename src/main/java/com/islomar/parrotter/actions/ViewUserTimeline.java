package com.islomar.parrotter.actions;

import com.islomar.parrotter.infrastructure.MessageOutput;
import com.islomar.parrotter.services.MessageService;

/**
 *
 */
public class ViewUserTimeline {

  private MessageOutput messageOutput;
  private MessageService messageService;

  public ViewUserTimeline(MessageOutput messageOutput, MessageService messageService) {
    this.messageOutput = messageOutput;
    this.messageService = messageService;
  }

  public void view(String username) {

    messageService.viewTimelineFor(username);
  }

}
