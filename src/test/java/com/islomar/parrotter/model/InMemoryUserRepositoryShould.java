package com.islomar.parrotter.model;

import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;

@Test
public class InMemoryUserRepositoryShould {

  private static final String ALICE = "Alice";
  private static final String BOB = "Bob";
  private static final String NON_EXISTING_USER = "NonExistingUser";
  private static final String MESSAGE_TEXT = "I love the weather today";

  private InMemoryUserRepository inMemoryUserRepository;

  @Mock private Clock clock;

  @BeforeMethod
  public void setUpMethod() {
    initMocks(this);
    this.inMemoryUserRepository = new InMemoryUserRepository(clock);
  }

  public void save_a_user() {
    inMemoryUserRepository.saveUser(ALICE);

    User alice = inMemoryUserRepository.findUserByUsername(ALICE);
    assertThat(alice.getUsername(), is(ALICE));
  }

  public void update_a_user() {
    inMemoryUserRepository.saveUser(ALICE);

    User alice = inMemoryUserRepository.findUserByUsername(ALICE);

    assertThat(alice.getFollowedUsers(), is(empty()));

    alice.follow(BOB);

    assertThat(alice.getFollowedUsers(), hasSize(1));
  }

  public void not_find_a_non_existing_user_by_username() {
    User nonExistingUser = inMemoryUserRepository.findUserByUsername(ALICE);

    assertThat(nonExistingUser, instanceOf(NullUser.class));
  }

  public void a_non_existing_user_has_no_messages_saved() {

    assertThat(inMemoryUserRepository.findAllMessagesForUser(NON_EXISTING_USER), is(empty()));
  }

  public void save_one_message() {

    Instant now = Instant.now();
    given(clock.instant()).willReturn(now);
    Message message = new Message(BOB, MESSAGE_TEXT, now);
    inMemoryUserRepository.saveMessage(BOB, MESSAGE_TEXT);

    List<Message> bobMessages = inMemoryUserRepository.findAllMessagesForUser(BOB);

    assertThat(bobMessages, hasSize(1));
    assertThat(bobMessages.get(0), is(message));
  }

  public void save_three_messages_and_then_find_them() {

    Instant now = Instant.now();
    given(clock.instant()).willReturn(now, now.minus(1, ChronoUnit.HOURS), now.minus(2, ChronoUnit.HOURS));

    Message message1 = new Message(ALICE, MESSAGE_TEXT + "1", now);
    Message message2 = new Message(ALICE, MESSAGE_TEXT + "2", now.minus(1, ChronoUnit.HOURS));
    Message message3 = new Message(ALICE, MESSAGE_TEXT + "3", now.minus(2, ChronoUnit.HOURS));
    inMemoryUserRepository.saveMessage(ALICE, MESSAGE_TEXT + "1");
    inMemoryUserRepository.saveMessage(ALICE, MESSAGE_TEXT + "2");
    inMemoryUserRepository.saveMessage(ALICE, MESSAGE_TEXT + "3");

    List<Message> aliceMessages = inMemoryUserRepository.findAllMessagesForUser(ALICE);

    assertThat(aliceMessages, hasSize(3));
    assertThat(aliceMessages.get(0), is(message1));
    assertThat(aliceMessages.get(1), is(message2));
    assertThat(aliceMessages.get(2), is(message3));
  }

}
