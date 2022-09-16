package com.api.hotelbooking.infra.db.dao;

import com.api.hotelbooking.infra.db.model.BookingDate;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingDateDAO extends JpaRepository<BookingDate, Long> {

  List<BookingDate> findOneByDateGreaterThanEqualAndDateLessThanEqual(LocalDate since,
      LocalDate until);
}
