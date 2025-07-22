package com.gymbro.repository;

import com.gymbro.model.UserExerciseInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserExerciseInfoRepository extends JpaRepository<UserExerciseInfo, Long> {
    UserExerciseInfo findByUser_Id(UUID userId);
}
