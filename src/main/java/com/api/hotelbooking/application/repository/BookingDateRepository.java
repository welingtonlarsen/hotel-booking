package com.api.hotelbooking.application.repository;

import com.api.hotelbooking.domain.entity.BookingDate;
import java.time.LocalDate;
import java.util.List;

public interface BookingDateRepository {

  List<BookingDate> findBetween(LocalDate init, LocalDate end);
}
