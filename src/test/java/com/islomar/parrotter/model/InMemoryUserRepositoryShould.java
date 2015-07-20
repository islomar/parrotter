package com.islomar.parrotter.model;

import com.islomar.parrotter.model.user.InMemoryUserRepository;
import com.islomar.parrotter.model.user.User;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@Test
public class InMemoryUserRepositoryShould {

  private static final String ALICE = "Alice";

  private InMemoryUserRepository inMemoryUserRepository;

  @BeforeMethod
  public void setUpMethod() {
    this.inMemoryUserRepository = new InMemoryUserRepository();
  }

  public void save_a_user() {
    inMemoryUserRepository.saveUser(ALICE);

    User alice = inMemoryUserRepository.getUser(ALICE);
    assertThat(alice.getUsername(), is(ALICE));
  }

}
