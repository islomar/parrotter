package com.islomar.parrotter.actions;

import com.islomar.parrotter.model.ShowUserWallService;

import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

@Test
public class ShowUserWallShould {

  private static final String ALICE = "Alice";

  @Mock ShowUserWallService showUserWallService;


  @BeforeMethod
  public void setUpMethod() {
    initMocks(this);
  }

  public void show_a_user_wall() {
    ShowUserWall showUserWall = new ShowUserWall(showUserWallService, ALICE);

    showUserWall.execute();

    verify(showUserWallService).printUserWallFor(ALICE);
  }
}
