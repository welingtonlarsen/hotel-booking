package com.api.hotelbooking.infra.db.dao;

import com.api.hotelbooking.infra.db.model.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepositoryDAO extends JpaRepository<Guest, String> {

}
