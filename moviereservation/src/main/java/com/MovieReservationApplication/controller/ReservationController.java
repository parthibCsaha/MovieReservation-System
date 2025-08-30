package com.MovieReservationApplication.controller;

import com.MovieReservationApplication.dto.CreateReservationRequest;
import com.MovieReservationApplication.dto.ReservationDto;
import com.MovieReservationApplication.entity.Reservation;
import com.MovieReservationApplication.service.ReservationService;
import com.MovieReservationApplication.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reservation")
public class ReservationController {

    private final ReservationService reservationService;
    private final UserService userService;

    public ReservationController(ReservationService reservationService, UserService userService) {
        this.reservationService = reservationService;
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReservationDto reserveSeats(@Valid @RequestBody CreateReservationRequest req, @AuthenticationPrincipal UserDetails userDetails) {
        Long userId = userService.getUserIdByUsername(userDetails.getUsername());
        Reservation reservation = reservationService.reserveSeats(
                userId, req.showtimeId(), req.seatNumbers(), req.pricePerSeat()
        );
        return mapToDto(reservation);
    }

    @GetMapping("/my")
    public List<ReservationDto> getMyReservations(@AuthenticationPrincipal UserDetails userDetails) {
        Long userId = userService.getUserIdByUsername(userDetails.getUsername());
        return reservationService.getUserReservations(userId);
    }

    private ReservationDto mapToDto(Reservation reservation) {
        return new ReservationDto(
                reservation.getId(),
                reservation.getShowtime().getId(),
                reservation.getSeatNumbers(),
                reservation.getTotalPrice(),
                reservation.getReservedAt()
        );
    }

}
