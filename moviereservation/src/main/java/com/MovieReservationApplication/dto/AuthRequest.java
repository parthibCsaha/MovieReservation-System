package com.MovieReservationApplication.dto;

public record AuthRequest(
        String username,
        String password
) {}