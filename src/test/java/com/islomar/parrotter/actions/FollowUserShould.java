package com.islomar.parrotter.actions;

import com.google.common.collect.Sets;

import com.islomar.parrotter.model.user.User;
import com.islomar.parrotter.model.user.UserService;

import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.islomar.parrotter.actions.FollowUser.FOLLOWS;
import static com.islomar.parrotter.actions.PostMessage.POST;
import static com.islomar.parrotter.actions.ShowUserWall.WALL;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

@Test
public class FollowUserShould {

  private static final String ALICE_USERNAME = "Alice";
  private static final String BOB_USERNAME = "Bob";

  @Mock UserService userService;
  private FollowUser followUser;

  @BeforeMethod
  public void setUpMethod() {
    initMocks(this);

    followUser = new FollowUser(userService);
  }

  public void follow_another_user() {

    User alice = new User(ALICE_USERNAME);
    given(userService.findUserByUsername(ALICE_USERNAME)).willReturn(alice);

    followUser.execute(ALICE_USERNAME + FOLLOWS + BOB_USERNAME);

    verify(userService).findUserByUsername(ALICE_USERNAME);
    assertThat(alice.getFollowedUsers(), is(Sets.newHashSet(BOB_USERNAME)));
  }

  public void be_able_to_execute_a_follow_command() {

    assertTrue(followUser.canExecuteCommandline(ALICE_USERNAME + FOLLOWS + BOB_USERNAME));
  }

  public void not_be_able_to_execute_a_wall_command() {

    assertFalse(followUser.canExecuteCommandline(ALICE_USERNAME + WALL));
  }

  public void not_be_able_to_execute_a_post_command() {

    assertFalse(followUser.canExecuteCommandline(ALICE_USERNAME + POST + "Hello"));
  }

  public void not_be_able_to_execute_a_view_timeline_command() {

    assertFalse(followUser.canExecuteCommandline(ALICE_USERNAME));
  }
}
