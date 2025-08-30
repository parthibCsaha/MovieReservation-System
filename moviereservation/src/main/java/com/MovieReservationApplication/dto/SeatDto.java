package com.MovieReservationApplication.dto;

public record SeatDto(
        Long id,
        String seatNumber,
        boolean isReserved
) {}