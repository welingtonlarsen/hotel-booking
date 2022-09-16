package com.api.hotelbooking.application.repository;

import com.api.hotelbooking.domain.entity.Booking;
import java.util.List;

public interface BookingRepository {

  void insert(Booking booking);

  void delete(Long bookingId);

  Booking findById(Long bookingId);

  List<Booking> findAll(String guestEmail);

  void replace(Booking oldBooking, Booking newBooking);
}
