package com.api.hotelbooking.infra.db.dao;

import com.api.hotelbooking.infra.db.model.Booking;
import com.api.hotelbooking.infra.db.model.Guest;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingDAO extends JpaRepository<Booking, Long> {

  List<Booking> findByGuest(Guest guestEmail);
}
