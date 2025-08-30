package com.MovieReservationApplication.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateMovieRequest(
        @NotBlank String title,
        @NotBlank String description,
        @NotBlank String posterUrl,
        @NotBlank String genre
) {}