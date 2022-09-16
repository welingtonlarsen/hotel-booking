package com.api.hotelbooking.domain.factories;

import com.api.hotelbooking.domain.entity.BookingDate;
import java.time.LocalDate;

public class BookingDateFactory {

  public static BookingDate of(LocalDate date) {
    return new BookingDate(date);
  }

}
