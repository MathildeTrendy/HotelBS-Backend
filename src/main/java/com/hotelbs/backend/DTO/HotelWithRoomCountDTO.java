package com.hotelbs.backend.DTO;

import com.hotelbs.backend.Entity.Hotel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HotelWithRoomCountDTO {
    private final int id;
    private final String name;
    private final String street;
    private final int roomCount;

    public HotelWithRoomCountDTO(Hotel hotel, int roomCount) {
        this.id = hotel.getId();
        this.name = hotel.getName();
        this.street = hotel.getStreet();
        this.roomCount = roomCount;
    }
}
