package com.api.hotelbooking.domain.entity;

import java.time.LocalDate;
import java.util.List;

public class Booking {


  private Long id;
  private Guest guest;
  private LocalDate checkin;
  private LocalDate checkout;

  public List<BookingDate> getDates() {
    return dates;
  }

  private List<BookingDate> dates;

  public Booking(Guest guest, LocalDate checkin, LocalDate checkout, List<BookingDate> dates) {
    this.guest = guest;
    this.checkin = checkin;
    this.checkout = checkout;
    this.dates = dates;
  }

  public Long getId() {
    return id;
  }

  public Guest getGuest() {
    return guest;
  }

  public String getGuestEmail() {
    return guest.getEmail();
  }

  public String getGuestName() {
    return guest.getName();
  }

  public LocalDate getCheckin() {
    return checkin;
  }

  public LocalDate getCheckout() {
    return checkout;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public boolean isValidCheckin(LocalDate currentDate) {
    var isFromNextDay = this.checkin.isAfter(currentDate);
    var isLessThanThirtyDays = this.checkin.isBefore(currentDate.plusDays(30));
    return isFromNextDay && isLessThanThirtyDays;
  }

  public boolean isValidCheckout() {
    var maximumLastDay = checkin.plusDays(2);
    return checkout.equals(maximumLastDay) || checkout.isBefore(maximumLastDay);
  }

  public void set(LocalDate checkin, LocalDate checkout) {
    this.checkin = checkin;
    this.checkout = checkout;
  }
}
