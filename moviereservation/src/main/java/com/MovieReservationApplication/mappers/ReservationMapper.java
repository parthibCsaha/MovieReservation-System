package com.MovieReservationApplication.mappers;

import com.MovieReservationApplication.dto.ReservationDto;
import com.MovieReservationApplication.entity.Reservation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReservationMapper {

    ReservationDto toDto(Reservation reservation);

}
