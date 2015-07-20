package com.islomar.parrotter.commands;

import com.islomar.parrotter.actions.ReadUserTimeline;


public class ReadUserTimelineCommand implements Command {

  private final ReadUserTimeline readUserTimeline;
  private final String username;

  public ReadUserTimelineCommand(final ReadUserTimeline readUserTimeline, final String username) {

    this.readUserTimeline = readUserTimeline;
    this.username = username;
  }

  @Override
  public void execute() {
    readUserTimeline.execute(username);
  }
}
