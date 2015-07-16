package infrastructure.repositories;


import com.islomar.parrotter.infrastructure.repositories.InMemoryMessageRepository;
import com.islomar.parrotter.model.Message;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Clock;
import java.time.Instant;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

@Test
public class InMemoryMessageRepositoryTest {

  private static final String ALICE = "Alice";
  private static final String NON_EXISTING_USER = "NonExistingUser";
  private static final String MESSAGE = "I love the weather today";

  private InMemoryMessageRepository inMemoryMessageRepository;

  @BeforeClass
  public void setUp() {
    inMemoryMessageRepository = new InMemoryMessageRepository(Clock.systemUTC());
  }

  public void a_non_existing_user_has_no_messages_saved() {

    assertThat(inMemoryMessageRepository.findAllMessagesForUser(NON_EXISTING_USER), is(empty()));
  }

  public void save_one_message() {

    Message message = new Message(ALICE, MESSAGE, Instant.now());
    inMemoryMessageRepository.saveMessage(message);

    List<Message> aliceMessages = inMemoryMessageRepository.findAllMessagesForUser(ALICE);
    assertThat(aliceMessages, hasSize(1));
    assertThat(aliceMessages.get(0), is(message));
  }

}
