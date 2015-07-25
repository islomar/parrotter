package com.islomar.parrotter.actions;

import com.islomar.parrotter.model.message.MessageService;

import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

@Test
public class ReadUserPersonalTimelineShould {

  private static final String ALICE = "Alice";

  @Mock MessageService messageService;
  private ReadUserPersonalTimeline readUserPersonalTimeline;


  @BeforeMethod
  public void setUpMethod() {
    initMocks(this);

    readUserPersonalTimeline = new ReadUserPersonalTimeline(messageService);
  }

  public void show_a_user_wall() {

    readUserPersonalTimeline.execute(ALICE);

    verify(messageService).printTimelineFor(ALICE);
  }

  public void be_able_to_execute_a_view_timeline_command() {

    assertTrue(readUserPersonalTimeline.canExecuteCommandline(ALICE));
  }

  public void be_able_to_execute_a_follow_command() {

    assertFalse(readUserPersonalTimeline.canExecuteCommandline(ALICE + " follow Bob"));
  }

  public void be_able_to_execute_a_wall_command() {

    assertFalse(readUserPersonalTimeline.canExecuteCommandline(ALICE + " wall"));
  }

  public void be_able_to_execute_a_post_command() {

    assertFalse(readUserPersonalTimeline.canExecuteCommandline(ALICE + " -> Hello"));
  }
}
