package com.islomar.parrotter.model;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

@Test
public class InMemoryUserRepositoryShould {

  private static final String ALICE = "Alice";
  private static final String BOB = "Bob";

  private InMemoryUserRepository inMemoryUserRepository;

  @BeforeMethod
  public void setUpMethod() {
    this.inMemoryUserRepository = new InMemoryUserRepository();
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

}
