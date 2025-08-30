package com.MovieReservationApplication.dto;

import java.time.LocalDateTime;

public record ShowtimeDto(
        Long id,
        Long movieId,
        LocalDateTime startTime,
        String auditorium,
        int totalSeats
) {}