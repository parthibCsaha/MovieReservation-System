# Movie Reservation System 

This project is a RESTful backend for a **Movie Reservation System** built with **Spring Boot**, **Spring Data JPA**, **Spring Security with JWT**, and **PostgreSQL**. It enables users to browse movies, view showtimes, and reserve seats securely.  

## Features

- **User Authentication:**
  - **Registration & Login:** Users register and log in to obtain a JWT token.
  - **Role-based Access:** Default role is **USER**, while **ADMIN** has elevated privileges.
- **Movie Management:**
  - **Public Endpoints:** View movies and showtimes.
  - **Admin Endpoints:** Create, update, and delete movies and showtimes.
- **Seat Reservation:**
  - Users can select available seats for a showtime and reserve them.
  - Seat locking ensures no double booking.
- **Reservation Management:**
  - Users can view their past and upcoming reservations.
  - Admins can view all reservations.
- **Additional Modules (for future extension):**
  - Payment Integration.
  - Discount Coupons.
  - Notifications (Email/SMS).
  - Seat Recommendation Engine.

---

## Architecture & Entity Relationships

The project follows a layered architecture with controllers, services, repositories, DTOs, and mappers.  

### UML Diagram

```mermaid
classDiagram
    class User {
        +Long id
        +String username
        +String password
        +String email
        +String role
    }

    class Movie {
        +Long id
        +String title
        +String description
        +int duration
    }

    class Showtime {
        +Long id
        +LocalDateTime startTime
        +LocalDateTime endTime
    }

    class Seat {
        +Long id
        +String seatNumber
        +boolean reserved
    }

    class Reservation {
        +Long id
        +Double totalPrice
        +LocalDateTime createdAt
    }

    class ReservationSeat {
        +Long id
    }

    %% Relationships
    User "1" -- "many" Reservation : makes >
    Movie "1" -- "many" Showtime : has >
    Showtime "1" -- "many" Seat : contains >
    Reservation "1" -- "many" ReservationSeat : includes >
    Seat "1" -- "many" ReservationSeat : booked in >
    Showtime "1" -- "many" Reservation : linked >
