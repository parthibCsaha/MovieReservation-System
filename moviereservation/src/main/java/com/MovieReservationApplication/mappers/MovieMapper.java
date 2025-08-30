package com.MovieReservationApplication.mappers;

import com.MovieReservationApplication.dto.MovieDto;
import com.MovieReservationApplication.entity.Movie;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MovieMapper {

    MovieDto toDto(Movie movie);
    Movie toEntity(MovieDto movieDto);
    List<MovieDto> toDtoList(List<Movie> movies);

}
