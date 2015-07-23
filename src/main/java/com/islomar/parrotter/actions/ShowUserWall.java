package com.islomar.parrotter.actions;


import com.islomar.parrotter.model.UserService;

public class ShowUserWall implements Command {

  private final UserService userService;
  private final String username;

  public ShowUserWall(UserService userService, String username) {

    this.userService = userService;
    this.username = username;
  }

  @Override
  public void execute() {
    userService.printUserWallFor(username);
  }
}
