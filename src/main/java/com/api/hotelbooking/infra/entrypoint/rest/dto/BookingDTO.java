package com.api.hotelbooking.infra.entrypoint.rest.dto;

import com.api.hotelbooking.domain.entity.Booking;
import com.api.hotelbooking.domain.entity.Guest;
import java.time.LocalDate;

public record BookingDTO(Long id,
                         String guestName,
                         String guestEmail,
                         LocalDate checkin,
                         LocalDate checkout) {

  public static BookingDTO of(Booking booking) {
    return new BookingDTO(booking.getId(), booking.getGuestName(), booking.getGuestName(),
        booking.getCheckin(), booking.getCheckout());
  }
}
