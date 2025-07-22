package com.gymbro.model;

import jakarta.persistence.*;
import java.util.UUID;
import lombok.Data;

@Entity
@Table(name = "USERS")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID")
    public UUID id;

    @Column(name = "EMAIL")
    public String email;
    @Column(name = "PASSWORD")
    public String password;
    @Column(name = "NAME")
    public String name;
}