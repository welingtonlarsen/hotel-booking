package com.api.hotelbooking.infra.db.model;

import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Getter
@Setter
@NoArgsConstructor
@Entity
@SQLDelete(sql = "UPDATE booking_date SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
public class BookingDate {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  LocalDate date;

  @ManyToOne
  @JoinColumn(name = "booking_id", referencedColumnName = "id")
  Booking booking;

  private boolean deleted = Boolean.FALSE;

  public BookingDate(LocalDate date, Booking booking) {
    this.date = date;
    this.booking = booking;
  }

  public boolean isDeleted() {
    return deleted;
  }
}
