package com.islomar.parrotter.actions;


import com.islomar.parrotter.model.user.ShowUserWallService;

public class ShowUserWall implements Command {

  private final ShowUserWallService showUserWallService;
  private final String username;

  public ShowUserWall(ShowUserWallService showUserWallService, String username) {

    this.showUserWallService = showUserWallService;
    this.username = username;
  }

  @Override
  public void execute() {
    showUserWallService.printUserWallFor(username);
  }
}
