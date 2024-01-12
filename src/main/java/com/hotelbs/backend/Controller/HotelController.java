package com.hotelbs.backend.Controller;

import com.hotelbs.backend.DTO.HotelWithRoomCountDTO;
import com.hotelbs.backend.Entity.Hotel;
import com.hotelbs.backend.Service.HotelService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @GetMapping()
    public List<HotelWithRoomCountDTO> getHotels() {
        return hotelService.getHotelsWithRoomCounts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getHotelById(@PathVariable int id) {
        try {
            return ResponseEntity.ok(
                    hotelService.getHotelById(id)
            );
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<Object> getAllHotelDetailsById(@PathVariable int id) {
        try {
            return ResponseEntity.ok(
                    hotelService.getAllHotelDetailsById(id)
            );
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

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

    @PutMapping("/{id}") // Same as calling PUT /api/hotels/{id}
    public ResponseEntity<Object> update(
            @PathVariable int id,
            @RequestParam(required = false) Optional<String> name,
            @RequestParam(required = false) Optional<String> street,
            @RequestParam(required = false) Optional<String> city,
            @RequestParam(required = false) Optional<String> country,
            @RequestParam(required = false) Optional<String> zip
    ) {
        try {
            return ResponseEntity.ok(
                hotelService.update(id, name, street, city, country, zip)
            );
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteHotel(@PathVariable int id) {
        try {
            return ResponseEntity.ok(
                hotelService.deleteHotel(id)
            );
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
