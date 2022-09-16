package com.api.hotelbooking.application.exception;

public class InvalidCheckoutException extends RuntimeException {

  public InvalidCheckoutException(String message) {
    super(message);
  }
}
