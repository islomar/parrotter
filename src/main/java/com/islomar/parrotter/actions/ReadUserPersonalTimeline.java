package com.islomar.parrotter.actions;

import com.islomar.parrotter.model.UserService;


public class ReadUserPersonalTimeline implements Command {

  private final UserService userService;
  private final String username;

  public ReadUserPersonalTimeline(final UserService messageService, final String username) {

    this.userService = messageService;
    this.username = username;
  }

  @Override
  public void execute() {
    userService.printTimelineFor(username);
  }
}
