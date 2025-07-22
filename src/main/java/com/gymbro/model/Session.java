package com.gymbro.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "SESSIONS")
@Data
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID")
    public UUID id;

    @Column(name = "GYM")
    public String gym;
    @Column(name = "TIME")
    public LocalDateTime time;

    @ElementCollection
    @Column(name = "MUSCLE_GROUPS")
    public List<String> muscleGroups;

    @Column(name = "BENCH")
    public Integer bench;
    @Column(name = "SQUAT")
    public Integer squat;
    @Column(name = "DEADLIFT")
    public Integer deadlift;
    @Column(name = "MAX_PARTICIPANTS")
    public Integer maxParticipants;

    @ManyToOne
    @JoinColumn(name = "CREATOR_ID")
    public User creator;
}