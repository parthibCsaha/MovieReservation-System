package com.MovieReservationApplication.controller;

import com.MovieReservationApplication.dto.CreateMovieRequest;
import com.MovieReservationApplication.dto.MovieDto;
import com.MovieReservationApplication.entity.Movie;
import com.MovieReservationApplication.mappers.MovieMapper;
import com.MovieReservationApplication.service.MovieService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService movieService;
    private final MovieMapper movieMapper;

    public MovieController(MovieService movieService, MovieMapper movieMapper) {
        this.movieService = movieService;
        this.movieMapper = movieMapper;
    }

    // Get all movies with pagination and optional sorting
    @GetMapping
    public Page<MovieDto> getAllMovies(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "title") String sortBy
    ) {
        return movieService.getMovies(PageRequest.of(page, size, Sort.by(sortBy)))
                .map(movieMapper::toDto);
    }

    // Get single movie by id
    @GetMapping("/{id}")
    public MovieDto getMovie(@PathVariable Long id) {
        return movieMapper.toDto(movieService.getMovieById(id));
    }

    // Add new movie (admin only)
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public MovieDto addMovie(@Valid @RequestBody CreateMovieRequest req) {
        Movie movie = Movie.builder()
                .title(req.title())
                .description(req.description())
                .posterUrl(req.posterUrl())
                .genre(req.genre())
                .build();
        return movieMapper.toDto(movieService.addMovie(movie));
    }

    // Delete movie by id (admin only)
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
    }
}
