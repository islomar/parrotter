package com.islomar.parrotter.actions;

import com.islomar.parrotter.services.ReadUserTimelineService;


public class ReadUserTimelineCommand implements Command {

  private final ReadUserTimelineService readUserTimelineService;
  private final String username;

  public ReadUserTimelineCommand(final ReadUserTimelineService readUserTimelineService, final String username) {

    this.readUserTimelineService = readUserTimelineService;
    this.username = username;
  }

  @Override
  public void execute() {
    readUserTimelineService.execute(username);
  }
}
