package com.islomar.parrotter.actions;

import com.islomar.parrotter.model.message.MessageService;
import com.islomar.parrotter.model.user.UserService;

import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.islomar.parrotter.actions.PostMessage.POST;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

@Test
public class PostMessageShould {

  private static final String ALICE = "Alice";
  private static final String MESSAGE_TEXT = "I love the weather today";

  @Mock UserService userService;
  @Mock MessageService messageService;

  private PostMessage postMessage;

  @BeforeMethod
  public void setUpMethod() {
    initMocks(this);
    this.postMessage = new PostMessage(userService, messageService);
  }

  public void save_the_user_and_post_the_message() {

    postMessage.execute(ALICE + POST + MESSAGE_TEXT);

    verify(userService).saveUser(ALICE);
    verify(messageService).saveMessage(ALICE, MESSAGE_TEXT);
  }

}
