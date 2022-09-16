package com.api.hotelbooking.application.util;

import com.api.hotelbooking.application.exception.InvalidCheckinException;
import com.api.hotelbooking.application.exception.InvalidCheckoutException;
import com.api.hotelbooking.application.exception.ThereIsNotVacancyException;
import com.api.hotelbooking.application.repository.BookingDateRepository;
import com.api.hotelbooking.domain.entity.Booking;
import com.api.hotelbooking.domain.entity.BookingDate;
import java.time.LocalDate;
import java.util.List;

public class DesiredDatesValidationUtil {

  public static void verifyIsValidCheckin(Booking booking, LocalDate currentDay) {
    if (!booking.isValidCheckin(currentDay)) {
      throw new InvalidCheckinException("Checkin date is invalid!");
    }
  }

  public static void verifyIsValidCheckout(Booking booking) {
    if (!booking.isValidCheckout()) {
      throw new InvalidCheckoutException("Checkout date is invalid!");
    }
  }

  public static void verifyVacancyToDesiredDates(LocalDate checkin, LocalDate checkout,
      BookingDateRepository bookingDateRepository) {
    List<BookingDate> bookingsBetweenDesiredDates =
        bookingDateRepository.findBetween(checkin, checkout);
    if (!bookingsBetweenDesiredDates.isEmpty()) {
      throw new ThereIsNotVacancyException("There is not vacancy for desired dates!");
    }
  }
}
