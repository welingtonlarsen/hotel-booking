package com.api.hotelbooking.application.usecase;

import com.api.hotelbooking.application.repository.BookingRepository;
import com.api.hotelbooking.domain.entity.Booking;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class GetAllGuestBookings {

  private BookingRepository bookingRepository;

  public GetAllGuestBookings(BookingRepository bookingRepository) {
    this.bookingRepository = bookingRepository;
  }

  public List<Booking> execute(String guestEmail) {
    return this.bookingRepository.findAll(guestEmail);
  }
}
