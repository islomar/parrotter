package com.islomar.parrotter.services;

import com.islomar.parrotter.infrastructure.Console;
import com.islomar.parrotter.infrastructure.repositories.UserRepository;
import com.islomar.parrotter.model.user.User;

import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.MockitoAnnotations.initMocks;

@Test
public class FollowUserServiceShould {

  private static final String CHARLIE = "Charlie";
  private static final String BOB = "Bob";

  @Mock UserRepository userRepository;
  @Mock Console console;


  @BeforeMethod
  public void setUpMethod() {

    initMocks(this);
  }

  public void follow_another_user() {

    User charlie = new User(CHARLIE);

    assertThat(charlie.getFollowedUsers(), is(empty()));

    charlie.follow(BOB);

    assertThat(charlie.getFollowedUsers(), hasSize(1));
    assertThat(charlie.getFollowedUsers(), contains(BOB));
  }
}
