package com.gymbro.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.UUID;

@Entity
@Table(name = "USER_EXERCISE_INFO")
@Data
public class UserExerciseInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    private Integer squatLegs;
    private Integer benchChest;
    private Integer seatedPressShoulders;
    private Integer deadliftBack;
    private Integer curlBiceps;
    private Integer extensionTriceps;
}
