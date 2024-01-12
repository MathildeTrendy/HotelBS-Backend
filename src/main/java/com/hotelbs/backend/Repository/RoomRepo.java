package com.hotelbs.backend.Repository;

import com.hotelbs.backend.Entity.Hotel;
import com.hotelbs.backend.Entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepo extends JpaRepository<Room, Integer> {
    // Custom JPA query to get number of rooms belonging to a hotel
    int countByHotelId(int hotelId);

    List<Room> findByHotelId(int hotelId);

    Optional<Room> findFirstByHotelIdOrderByRoomNumberDesc(int hotelId);
}
