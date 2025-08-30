package com.MovieReservationApplication.repo;

import com.MovieReservationApplication.entity.Seat;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SeatRepository extends JpaRepository<Seat, Long> {

    List<Seat> findByShowtimeId(Long showtimeId);

    List<Seat> findByShowtimeIdAndIsReservedFalse(Long showtimeId);

    Optional<Seat> findByShowtimeIdAndSeatNumber(Long showtimeId, String seatNumber);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT s FROM Seat s WHERE s.showtime.id = :showtimeId AND s.seatNumber IN :seatNumbers AND s.isReserved = false")
    List<Seat> findAvailableSeatsForUpdate(@Param("showtimeId") Long showtimeId, @Param("seatNumbers") List<String> seatNumbers);

}
