package com.api.hotelbooking.infra.db.model;

import java.util.List;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@SQLDelete(sql = "UPDATE guest SET deleted = true WHERE email=?")
@Where(clause = "deleted = false")
public class Guest {

  @Id
  @NotNull
  private String email;
  @NotNull
  private String name;

  @OneToMany(mappedBy = "guest")
  private List<Booking> booking;

  public List<Booking> getBooking() {
    return booking;
  }

  private boolean deleted = Boolean.FALSE;

  public Guest(String email, String name) {
    this.email = email;
    this.name = name;
  }

  public boolean isDeleted() {
    return deleted;
  }
}
