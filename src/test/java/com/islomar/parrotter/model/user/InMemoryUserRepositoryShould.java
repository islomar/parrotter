package com.islomar.parrotter.model.user;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

@Test
public class InMemoryUserRepositoryShould {

  private static final String ALICE_USERNAME = "Alice";
  private static final User ALICE = new User(ALICE_USERNAME);
  private static final String BOB_USERNAME = "Bob";

  private InMemoryUserRepository inMemoryUserRepository;

  @BeforeMethod
  public void setUpMethod() {
    this.inMemoryUserRepository = new InMemoryUserRepository();
  }

  public void save_a_user() {
    inMemoryUserRepository.saveUser(ALICE);

    User alice = inMemoryUserRepository.findUserByUsername(ALICE_USERNAME);

    assertThat(alice.getUsername(), is(ALICE));
  }

  public void update_a_user() {
    inMemoryUserRepository.saveUser(ALICE);

    User alice = inMemoryUserRepository.findUserByUsername(ALICE_USERNAME);

    assertThat(alice.getFollowedUsers(), is(empty()));

    alice.follow(BOB_USERNAME);

    assertThat(alice.getFollowedUsers(), hasSize(1));
  }

  public void not_find_a_non_existing_user_by_username() {
    User nonExistingUser = inMemoryUserRepository.findUserByUsername(ALICE_USERNAME);

    assertThat(nonExistingUser, instanceOf(NullUser.class));
  }

}
