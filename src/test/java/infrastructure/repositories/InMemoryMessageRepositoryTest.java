package infrastructure.repositories;

import com.islomar.parrotter.infrastructure.repositories.InMemoryMessageRepository;

import org.hamcrest.collection.IsCollectionWithSize;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

@Test
public class InMemoryMessageRepositoryTest {

  private InMemoryMessageRepository inMemoryMessageRepository;

  @BeforeClass
  public void setUp() {
    inMemoryMessageRepository = new InMemoryMessageRepository();
  }

  public void save_one_message() {
    assertThat(inMemoryMessageRepository.findAllMessagesForUser("username"), is(empty()));

    inMemoryMessageRepository.saveMessage("username", "Hello world");

    assertThat(inMemoryMessageRepository.findAllMessagesForUser("username"), hasSize(1));
  }

}
