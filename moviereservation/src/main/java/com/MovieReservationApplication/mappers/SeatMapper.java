package com.MovieReservationApplication.mappers;

import com.MovieReservationApplication.dto.SeatDto;
import com.MovieReservationApplication.entity.Seat;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SeatMapper {

    SeatDto toDto(Seat seat);
    List<SeatDto> toDtoList(List<Seat> seats);

}