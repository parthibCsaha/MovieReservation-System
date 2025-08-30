package com.MovieReservationApplication.dto;

import java.time.LocalDateTime;

public record MovieDto(
        Long id,
        String title,
        String description,
        String posterUrl,
        String genre,
        LocalDateTime createdAt
) {}