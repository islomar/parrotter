package com.islomar.parrotter.actions;

import com.islomar.parrotter.model.user.User;
import com.islomar.parrotter.model.user.UserService;

import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

@Test
public class FollowUserShould {

  private static final String ALICE = "Alice";
  private static final String BOB = "Bob";

  @Mock UserService userService;

  @BeforeMethod
  public void setUpMethod() {
    initMocks(this);

    given(userService.findUserByUsername(ALICE)).willReturn(new User(ALICE));
  }

  public void follow_another_user() {
    FollowUser postMessage = new FollowUser(userService, ALICE, BOB);

    postMessage.execute();

    verify(userService).findUserByUsername(ALICE);
  }
}
