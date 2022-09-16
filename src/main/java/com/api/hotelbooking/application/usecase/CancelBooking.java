package com.api.hotelbooking.application.usecase;

import com.api.hotelbooking.application.repository.BookingRepository;
import org.springframework.stereotype.Service;

@Service
public class CancelBooking {

  private BookingRepository bookingRepository;

  public CancelBooking(BookingRepository bookingRepository) {
    this.bookingRepository = bookingRepository;
  }

  public void execute(Long bookingId) {
    this.bookingRepository.delete(bookingId);
  }
}
