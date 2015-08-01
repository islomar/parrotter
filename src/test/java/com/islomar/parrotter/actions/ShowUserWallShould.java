package com.islomar.parrotter.actions;

import com.islomar.parrotter.model.user.ShowUserWallService;

import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.islomar.parrotter.actions.ShowUserWall.WALL;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

@Test
public class ShowUserWallShould {

  private static final String ALICE = "Alice";

  @Mock ShowUserWallService showUserWallService;
  private ShowUserWall showUserWall;


  @BeforeMethod
  public void setUpMethod() {
    initMocks(this);
    showUserWall = new ShowUserWall(showUserWallService);
  }

  public void show_a_user_wall() {

    showUserWall.execute(ALICE + WALL);

    verify(showUserWallService).printUserWallFor(ALICE);
  }

  public void be_able_to_execute_a_wall_command() {

    assertTrue(showUserWall.canExecuteCommandline(ALICE + WALL));
  }

  public void not_be_able_to_execute_a_follow_command() {

    assertFalse(showUserWall.canExecuteCommandline(ALICE + " follow Bob"));
  }

  public void not_be_able_to_execute_a_post_command() {

    assertFalse(showUserWall.canExecuteCommandline(ALICE + " -> Hello"));
  }

  public void not_be_able_to_execute_a_view_timeline_command() {

    assertFalse(showUserWall.canExecuteCommandline(ALICE));
  }
}
