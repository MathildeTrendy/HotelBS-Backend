package com.hotelbs.backend.Model;

import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDateTime reservationDate;
    private LocalDateTime created;
    private LocalDateTime updated;

    @ManyToOne
    @JoinColumn(name = "room", referencedColumnName = "id")
    Room room;


    @ManyToOne
    @JoinColumn(name = "guest", referencedColumnName = "id")
    Guest guest;
}
