package com.hotelbs.backend.Controller;

import com.hotelbs.backend.DTO.HotelWithRoomCountDTO;
import com.hotelbs.backend.Entity.Hotel;
import com.hotelbs.backend.Service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @PostMapping() // Same as calling POST /api/hotels
    public Hotel create(
        @RequestParam String name,
        @RequestParam String street,
        @RequestParam String city,
        @RequestParam String country,
        @RequestParam String zip
    ) {
        return hotelService.create(name, street, city, country, zip);
    }

    @GetMapping()
    public List<HotelWithRoomCountDTO> getHotels() {
        return hotelService.getHotelsWithRoomCounts();
    }

    @GetMapping("/{id}")
    public HotelWithRoomCountDTO getHotelById(@PathVariable int id) {
        return hotelService.getHotelWithRoomCount(id);
    }
}
