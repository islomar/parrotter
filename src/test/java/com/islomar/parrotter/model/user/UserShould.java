package com.islomar.parrotter.model.user;

import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;

@Test
public class UserShould {

  private static final String CHARLIE = "Charlie";
  private static final String BOB = "Bob";


  public void not_follow_anyone_after_creation() {

    User charlie = new User(CHARLIE);

    assertThat(charlie.getFollowedUsers(), is(empty()));
  }

  public void follow_another_user() {

    User charlie = new User(CHARLIE);

    charlie.follow(BOB);

    assertThat(charlie.getFollowedUsers(), hasSize(1));
    assertThat(charlie.getFollowedUsers(), contains(BOB));
  }

  public void get_his_username() {
    User charlie = new User(CHARLIE);

    assertThat(charlie.getUsername(), is(CHARLIE));
  }

}
