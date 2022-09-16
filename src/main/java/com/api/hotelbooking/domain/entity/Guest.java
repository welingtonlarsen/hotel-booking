package com.api.hotelbooking.domain.entity;

public class Guest {

  private String name;
  private String email;

  public Guest(String name, String email) {
    this.name = name;
    this.email = email;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }
}
