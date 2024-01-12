package com.hotelbs.backend.Service;

import com.hotelbs.backend.Entity.Hotel;
import com.hotelbs.backend.Entity.Room;
import com.hotelbs.backend.Repository.HotelRepo;
import com.hotelbs.backend.Repository.RoomRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Optional;
import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class RoomService {

    @Autowired
    private HotelRepo hotelRepo;

    @Autowired
    private RoomRepo roomRepo;

    public Room createRoomForHotel(int hotelId, int numberOfBeds) {
        Optional<Hotel> hotel = hotelRepo.findById(hotelId);

        // Check if hotel exists, if not -> Error
        if (hotel.isEmpty()) {
            throw new EntityNotFoundException("No hotel found with Id: " + id);
        }

        Room roomSet = new Room();
        roomSet.setNumberOfBeds(numberOfBeds);
        //using .get because it is "optional" (we don't know, if the hotel exists)
        roomSet.setHotel(hotel.get());

        System.out.println(getNextAvailableRoomNumber(hotel.get()));
        roomSet.setRoomNumber(
                getNextAvailableRoomNumber(hotel.get())
        );

        roomSet.setCreated(LocalDateTime.now());
        roomSet.setUpdated(LocalDateTime.now());
        return roomRepo.save(roomSet);
    }

    private int getNextAvailableRoomNumber(Hotel hotel) {
        // Find room with the biggest room number for the hotel
        Optional<Room> room = roomRepo.findFirstByHotelIdOrderByRoomNumberDesc(hotel.getId());

        if (room.isEmpty()) {
            return 1;
        }

        // Increase room number by 1
        return room.get().getRoomNumber() + 1;
    }
}
