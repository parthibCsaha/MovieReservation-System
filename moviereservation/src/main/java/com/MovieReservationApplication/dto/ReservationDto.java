package com.MovieReservationApplication.dto;

import java.time.LocalDateTime;
import java.util.List;

public record ReservationDto(
        Long id,
        Long showtimeId,
        List<String> seatNumbers,
        double totalPrice,
        LocalDateTime reservedAt
) {}