package com.api.hotelbooking.domain.factories;

import com.api.hotelbooking.domain.entity.BookingDate;
import com.api.hotelbooking.domain.entity.Guest;
import com.api.hotelbooking.domain.entity.Booking;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class BookingFactory {

  public static Booking of(Guest guest, LocalDate checkin, LocalDate checkout) {
    return new Booking(guest, checkin, checkout, calculateBookingDates(checkin, checkout));
  }

  private static List<BookingDate> calculateBookingDates(LocalDate checkin, LocalDate checkout) {
    List<LocalDate> bookingDays = checkin.datesUntil(checkout).collect(Collectors.toList());
    bookingDays.add(checkout);
    return bookingDays.stream().map(BookingDateFactory::of).toList();
  }
}
