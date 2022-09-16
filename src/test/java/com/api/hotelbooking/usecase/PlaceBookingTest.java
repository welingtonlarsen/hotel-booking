package com.api.hotelbooking.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.api.hotelbooking.application.dto.PlaceReservationDTO;
import com.api.hotelbooking.application.exception.InvalidCheckinException;
import com.api.hotelbooking.application.exception.InvalidCheckoutException;
import com.api.hotelbooking.application.exception.ThereIsNotVacancyException;
import com.api.hotelbooking.application.repository.BookingDateRepository;
import com.api.hotelbooking.application.repository.BookingRepository;
import com.api.hotelbooking.application.usecase.PlaceBooking;
import com.api.hotelbooking.domain.entity.Booking;
import com.api.hotelbooking.domain.factories.BookingDateFactory;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class PlaceBookingTest {

  private BookingRepository bookingRepository;
  private BookingDateRepository bookingDateRepository;
  private PlaceBooking placeBooking;

  private LocalDate currentDay, nextDay;

  @BeforeEach
  public void beforeEach() {
    bookingRepository = Mockito.mock(BookingRepository.class);
    bookingDateRepository = Mockito.mock(BookingDateRepository.class);

    placeBooking = new PlaceBooking(bookingRepository, bookingDateRepository);

    currentDay = LocalDate.of(2022, Month.JANUARY, 1);
    nextDay = currentDay.plusDays(1);
  }

  @Test
  void shouldSuccessfulBookARoom() {
    Mockito.when(
            bookingDateRepository.findBetween(any(LocalDate.class), any(LocalDate.class)))
        .thenReturn(List.of());

    var placeReservationDto = new PlaceReservationDTO("Joseph Warren",
        "test@test.com", currentDay, nextDay, nextDay.plusDays(2)
    );
    placeBooking.execute(placeReservationDto);

    verify(bookingRepository, times(1)).insert(any(Booking.class));
    verify(bookingDateRepository, times(1)).findBetween(any(LocalDate.class), any(LocalDate.class));
  }

  @Test
  void shouldThrowInvalidCheckinExceptionWhenCheckingDayIsEqualCurrentDay() {
    var placeReservationDto = new PlaceReservationDTO("Joseph Warren",
        "test@test.com", currentDay, currentDay, nextDay
    );

    Exception exception = assertThrows(InvalidCheckinException.class,
        () -> placeBooking.execute(placeReservationDto));
    assertEquals("Checkin date is invalid!", exception.getMessage());
  }

  @Test
  void shouldThrowInvalidCheckoutExceptionWhenBookingPeriodIsHigherThanThree() {
    var placeReservationDto = new PlaceReservationDTO("Joseph Warren",
        "test@test.com", currentDay, nextDay, nextDay.plusDays(3)
    );

    Exception exception = assertThrows(InvalidCheckoutException.class,
        () -> placeBooking.execute(placeReservationDto));
    assertEquals("Checkout date is invalid!", exception.getMessage());
  }

  @Test
  void shouldThrowThereIsNotVacancyExceptionWhenDesiredBookingDaysAreNotVacancy() {
    Mockito.when(
            bookingDateRepository.findBetween(any(LocalDate.class), any(LocalDate.class)))
        .thenReturn(List.of(BookingDateFactory.of(nextDay)));
    var placeReservationDto = new PlaceReservationDTO("Joseph Warren",
        "test@test.com", currentDay, nextDay, nextDay.plusDays(2)
    );

    Exception exception = assertThrows(ThereIsNotVacancyException.class,
        () -> placeBooking.execute(placeReservationDto));
    assertEquals("There is not vacancy for desired dates!", exception.getMessage());
  }
}
