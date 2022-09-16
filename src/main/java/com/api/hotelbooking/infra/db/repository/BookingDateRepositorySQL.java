package com.api.hotelbooking.infra.db.repository;

import com.api.hotelbooking.application.repository.BookingDateRepository;
import com.api.hotelbooking.domain.entity.BookingDate;
import com.api.hotelbooking.domain.factories.BookingDateFactory;
import com.api.hotelbooking.infra.db.dao.BookingDateDAO;
import java.time.LocalDate;
import java.util.List;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Slf4j
@Service
public class BookingDateRepositorySQL implements BookingDateRepository {

  private BookingDateDAO bookingDateDAO;

  @Override
  @Transactional
  public List<BookingDate> findBetween(LocalDate init, LocalDate end) {
    log.info("Finding booking dates in database");

    var bookingDateModels =
        bookingDateDAO.findOneByDateGreaterThanEqualAndDateLessThanEqual(init, end);

    log.info("Booking dates was found in database");
    return bookingDateModels.stream()
        .map(model -> BookingDateFactory.of(model.getDate()))
        .toList();
  }
}
