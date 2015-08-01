package com.islomar.parrotter.model.user;

import com.islomar.parrotter.infrastructure.Console;
import com.islomar.parrotter.infrastructure.repositories.UserRepository;

import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

@Test
public class UserServiceShould {

  private static final String CHARLIE_USERNAME = "Charlie";
  private static final User CHARLIE = new User(CHARLIE_USERNAME);

  @Mock UserRepository userRepository;
  @Mock Console console;

  private UserService userService;


  @BeforeMethod
  public void setUpMethod() {

    initMocks(this);
    userService = new UserService(userRepository);
  }

  public void save_a_user() {

    userService.saveUser(CHARLIE_USERNAME);

    verify(userRepository).saveUser(CHARLIE);
  }

  public void find_a_user() {

    userService.findUserByUsername(CHARLIE_USERNAME);

    verify(userRepository).findUserByUsername(CHARLIE_USERNAME);
  }

}
