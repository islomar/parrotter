package com.islomar.parrotter.actions;

import com.islomar.parrotter.model.User;
import com.islomar.parrotter.model.UserService;

import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.islomar.parrotter.actions.FollowUser.FOLLOWS;
import static com.islomar.parrotter.actions.PostMessage.POST;
import static com.islomar.parrotter.actions.ShowUserWall.WALL;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

@Test
public class FollowUserShould {

  private static final String ALICE = "Alice";
  private static final String BOB = "Bob";

  @Mock UserService userService;
  private FollowUser postMessage;

  @BeforeMethod
  public void setUpMethod() {
    initMocks(this);

    postMessage = new FollowUser(userService);

    given(userService.findUserByUsername(ALICE)).willReturn(new User(ALICE));
  }

  public void follow_another_user() {

    postMessage.execute(ALICE + FOLLOWS + BOB);

    verify(userService).findUserByUsername(ALICE);
  }

  public void be_able_to_execute_a_follow_command() {

    assertTrue(postMessage.canExecuteCommandline(ALICE + FOLLOWS + BOB));
  }

  public void be_able_to_execute_a_wall_command() {

    assertFalse(postMessage.canExecuteCommandline(ALICE + WALL));
  }

  public void be_able_to_execute_a_post_command() {

    assertFalse(postMessage.canExecuteCommandline(ALICE + POST + "Hello"));
  }

  public void be_able_to_execute_a_view_timeline_command() {

    assertFalse(postMessage.canExecuteCommandline(ALICE));
  }
}
