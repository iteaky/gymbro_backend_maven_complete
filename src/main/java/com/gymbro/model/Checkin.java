package com.gymbro.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "CHECKINS")
public class Checkin {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID id;

    public String gym;
    public double latitude;
    public double longitude;
    public LocalDateTime timestamp;

    @ManyToOne
    public User user;
}