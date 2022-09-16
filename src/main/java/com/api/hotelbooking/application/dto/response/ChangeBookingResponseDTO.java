package com.api.hotelbooking.application.dto.response;

import com.api.hotelbooking.domain.entity.Booking;
import java.time.LocalDate;

public record ChangeBookingResponseDTO(Long id, LocalDate checkin, LocalDate checkout) {

  public static ChangeBookingResponseDTO of(Booking booking) {
    return new ChangeBookingResponseDTO(booking.getId(), booking.getCheckin(),
        booking.getCheckout());
  }
}
