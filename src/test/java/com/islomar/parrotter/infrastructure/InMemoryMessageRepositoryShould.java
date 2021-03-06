package com.islomar.parrotter.infrastructure;


import com.islomar.parrotter.infrastructure.repositories.InMemoryMessageRepository;
import com.islomar.parrotter.model.message.Message;
import org.mockito.Mock;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;

@Test
public class InMemoryMessageRepositoryShould {

  private static final String ALICE = "Alice";
  private static final String BOB = "Bob";
  private static final String NON_EXISTING_USER = "NonExistingUser";
  private static final String MESSAGE_TEXT = "I love the weather today";

  @Mock private Clock clock;

  private InMemoryMessageRepository inMemoryMessageRepository;

  @BeforeClass
  public void setUpClass() {

    initMocks(this);

    inMemoryMessageRepository = new InMemoryMessageRepository();
  }

  public void a_non_existing_user_has_no_messages_saved() {

    assertThat(inMemoryMessageRepository.findAllMessagesForUser(NON_EXISTING_USER), is(empty()));
  }

  public void save_one_message() {

    Instant now = Instant.now();
    given(clock.instant()).willReturn(now);
    Message message = new Message(BOB, MESSAGE_TEXT, now);
    inMemoryMessageRepository.saveMessage(message);

    List<Message> bobMessages = inMemoryMessageRepository.findAllMessagesForUser(BOB);

    assertThat(bobMessages, hasSize(1));
    assertThat(bobMessages.get(0), is(message));
  }

  public void save_three_messages_and_then_find_them() {

    Instant now = Instant.now();
    given(clock.instant()).willReturn(now,
                                      now.minus(1, ChronoUnit.HOURS),
                                      now.minus(2, ChronoUnit.HOURS));
    Message message1 = new Message(ALICE, MESSAGE_TEXT + "1", now);
    Message message2 = new Message(ALICE, MESSAGE_TEXT + "2", now.minus(1, ChronoUnit.HOURS));
    Message message3 = new Message(ALICE, MESSAGE_TEXT + "3", now.minus(2, ChronoUnit.HOURS));
    inMemoryMessageRepository.saveMessage(message1);
    inMemoryMessageRepository.saveMessage(message2);
    inMemoryMessageRepository.saveMessage(message3);

    List<Message> aliceMessages = inMemoryMessageRepository.findAllMessagesForUser(ALICE);

    assertThat(aliceMessages, is(Arrays.asList(message1, message2, message3)));
  }

}
