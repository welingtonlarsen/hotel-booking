package com.api.hotelbooking.infra.db.repository;

import com.api.hotelbooking.application.repository.BookingRepository;
import com.api.hotelbooking.domain.entity.Booking;
import com.api.hotelbooking.domain.factories.BookingFactory;
import com.api.hotelbooking.domain.factories.GuestFactory;
import com.api.hotelbooking.infra.db.dao.BookingDAO;
import com.api.hotelbooking.infra.db.dao.BookingDateDAO;
import com.api.hotelbooking.infra.db.dao.GuestRepositoryDAO;
import com.api.hotelbooking.infra.db.exception.ThereIsNotRegister;
import com.api.hotelbooking.infra.db.model.BookingDate;
import com.api.hotelbooking.infra.db.model.Guest;
import java.util.List;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Slf4j
@Service
public class BookingRepositorySQL implements BookingRepository {

  private BookingDAO bookingDAO;
  private BookingDateDAO bookingDateDAO;
  private GuestRepositoryDAO guestRepositoryDAO;

  @Override
  @Transactional
  public void insert(Booking booking) {
    log.info("Inserting Booking in database");

    var guestModel = guestRepositoryDAO
        .findById(booking.getGuestEmail())
        .orElse(new Guest(booking.getGuestEmail(), booking.getGuestName()));

    var persistedBookingDAO = bookingDAO.save(
        new com.api.hotelbooking.infra.db.model.Booking(guestModel, booking.getCheckin(),
            booking.getCheckout()));

    var bookingDateModels = booking.getDates()
        .stream()
        .map(bookingDate -> new BookingDate(bookingDate.getDate(), persistedBookingDAO))
        .toList();

    bookingDateDAO.saveAll(bookingDateModels);

    log.info("Booking was successful inserted in database");
  }

  @Override
  @Transactional
  public void delete(Long id) {
    log.info("Deleting booking from database");
    this.bookingDAO.deleteById(id);
    log.info("Booking was successful deleted from database");
  }

  @Override
  public Booking findById(Long bookingId) {
    log.info("Finding booking by id in database");

    var bookingModel = this.bookingDAO.findById(bookingId)
        .orElseThrow(() -> new ThereIsNotRegister("There is not booking to id: " + bookingId));

    var guestModel = this.guestRepositoryDAO.findById(bookingModel.getGuest().getEmail())
        .orElseThrow(() -> new ThereIsNotRegister(
            "There is not guest related to booking id: " + bookingId));

    var guest = GuestFactory.of(guestModel.getName(), guestModel.getEmail());
    var booking = BookingFactory.of(guest, bookingModel.getCheckin(), bookingModel.getCheckout());
    booking.setId(bookingModel.getId());

    log.info("Booking was find in database");
    return booking;
  }

  @Override
  @Transactional
  public List<Booking> findAll(String guestEmail) {
    log.info("Finding all bookings by guest email in database");

    var guestModel = this.guestRepositoryDAO.findById(guestEmail)
        .orElseThrow(() -> new ThereIsNotRegister("There is not bookings to guest: " + guestEmail));
    var bookingModels = this.bookingDAO.findByGuest(guestModel);

    log.info("All bookings for guest email were found in database");

    return bookingModels.stream().map(model -> {
      var guestEntity = GuestFactory.of(model.getGuest().getName(), model.getGuest().getEmail());
      var bookingEntity = BookingFactory.of(guestEntity, model.getCheckin(), model.getCheckout());
      bookingEntity.setId(model.getId());
      return bookingEntity;
    }).toList();
  }

  @Override
  @Transactional
  public void replace(Booking oldBooking, Booking newBooking) {
    log.info("Replacing old booking by new booking in database");

    this.bookingDAO.deleteById(oldBooking.getId());
    this.insert(newBooking);

    log.info("Old booking was replaced by new booking in database");
  }
}
