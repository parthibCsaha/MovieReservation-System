package com.MovieReservationApplication;


import com.MovieReservationApplication.entity.User;
import com.MovieReservationApplication.repo.UserRepository;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class MovieReservationApplication {
    public static void main(String[] args) {
        SpringApplication.run(MovieReservationApplication.class, args);
    }

    @Bean
    public CommandLineRunner seedAdmin(UserRepository userRepository, PasswordEncoder encoder) {
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()) {
                User admin = User.builder()
                        .username("admin")
                        .password(encoder.encode("admin123"))
                        .email("admin@example.com")
                        .role(User.Role.ADMIN)
                        .build();
                userRepository.save(admin);
            }
        };
    }
}