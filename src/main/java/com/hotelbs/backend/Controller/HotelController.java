package com.hotelbs.backend.Controller;

import com.hotelbs.backend.DTO.HotelWithRoomCountDTO;
import com.hotelbs.backend.Entity.Hotel;
import com.hotelbs.backend.Service.HotelService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Object> getHotelById(@PathVariable int id) {
        try {
            return ResponseEntity.ok(
                hotelService.getHotelWithRoomCount(id)
            );
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
