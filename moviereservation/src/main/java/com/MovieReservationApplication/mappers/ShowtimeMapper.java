package com.MovieReservationApplication.mappers;

import com.MovieReservationApplication.dto.ShowtimeDto;
import com.MovieReservationApplication.entity.Showtime;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ShowtimeMapper {

    ShowtimeDto toDto(Showtime showtime);
    List<ShowtimeDto> toDtoList(List<Showtime> showtimes);

}
