package com.api.hotelbooking.infra.entrypoint.rest.endpoint;

import com.api.hotelbooking.application.dto.ChangeBookingInputDTO;
import com.api.hotelbooking.application.dto.PlaceReservationDTO;
import com.api.hotelbooking.application.usecase.CancelBooking;
import com.api.hotelbooking.application.usecase.ChangeBooking;
import com.api.hotelbooking.application.usecase.GetAllGuestBookings;
import com.api.hotelbooking.application.usecase.PlaceBooking;
import com.api.hotelbooking.infra.entrypoint.rest.dto.BookingDTO;
import com.api.hotelbooking.infra.entrypoint.rest.dto.ChangeBookingDTO;
import com.api.hotelbooking.infra.entrypoint.rest.dto.PlaceBookingDTO;
import java.util.List;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/booking")
public class BookingEndpoint {

  private PlaceBooking placeBooking;
  private CancelBooking cancelBooking;
  private GetAllGuestBookings getAllGuestBookings;

  private ChangeBooking changeBooking;

  @PostMapping
  public ResponseEntity<?> placeBooking(@RequestBody @Valid PlaceBookingDTO placeBookingDTO) {
    log.info("Placing booking");

    var applicationDto = new PlaceReservationDTO(
        placeBookingDTO.getGuestName(),
        placeBookingDTO.getGuestEmail(),
        placeBookingDTO.getCurrentDay(),
        placeBookingDTO.getCheckin(),
        placeBookingDTO.getCheckout()
    );

    this.placeBooking.execute(applicationDto);

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @GetMapping
  public ResponseEntity<List<BookingDTO>> getAllGuestBookings(
      @RequestParam(name = "guest-email") String guestEmail) {
    log.info("Fetching all guest bookings");

    var guestBookings = this.getAllGuestBookings.execute(guestEmail)
        .stream()
        .map(BookingDTO::of)
        .toList();
    return ResponseEntity.ok(guestBookings);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<String> changeBooking(
      @RequestBody @Valid ChangeBookingDTO changeBookingDTO,
      @PathVariable("id") Long id) {
    log.info("Changing booking");

    var changeBookingInputDto = new ChangeBookingInputDTO(id, changeBookingDTO.getCurrentDay(),
        changeBookingDTO.getCheckin(), changeBookingDTO.getCheckout());

    changeBooking.execute(changeBookingInputDto);

    return ResponseEntity.ok("Booking updated!");
  }

  @DeleteMapping
  public ResponseEntity<?> deleteBooking(@RequestParam Long id) {
    log.info("Deleting booking");

    this.cancelBooking.execute(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
