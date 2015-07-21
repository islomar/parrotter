package com.islomar.parrotter.actions;

import com.islomar.parrotter.model.message.MessageService;

import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

@Test
public class ReadUserPersonalTimelineShould {

  private static final String ALICE = "Alice";

  @Mock MessageService messageService;


  @BeforeMethod
  public void setUpMethod() {
    initMocks(this);
  }

  public void show_a_user_wall() {
    ReadUserPersonalTimeline readUserPersonalTimeline = new ReadUserPersonalTimeline(messageService, ALICE);

    readUserPersonalTimeline.execute();

    verify(messageService).printTimelineFor(ALICE);
  }
}
