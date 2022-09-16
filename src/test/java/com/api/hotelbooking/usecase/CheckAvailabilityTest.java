package com.api.hotelbooking.usecase;

import static org.mockito.Mockito.mock;

import com.api.hotelbooking.application.repository.BookingDateRepository;
import com.api.hotelbooking.application.usecase.CheckAvailability;
import com.api.hotelbooking.domain.factories.BookingDateFactory;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class CheckAvailabilityTest {

  private BookingDateRepository bookingDateRepository;

  private CheckAvailability checkAvailabilityUseCase;

  @BeforeEach
  public void beforeEach() {
    bookingDateRepository = mock(BookingDateRepository.class);
    checkAvailabilityUseCase = new CheckAvailability(bookingDateRepository);
  }

  @Test
  void shouldSuccessfulCheckAvailability() {
    LocalDate from = LocalDate.of(2022, Month.JANUARY, 1);
    LocalDate until = LocalDate.of(2022, Month.JANUARY, 10);
    List<LocalDate> unavailableDays = List.of(
        LocalDate.of(2022, Month.JANUARY, 3),
        LocalDate.of(2022, Month.JANUARY, 4),
        LocalDate.of(2022, Month.JANUARY, 5),
        LocalDate.of(2022, Month.JANUARY, 6),
        LocalDate.of(2022, Month.JANUARY, 8)
    );
    Mockito.when(bookingDateRepository.findBetween(from, until)).thenReturn(
        unavailableDays.stream().map(BookingDateFactory::of).toList()
    );

    var result = checkAvailabilityUseCase.execute(from, until);

    var expectAvailableDays = List.of(
        LocalDate.of(2022, Month.JANUARY, 1),
        LocalDate.of(2022, Month.JANUARY, 2),
        LocalDate.of(2022, Month.JANUARY, 7),
        LocalDate.of(2022, Month.JANUARY, 9),
        LocalDate.of(2022, Month.JANUARY, 10)
    );
    Assertions.assertArrayEquals(unavailableDays.toArray(), result.unavailable().toArray());
    Assertions.assertArrayEquals(expectAvailableDays.toArray(), result.available().toArray());
  }

}
