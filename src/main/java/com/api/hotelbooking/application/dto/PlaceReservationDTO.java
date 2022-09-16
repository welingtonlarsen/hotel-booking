package com.api.hotelbooking.application.dto;

import com.api.hotelbooking.domain.entity.Booking;
import com.api.hotelbooking.domain.factories.GuestFactory;
import com.api.hotelbooking.domain.factories.BookingFactory;
import java.time.LocalDate;

public record PlaceReservationDTO(String guestName, String guestEmail, LocalDate currentDay,
                                  LocalDate checkin, LocalDate checkout) {

  public Booking parseToBookingEntity() {
    var guest = GuestFactory.of(this.guestName, this.guestEmail);
    return BookingFactory.of(guest, checkin, checkout);
  }
}
