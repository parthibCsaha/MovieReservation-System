package com.MovieReservationApplication.dto;

public record CreateShowtimeRequest(
        Long movieId,
        String startTime, // Consider parsing to LocalDateTime later
        String auditorium,
        int totalSeats
) {}