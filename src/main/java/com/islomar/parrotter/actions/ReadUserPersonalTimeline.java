package com.islomar.parrotter.actions;

import com.islomar.parrotter.model.message.MessageService;

import java.util.regex.Pattern;


public class ReadUserPersonalTimeline implements Command {

  private static final String ONLY_LETTERS_REGEXP = "^[a-zA-Z]+$";

  private final MessageService messageService;

  public ReadUserPersonalTimeline(final MessageService messageService) {

    this.messageService = messageService;
  }

  @Override
  public void execute(String inputCommandLine) {

    String username = inputCommandLine;

    messageService.printTimelineFor(username);
  }

  @Override
  public boolean canExecuteCommandline(String inputCommandLine) {

    return Pattern.matches(ONLY_LETTERS_REGEXP, inputCommandLine);
  }
}
