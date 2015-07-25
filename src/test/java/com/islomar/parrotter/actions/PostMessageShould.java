package com.islomar.parrotter.actions;

import com.islomar.parrotter.model.UserService;

import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.islomar.parrotter.actions.PostMessage.POST;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

@Test
public class PostMessageShould {

  private static final String ALICE = "Alice";
  private static final String MESSAGE_TEXT = "I love the weather today";

  @Mock UserService userService;

  private PostMessage postMessage;

  @BeforeMethod
  public void setUpMethod() {
    initMocks(this);
    this.postMessage = new PostMessage(userService);
  }

  public void save_the_user_and_post_the_message() {

    postMessage.execute(ALICE + POST + MESSAGE_TEXT);

    verify(userService).saveUser(ALICE);
    verify(userService).saveMessage(ALICE, MESSAGE_TEXT);
  }

  public void be_able_to_execute_a_post_command() {

    assertTrue(postMessage.canExecuteCommandline(ALICE + POST + MESSAGE_TEXT));
  }

  public void be_able_to_execute_a_follow_command() {

    assertFalse(postMessage.canExecuteCommandline(ALICE + " follow Bob"));
  }

  public void be_able_to_execute_a_wall_command() {

    assertFalse(postMessage.canExecuteCommandline(ALICE + " wall"));
  }

  public void be_able_to_execute_a_view_timeline_command() {

    assertFalse(postMessage.canExecuteCommandline(ALICE));
  }

}
