import com.islomar.parrotter.actions.ViewUserTimeline;
import com.islomar.parrotter.infrastructure.Console;

import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

@Test
public class ViewUserTimelineTest {

  @Mock
  Console console;

  @BeforeMethod
  public void setUp() {
    initMocks(this);
  }

  public void no_message_shown_for_non_existing_user() {

    ViewUserTimeline viewUserTimeline = new ViewUserTimeline(console);
    viewUserTimeline.view("NonExistingUser");

    verify(console).printLine("");
  }
}
