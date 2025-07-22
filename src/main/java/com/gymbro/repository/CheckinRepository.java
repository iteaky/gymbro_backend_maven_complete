package com.gymbro.repository;

import com.gymbro.model.Checkin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CheckinRepository extends JpaRepository<Checkin, UUID> {
}