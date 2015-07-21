package com.islomar.parrotter.model.user;

import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.isEmptyOrNullString;

@Test
public class NullUserShould {

  private static final String BOB = "Bob";

  public void have_null_as_username() {

    User nullUser = new NullUser();

    assertThat(nullUser.getUsername(), isEmptyOrNullString());
  }

  public void not_follow_anyone() {

    User nullUser = new NullUser();

    nullUser.follow(BOB);

    assertThat(nullUser.getFollowedUsers(), is(empty()));
  }
}
