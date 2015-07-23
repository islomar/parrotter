package com.islomar.parrotter.infrastructure.utils;

import com.islomar.parrotter.actions.Command;
import com.islomar.parrotter.actions.FollowUser;
import com.islomar.parrotter.actions.PostMessage;
import com.islomar.parrotter.actions.ReadUserPersonalTimeline;
import com.islomar.parrotter.actions.ShowUserWall;
import com.islomar.parrotter.controller.utils.CommandGenerator;
import com.islomar.parrotter.model.UserService;

import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.mockito.MockitoAnnotations.initMocks;

@Test
public class CommandGeneratorShould {

  private CommandGenerator commandGenerator;
  @Mock private UserService userService;

  @BeforeMethod
  public void setUpMethod() {
    initMocks(this);

    commandGenerator = new CommandGenerator(userService);
  }

  public void create_PostMessage_object_for_post_command_line() {

    String inputCommandLine = "Alice -> I love the weather today";

    Command commandFromInputLine = commandGenerator.createCommandFromInputLine(inputCommandLine);

    assertThat(commandFromInputLine, instanceOf(PostMessage.class));
  }

  public void create_FollowUser_object_for_follow_command_line() {

    String inputCommandLine = "Alice follows Bob";

    Command commandFromInputLine = commandGenerator.createCommandFromInputLine(inputCommandLine);

    assertThat(commandFromInputLine, instanceOf(FollowUser.class));
  }

  public void create_ReadUserPersonalTimeline_object_for_read_personal_timeline_command_line() {

    String inputCommandLine = "Alice";

    Command commandFromInputLine = commandGenerator.createCommandFromInputLine(inputCommandLine);

    assertThat(commandFromInputLine, instanceOf(ReadUserPersonalTimeline.class));
  }

  public void create_ShowUserWall_object_for_show_user_wall_command_line() {

    String inputCommandLine = "Alice wall";

    Command commandFromInputLine = commandGenerator.createCommandFromInputLine(inputCommandLine);

    assertThat(commandFromInputLine, instanceOf(ShowUserWall.class));
  }

}
