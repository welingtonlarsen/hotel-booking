package com.api.hotelbooking.infra.db.model;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Getter
@Setter
@NoArgsConstructor
@Entity
@SQLDelete(sql = "UPDATE booking SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
public class Booking {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private LocalDate checkin;

  private LocalDate checkout;

  @ManyToOne(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "guest_email", referencedColumnName = "email")
  private Guest guest;

  @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "booking")
  private List<BookingDate> bookingDate;

  private boolean deleted = Boolean.FALSE;

  public Booking(Guest guest, LocalDate checkin, LocalDate checkout) {
    this.guest = guest;
    this.checkin = checkin;
    this.checkout = checkout;
  }

  // TODO: verify is possible to remove
  public boolean isDeleted() {
    return deleted;
  }
}
