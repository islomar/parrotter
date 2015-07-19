package com.islomar.parrotter.model;


import com.islomar.parrotter.model.InMemoryMessageRepository;
import com.islomar.parrotter.model.Message;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

@Test
public class InMemoryMessageRepositoryShould {

  private static final String ALICE = "Alice";
  private static final String BOB = "Bob";
  private static final String NON_EXISTING_USER = "NonExistingUser";
  private static final String MESSAGE = "I love the weather today";

  private InMemoryMessageRepository inMemoryMessageRepository;

  @BeforeClass
  public void setUpClass() {
    inMemoryMessageRepository = new InMemoryMessageRepository(Clock.systemUTC());
  }

  public void a_non_existing_user_has_no_messages_saved() {

    assertThat(inMemoryMessageRepository.findAllMessagesForUser(NON_EXISTING_USER), is(empty()));
  }

  public void save_one_message() {

    Message message = new Message(BOB, MESSAGE, Instant.now());
    inMemoryMessageRepository.saveMessage(message);

    List<Message> bobMessages = inMemoryMessageRepository.findAllMessagesForUser(BOB);

    assertThat(bobMessages, hasSize(1));
    assertThat(bobMessages.get(0), is(message));
  }

  public void save_three_messages_and_then_find_them() {

    Message message1 = new Message(ALICE, MESSAGE + "1", Instant.now());
    Message message2 = new Message(ALICE, MESSAGE + "2", Instant.now().minus(1, ChronoUnit.HOURS));
    Message message3 = new Message(ALICE, MESSAGE + "3", Instant.now().minus(2, ChronoUnit.HOURS));
    inMemoryMessageRepository.saveMessage(message1);
    inMemoryMessageRepository.saveMessage(message2);
    inMemoryMessageRepository.saveMessage(message3);

    List<Message> aliceMessages = inMemoryMessageRepository.findAllMessagesForUser(ALICE);

    assertThat(aliceMessages, hasSize(3));
    assertThat(aliceMessages.get(0), is(message1));
    assertThat(aliceMessages.get(1), is(message2));
    assertThat(aliceMessages.get(2), is(message3));
  }

  private Message createMessage(int id) {
    return new Message(ALICE, MESSAGE + id, Instant.now().minus(id, ChronoUnit.HOURS));
  }

}
