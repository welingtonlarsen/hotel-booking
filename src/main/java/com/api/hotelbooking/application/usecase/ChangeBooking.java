package com.api.hotelbooking.application.usecase;

import static com.api.hotelbooking.application.util.DesiredDatesValidationUtil.verifyIsValidCheckin;
import static com.api.hotelbooking.application.util.DesiredDatesValidationUtil.verifyIsValidCheckout;
import static com.api.hotelbooking.application.util.DesiredDatesValidationUtil.verifyVacancyToDesiredDates;

import com.api.hotelbooking.application.dto.ChangeBookingInputDTO;
import com.api.hotelbooking.application.dto.response.ChangeBookingResponseDTO;
import com.api.hotelbooking.application.repository.BookingDateRepository;
import com.api.hotelbooking.application.repository.BookingRepository;
import com.api.hotelbooking.domain.entity.Booking;
import com.api.hotelbooking.domain.factories.BookingFactory;
import org.springframework.stereotype.Service;

@Service
public class ChangeBooking {

  private BookingRepository bookingRepository;
  private BookingDateRepository bookingDateRepository;

  public ChangeBooking(BookingRepository bookingRepository,
      BookingDateRepository bookingDateRepository) {
    this.bookingRepository = bookingRepository;
    this.bookingDateRepository = bookingDateRepository;
  }

  public void execute(ChangeBookingInputDTO changeBookingDTO) {
    var booking = changeBookingDTO.parseToBooking();

    verifyIsValidCheckin(booking, changeBookingDTO.currentDay());
    verifyIsValidCheckout(booking);
    verifyVacancyToDesiredDates(booking.getCheckin(), booking.getCheckout(),
        this.bookingDateRepository);

    Booking bookingPersisted = this.bookingRepository.findById(changeBookingDTO.id());

    Booking newBooking = BookingFactory.of(bookingPersisted.getGuest(), booking.getCheckin(),
        booking.getCheckout());

    this.bookingRepository.replace(bookingPersisted, newBooking);
  }
}
