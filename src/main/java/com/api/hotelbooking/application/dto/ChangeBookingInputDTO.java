package com.api.hotelbooking.application.dto;

import com.api.hotelbooking.domain.entity.Booking;
import com.api.hotelbooking.domain.factories.BookingFactory;
import java.time.LocalDate;

public record ChangeBookingInputDTO(Long id, LocalDate currentDay, LocalDate checkin,
                                    LocalDate checkout) {

  public Booking parseToBooking() {
    // todo: change guest to optional
    return BookingFactory.of(null, checkin, checkout);
  }

}
