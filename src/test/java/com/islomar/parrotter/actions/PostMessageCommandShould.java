package com.islomar.parrotter.actions;

import com.islomar.parrotter.services.PostMessageService;
import com.islomar.parrotter.services.UserService;

import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

@Test
public class PostMessageCommandShould {

  private static final String ALICE = "Alice";
  private static final String MESSAGE_TEXT = "I love the weather today";

  @Mock private UserService userService;
  @Mock private PostMessageService postMessageService;

  private PostMessageCommand postMessageCommand;


  @BeforeMethod
  public void setUpMethod() {
    initMocks(this);

    postMessageCommand = new PostMessageCommand(userService, postMessageService, ALICE, MESSAGE_TEXT);
  }

  public void save_the_user_and_post_the_message() {

    postMessageCommand.execute();

    verify(userService).saveUser(ALICE);
    verify(postMessageService).execute(ALICE, MESSAGE_TEXT);
  }

}
