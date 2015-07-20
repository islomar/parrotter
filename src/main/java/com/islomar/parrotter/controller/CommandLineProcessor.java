package com.islomar.parrotter.controller;

import com.islomar.parrotter.services.FollowUserService;
import com.islomar.parrotter.services.PostMessageService;
import com.islomar.parrotter.services.ReadUserTimelineService;
import com.islomar.parrotter.services.ShowUserWallService;
import com.islomar.parrotter.actions.Command;
import com.islomar.parrotter.actions.CommandGenerator;
import com.islomar.parrotter.services.UserService;


public class CommandLineProcessor {

  private final PostMessageService postMessageService;
  private final ReadUserTimelineService readUserTimelineService;
  private final UserService userService;
  private FollowUserService followUserService;
  private ShowUserWallService showUserWallService;

  public CommandLineProcessor(UserService userService, PostMessageService postMessageService, ReadUserTimelineService readUserTimelineService, FollowUserService followUserService, ShowUserWallService showUserWallService) {

    this.userService = userService;
    this.postMessageService = postMessageService;
    this.readUserTimelineService = readUserTimelineService;
    this.followUserService = followUserService;
    this.showUserWallService = showUserWallService;
  }

  public void execute(String inputCommandLine) {

    CommandGenerator commandGenerator = new CommandGenerator(userService, postMessageService, readUserTimelineService, followUserService, showUserWallService);
    Command command = commandGenerator.createCommandFromInputLine(inputCommandLine);

    command.execute();
  }
}
