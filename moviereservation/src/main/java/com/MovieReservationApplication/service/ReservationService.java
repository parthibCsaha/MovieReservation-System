package com.MovieReservationApplication.service;

import com.MovieReservationApplication.dto.ReservationDto;
import com.MovieReservationApplication.entity.*;
import com.MovieReservationApplication.repo.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final SeatRepository seatRepository;
    private final ShowtimeRepository showtimeRepository;
    private final ReservationSeatRepository reservationSeatRepository;

    public ReservationService(ReservationRepository reservationRepository, UserRepository userRepository, SeatRepository seatRepository, ShowtimeRepository showtimeRepository, ReservationSeatRepository reservationSeatRepository) {
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
        this.seatRepository = seatRepository;
        this.showtimeRepository = showtimeRepository;
        this.reservationSeatRepository = reservationSeatRepository;
    }

    @Transactional(rollbackFor = Exception.class)
    public Reservation reserveSeats(Long userId, Long showtimeId, List<String> seatNumbers, double pricePerSeat) {
        if (seatNumbers == null || seatNumbers.isEmpty()) {
            throw new IllegalArgumentException("Seat numbers cannot be empty");
        }
        if (pricePerSeat <= 0) {
            throw new IllegalArgumentException("Price per seat should be greater than 0");
        }
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found"));
        Showtime showtime = showtimeRepository.findById(showtimeId)
                .orElseThrow(() -> new NoSuchElementException("Showtime not found"));
        List<Seat> seats = seatRepository.findAvailableSeatsForUpdate(showtimeId, seatNumbers);
        if (seats.size() < seatNumbers.size()) {
            throw new IllegalArgumentException("Seat numbers do not match");
        }
        seats.forEach(seat -> seat.setReserved(true));
        seatRepository.saveAll(seats);
        Reservation reservation = Reservation.builder()
                .user(user)
                .showtime(showtime)
                .totalPrice(pricePerSeat * seatNumbers.size())
                .build();
        reservationRepository.save(reservation);
        List<ReservationSeat> reservationSeats = seats.stream()
                .map(seat -> ReservationSeat.builder()
                        .reservation(reservation)
                        .seat(seat)
                        .build())
                .toList();
        reservationSeatRepository.saveAll(reservationSeats);
        return reservation;
    }

    public List<ReservationDto> getUserReservations(Long userId) {
        List<Reservation> reservations = reservationRepository.findByUserId(userId);
        return reservations.stream()
                .map(res -> new ReservationDto(
                        res.getId(),
                        res.getShowtime().getId(),
                        res.getSeatNumbers(),
                        res.getTotalPrice(),
                        res.getReservedAt()
                ))
                .collect(Collectors.toList());
    }

}
