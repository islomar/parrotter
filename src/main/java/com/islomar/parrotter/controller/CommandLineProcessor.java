package com.islomar.parrotter.controller;

import com.islomar.parrotter.actions.FollowUser;
import com.islomar.parrotter.actions.PostMessage;
import com.islomar.parrotter.actions.ReadUserTimeline;
import com.islomar.parrotter.commands.Command;
import com.islomar.parrotter.commands.CommandGenerator;


public class CommandLineProcessor {

  private final PostMessage postMessage;
  private final ReadUserTimeline readUserTimeline;
  private FollowUser followUser;

  public CommandLineProcessor(PostMessage postMessage, ReadUserTimeline readUserTimeline, FollowUser followUser) {

    this.postMessage = postMessage;
    this.readUserTimeline = readUserTimeline;
    this.followUser = followUser;
  }

  public void execute(String inputCommandLine) {

    CommandGenerator commandGenerator = new CommandGenerator(postMessage, readUserTimeline, followUser);
    Command command = commandGenerator.createCommandFromInputLine(inputCommandLine);

    command.execute();
  }
}
