package com.api.hotelbooking.infra.db.exception;

public class ThereIsNotRegister extends RuntimeException {

  public ThereIsNotRegister(String message) {
    super(message);
  }
}
