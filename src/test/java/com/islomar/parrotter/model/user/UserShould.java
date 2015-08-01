package com.islomar.parrotter.model.user;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Set;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;

@Test
public class UserShould {

  private static final String CHARLIE = "Charlie";
  private static final String BOB = "Bob";
  private static final String ALICE = "Alice";


  public void not_follow_anyone_after_creation() {

    User charlie = new User(CHARLIE);

    assertThat(charlie.getFollowedUsers(), is(empty()));
  }

  public void follow_another_user() {

    User charlie = new User(CHARLIE);

    charlie.follow(BOB);
    charlie.follow(ALICE);

    Set<String> followedUsers = charlie.getFollowedUsers();
    assertThat(followedUsers, is(Arrays.asList(BOB, ALICE)));
  }

  public void not_follow_twice_the_same_user() {

    User charlie = new User(CHARLIE);

    charlie.follow(BOB);
    charlie.follow(BOB);

    assertThat(charlie.getFollowedUsers(), is(BOB));
  }

  public void get_his_username() {
    User charlie = new User(CHARLIE);

    assertThat(charlie.getUsername(), is(CHARLIE));
  }

}
