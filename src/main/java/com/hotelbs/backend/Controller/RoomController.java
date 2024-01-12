package com.hotelbs.backend.Controller;

import com.hotelbs.backend.DTO.HotelWithRoomCountDTO;
import com.hotelbs.backend.Entity.Hotel;
import com.hotelbs.backend.Entity.Room;
import com.hotelbs.backend.Service.HotelService;
import com.hotelbs.backend.Service.RoomService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @PostMapping() // Same as calling POST /api/hotels
    public Room create(
            @RequestParam int hotelId,
            @RequestParam int numberOfBeds
    ) {
        return roomService.createRoomForHotel(hotelId, numberOfBeds);
    }
}
