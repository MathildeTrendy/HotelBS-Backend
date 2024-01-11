package com.hotelbs.backend.Repository;

import com.hotelbs.backend.Entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepo extends JpaRepository<Room, Integer> {
    // Custom JPA query to get number of rooms belonging to a hotel
    int countByHotelId(int hotelId);
}
