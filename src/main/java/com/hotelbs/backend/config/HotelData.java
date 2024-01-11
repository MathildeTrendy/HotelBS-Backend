package com.hotelbs.backend.config;

import com.github.javafaker.Faker;
import com.hotelbs.backend.Entity.Hotel;
import com.hotelbs.backend.Entity.Room;
import com.hotelbs.backend.Repository.GuestRepo;
import com.hotelbs.backend.Repository.HotelRepo;
import com.hotelbs.backend.Repository.ReservationRepo;
import com.hotelbs.backend.Repository.RoomRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Random;

@Component
public class HotelData implements CommandLineRunner {

    GuestRepo guestRepo;
    HotelRepo hotelRepo;
    ReservationRepo reservationRepo;
    RoomRepo roomRepo;

    public HotelData(GuestRepo guestRepo, HotelRepo hotelRepo, ReservationRepo reservationRepo, RoomRepo roomRepo) {
        this.guestRepo = guestRepo;
        this.hotelRepo = hotelRepo;
        this.reservationRepo = reservationRepo;
        this.roomRepo = roomRepo;
    }

    @Override
    public void run(String... args) {
        Faker faker = new Faker();

        // 250 times
        for (int i = 1; i <= 250; i++) {
            // Create a Hotel
            Hotel hotelSet = new Hotel();
            hotelSet.setName(faker.company().name());
            hotelSet.setStreet(faker.address().streetAddress());
            hotelSet.setCity(faker.address().cityName());
            hotelSet.setCountry("Danmark");
            hotelSet.setZip(faker.address().zipCode());
            hotelSet.setCreated(LocalDateTime.now());
            hotelSet.setUpdated(LocalDateTime.now());
            Hotel createdHotel = hotelRepo.save(hotelSet);

            // For each Hotel create a random amount of rooms, 10-40 pcs.
            for (int j = 1; j <= faker.random().nextInt(10, 40); j++) {
                // Create a Room
                Room roomSet = new Room();
                roomSet.setRoomNumber(String.valueOf(j));

                // For each room, add between 1 and 4 beds randomly
                roomSet.setNumberOfBeds(faker.random().nextInt(1, 4));

                // Set the room to belong to the hotel
                roomSet.setHotel(createdHotel);

                roomSet.setCreated(LocalDateTime.now());
                roomSet.setUpdated(LocalDateTime.now());

                Room createdRoom = roomRepo.save(roomSet);
            }
        }
    }
}
