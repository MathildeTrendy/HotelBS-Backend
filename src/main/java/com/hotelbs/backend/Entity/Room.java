package com.hotelbs.backend.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
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
    @JoinColumn(name = "hotel_id", referencedColumnName = "id")
    Hotel hotel;

}
