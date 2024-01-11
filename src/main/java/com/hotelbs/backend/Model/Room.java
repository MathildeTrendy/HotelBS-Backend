package com.hotelbs.backend.Model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Room {

    @Id
    @Column(length = 4)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int roomNumber;
    private int numberOfBeds;
    private LocalDateTime created;
    private LocalDateTime updated;

    @ManyToOne
    @JoinColumn(name = "hotel", referencedColumnName = "id")
    Hotel hotel;

}
