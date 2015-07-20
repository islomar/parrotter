package com.islomar.parrotter.infrastructure.repositories;


public interface UserRepository {

  public void saveUser(String username);
  public void updateUser(String username);

}
