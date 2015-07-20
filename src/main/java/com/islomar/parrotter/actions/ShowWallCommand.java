package com.islomar.parrotter.actions;


import com.islomar.parrotter.services.ShowUserWallService;

public class ShowWallCommand implements Command {

  private final ShowUserWallService showUserWallService;
  private final String username;

  public ShowWallCommand(ShowUserWallService showUserWallService, String username) {

    this.showUserWallService = showUserWallService;
    this.username = username;
  }


  @Override
  public void execute() {
    showUserWallService.execute(username);
  }
}
