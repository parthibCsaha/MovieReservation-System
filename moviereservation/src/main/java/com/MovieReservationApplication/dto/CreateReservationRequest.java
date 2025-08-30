package com.MovieReservationApplication.dto;

import java.util.List;

public record CreateReservationRequest(
        Long showtimeId,
        List<String> seatNumbers,
        double pricePerSeat
) {}