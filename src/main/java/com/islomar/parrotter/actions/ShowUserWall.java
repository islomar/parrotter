package com.islomar.parrotter.actions;


import com.islomar.parrotter.model.user.ShowUserWallService;

public class ShowUserWall implements Command {

  public static final String WALL = " wall";

  private final ShowUserWallService showUserWallService;

  public ShowUserWall(ShowUserWallService showUserWallService) {

    this.showUserWallService = showUserWallService;
  }

  @Override
  public void execute(String inputCommandLine) {
    if (canNotExecute(inputCommandLine)) {
      return;
    }

    String username = extractUsername(inputCommandLine);
    showUserWallService.printUserWallFor(username);
  }

  private String extractUsername(String inputCommandLine) {
    String[] inputArguments = inputCommandLine.split(WALL);
    return inputArguments[0].trim();
  }

  public boolean canNotExecute(String inputCommandLine) {
    return !inputCommandLine.contains(WALL);
  }
}
