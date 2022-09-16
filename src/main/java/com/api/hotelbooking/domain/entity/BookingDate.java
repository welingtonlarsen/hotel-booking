package com.api.hotelbooking.domain.entity;

import java.time.LocalDate;

public class BookingDate {

  private LocalDate date;

  public BookingDate(LocalDate date) {
    this.date = date;
  }

  public LocalDate getDate() {
    return date;
  }
}
