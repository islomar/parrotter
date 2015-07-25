package com.islomar.parrotter.actions;


import com.islomar.parrotter.model.UserService;

public class ShowUserWall implements Command {

  public static final String WALL = " wall";

  private final UserService userService;

  public ShowUserWall(UserService userService) {

    this.userService = userService;
  }


  @Override
  public void execute(String inputCommandLine) {

    String username = extractUsername(inputCommandLine);
    userService.printUserWallFor(username);
  }

  @Override
  public boolean canExecuteCommandline(String inputCommandLine) {
    return inputCommandLine.contains(WALL);
  }

  private String extractUsername(String inputCommandLine) {
    String[] inputArguments = inputCommandLine.split(WALL);
    return inputArguments[0].trim();
  }
}
