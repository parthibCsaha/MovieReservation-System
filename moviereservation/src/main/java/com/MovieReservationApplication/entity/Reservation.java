package com.MovieReservationApplication.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Showtime showtime;

    @ElementCollection
    private List<String> seatNumbers;

    private LocalDateTime reservedAt;

    private double totalPrice;

    @PrePersist
    public void prePersist() {
        reservedAt = LocalDateTime.now();
    }
}
