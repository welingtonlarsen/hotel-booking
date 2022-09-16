package com.api.hotelbooking.entity;

import com.api.hotelbooking.domain.entity.Booking;
import com.api.hotelbooking.domain.entity.Guest;
import com.api.hotelbooking.domain.factories.BookingFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookingTest {

  private static Guest guest;
  private static LocalDate currentDay, nextDay;

  @BeforeAll
  public static void beforeAll() {
    guest = new Guest("Jose da silva", "jose@test.com");
    currentDay = LocalDate.of(2022, Month.JANUARY, 1);
    nextDay = currentDay.plusDays(1);
  }

  @Test
  void shouldBeValidCheckinDate() {
    Booking booking = BookingFactory.of(guest, nextDay, nextDay.plusDays(1));
    boolean result = booking.isValidCheckin(currentDay);

    assertTrue(result);
  }

  @Test
  void shouldBeInvalidCheckinDate() {
    Booking booking = BookingFactory.of(guest, currentDay, nextDay);

    boolean result = booking.isValidCheckin(currentDay);

    assertFalse(result);
  }

  @Test
  void shouldBeValidCheckoutDate() {
    Booking booking = BookingFactory.of(guest, nextDay, nextDay.plusDays(2));

    boolean result = booking.isValidCheckout();

    assertTrue(result);
  }

  @Test
  void shouldBeInvalidCheckoutDate() {
    Booking booking = BookingFactory.of(guest, nextDay, nextDay.plusDays(3));

    boolean result = booking.isValidCheckout();

    assertFalse(result);
  }
}
