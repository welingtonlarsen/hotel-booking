package com.api.hotelbooking.application.exception;

public class ThereIsNotVacancyException extends RuntimeException {

  public ThereIsNotVacancyException(String message) {
    super(message);
  }
}
