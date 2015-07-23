package com.islomar.parrotter.actions;

import com.islomar.parrotter.model.UserService;

import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

@Test
public class ReadUserPersonalTimelineShould {

  private static final String ALICE = "Alice";

  @Mock UserService userService;


  @BeforeMethod
  public void setUpMethod() {
    initMocks(this);
  }

  public void show_a_user_wall() {
    ReadUserPersonalTimeline readUserPersonalTimeline = new ReadUserPersonalTimeline(userService, ALICE);

    readUserPersonalTimeline.execute();

    verify(userService).printTimelineFor(ALICE);
  }
}
