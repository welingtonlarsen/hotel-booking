package com.api.hotelbooking.application.exception;

public class InvalidCheckinException extends RuntimeException {

  public InvalidCheckinException(String message) {
    super(message);
  }
}
