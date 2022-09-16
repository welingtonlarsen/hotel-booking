package com.api.hotelbooking.domain.factories;

import com.api.hotelbooking.domain.entity.Guest;

public class GuestFactory {

  public static Guest of(String name, String email) {
    return new Guest(name, email);
  }
}
