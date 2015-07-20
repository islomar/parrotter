package com.islomar.parrotter.controller;

import com.islomar.parrotter.actions.PostMessage;
import com.islomar.parrotter.actions.ReadUserTimeline;
import com.islomar.parrotter.commands.Command;
import com.islomar.parrotter.commands.CommandGenerator;

import java.time.Clock;


public class CommandLineProcessor {

  private final Clock clock;
  private final PostMessage postMessage;
  private final ReadUserTimeline readUserTimeline;

  public CommandLineProcessor(Clock clock, PostMessage postMessage, ReadUserTimeline readUserTimeline) {
    this.clock = clock;

    this.postMessage = postMessage;
    this.readUserTimeline = readUserTimeline;
  }

  public void execute(String inputCommandLine) {

    CommandGenerator commandGenerator = new CommandGenerator(clock, postMessage, readUserTimeline);
    Command command = commandGenerator.createCommandFromInputLine(inputCommandLine);

    command.execute();
  }
}
