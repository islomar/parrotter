package com.islomar.parrotter.actions;

import com.islomar.parrotter.model.UserService;

import java.util.regex.Pattern;


public class ReadUserPersonalTimeline implements Command {

  private static final String ONLY_LETTERS_REGEXP = "^[a-zA-Z]+$";

  private final UserService userService;

  public ReadUserPersonalTimeline(final UserService userService) {

    this.userService = userService;
  }

  @Override
  public void execute(String inputCommandLine) {

    String username = inputCommandLine;

    userService.printTimelineFor(username);
  }

  @Override
  public boolean canExecuteCommandline(String inputCommandLine) {

    return Pattern.matches(ONLY_LETTERS_REGEXP, inputCommandLine);
  }
}
