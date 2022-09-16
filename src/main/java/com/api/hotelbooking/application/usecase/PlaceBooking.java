package com.api.hotelbooking.application.usecase;

import static com.api.hotelbooking.application.util.DesiredDatesValidationUtil.verifyIsValidCheckin;
import static com.api.hotelbooking.application.util.DesiredDatesValidationUtil.verifyIsValidCheckout;
import static com.api.hotelbooking.application.util.DesiredDatesValidationUtil.verifyVacancyToDesiredDates;

import com.api.hotelbooking.application.dto.PlaceReservationDTO;
import com.api.hotelbooking.application.repository.BookingDateRepository;
import com.api.hotelbooking.application.repository.BookingRepository;
import org.springframework.stereotype.Service;

@Service
public class PlaceBooking {

  private BookingRepository bookingRepository;
  private BookingDateRepository bookingDateRepository;

  public PlaceBooking(BookingRepository bookingRepository,
      BookingDateRepository bookingDateRepository) {
    this.bookingRepository = bookingRepository;
    this.bookingDateRepository = bookingDateRepository;
  }

  public void execute(PlaceReservationDTO placeReservationDto) {
    var booking = placeReservationDto.parseToBookingEntity();
    verifyIsValidCheckin(booking, placeReservationDto.currentDay());
    verifyIsValidCheckout(booking);
    verifyVacancyToDesiredDates(booking.getCheckin(), booking.getCheckout(),
        this.bookingDateRepository);
    this.bookingRepository.insert(booking);
  }
}
