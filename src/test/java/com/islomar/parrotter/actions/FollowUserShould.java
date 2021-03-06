package com.islomar.parrotter.actions;

import com.google.common.collect.Sets;

import com.islomar.parrotter.model.user.User;
import com.islomar.parrotter.model.user.UserService;

import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.islomar.parrotter.actions.FollowUser.FOLLOWS;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

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

}
