package com.api.hotelbooking.application.usecase;

import com.api.hotelbooking.application.dto.AvailabilityDTO;
import com.api.hotelbooking.application.repository.BookingDateRepository;
import com.api.hotelbooking.domain.entity.BookingDate;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.stream.Stream;
import org.springframework.stereotype.Service;

@Service
public class CheckAvailability {

  private BookingDateRepository bookingDateRepository;

  public CheckAvailability(BookingDateRepository bookingDateRepository) {
    this.bookingDateRepository = bookingDateRepository;
  }

  public AvailabilityDTO execute(LocalDate from, LocalDate until) {
    var unavailableDays = this.bookingDateRepository.findBetween(from, until)
        .stream()
        .map(BookingDate::getDate)
        .toList();

    var availableDays = Stream.iterate(from, date -> date.plusDays(1))
        .limit(ChronoUnit.DAYS.between(from, until) + 1)
        .filter(day -> !unavailableDays.contains(day))
        .toList();

    return new AvailabilityDTO(availableDays, unavailableDays);
  }
}
