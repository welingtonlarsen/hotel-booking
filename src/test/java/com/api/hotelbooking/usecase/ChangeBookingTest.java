package com.api.hotelbooking.usecase;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import com.api.hotelbooking.application.dto.ChangeBookingInputDTO;
import com.api.hotelbooking.application.repository.BookingDateRepository;
import com.api.hotelbooking.application.repository.BookingRepository;
import com.api.hotelbooking.application.usecase.ChangeBooking;
import com.api.hotelbooking.domain.entity.Booking;
import com.api.hotelbooking.domain.factories.BookingFactory;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ChangeBookingTest {

  private BookingRepository bookingRepository;
  private BookingDateRepository bookingDateRepository;

  private ChangeBooking changeBookingUseCase;

  private LocalDate currentDay, nextDay;
  private Booking booking;

  @BeforeEach
  public void beforeEach() {
    bookingRepository = Mockito.mock(BookingRepository.class);
    bookingDateRepository = Mockito.mock(BookingDateRepository.class);
    changeBookingUseCase = new ChangeBooking(bookingRepository, bookingDateRepository);
    currentDay = LocalDate.of(2022, Month.JANUARY, 1);
    nextDay = currentDay.plusDays(1);
    booking = BookingFactory.of(null, nextDay, nextDay.plusDays(1));
  }

  @Test
  void shouldSuccessfulChangeABooking() {
    Mockito.when(
            bookingRepository.findById(any(Long.class)))
        .thenReturn(booking);
    Mockito.when(
            bookingDateRepository.findBetween(any(LocalDate.class), any(LocalDate.class)))
        .thenReturn(List.of());

    var changeBookingDto = new ChangeBookingInputDTO(1L, currentDay, nextDay, nextDay.plusDays(2));
    changeBookingUseCase.execute(changeBookingDto);

    verify(bookingDateRepository, Mockito.times(1)).findBetween(nextDay, nextDay.plusDays(2));
    verify(bookingRepository, Mockito.times(1)).findById(1L);
    verify(bookingRepository, Mockito.times(1)).replace(any(Booking.class), any(Booking.class));
  }

}
