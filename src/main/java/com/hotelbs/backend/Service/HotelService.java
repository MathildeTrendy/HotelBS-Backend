package com.hotelbs.backend.Service;

import com.hotelbs.backend.DTO.HotelWithRoomCountDTO;
import com.hotelbs.backend.Entity.Hotel;
import com.hotelbs.backend.Repository.HotelRepo;
import com.hotelbs.backend.Repository.RoomRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HotelService {

    @Autowired
    private HotelRepo hotelRepo;

    @Autowired
    private RoomRepo roomRepo;

    public Hotel create(String name, String street, String city, String country, String zip) {
        Hotel hotelSet = new Hotel();
        hotelSet.setName(name);
        hotelSet.setStreet(street);
        hotelSet.setCity(city);
        hotelSet.setCountry(country);
        hotelSet.setZip(zip);
        hotelSet.setCreated(LocalDateTime.now());
        hotelSet.setUpdated(LocalDateTime.now());
       return hotelRepo.save(hotelSet);
    }

    public List<Hotel> getHotels() {
        return hotelRepo.findAll();
    }

    public List<HotelWithRoomCountDTO> getHotelsWithRoomCounts() {
        List<Hotel> hotels = hotelRepo.findAll();

        // Map each hotel and add a roomCount using the RoomRepository countByHotelId query
        return hotels.stream()
                .map(hotel -> {
                    int roomCount = roomRepo.countByHotelId(hotel.getId());
                    return new HotelWithRoomCountDTO(hotel, roomCount);
                })
                .collect(Collectors.toList());
    }

    public HotelWithRoomCountDTO getHotelWithRoomCount(int id) {
        Optional<Hotel> hotel = hotelRepo.findById(id);

        if (hotel.isEmpty()) {
            throw new EntityNotFoundException("No hotel found with Id: " + id);
        }

        int roomCount = roomRepo.countByHotelId(hotel.get().getId());
        return new HotelWithRoomCountDTO(hotel.get(), roomCount);
    }
}
