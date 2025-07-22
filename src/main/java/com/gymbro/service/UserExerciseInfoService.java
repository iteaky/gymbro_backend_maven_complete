package com.gymbro.service;

import com.gymbro.dto.UserExerciseInfoRequest;
import com.gymbro.model.User;
import com.gymbro.model.UserExerciseInfo;
import com.gymbro.repository.UserExerciseInfoRepository;
import com.gymbro.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserExerciseInfoService {
    private final UserExerciseInfoRepository userExerciseInfoRepository;
    private final UserRepository userRepository;

    public UserExerciseInfoService(UserExerciseInfoRepository userExerciseInfoRepository, UserRepository userRepository) {
        this.userExerciseInfoRepository = userExerciseInfoRepository;
        this.userRepository = userRepository;
    }

    public UserExerciseInfo save(UserExerciseInfoRequest request) {
        User user = userRepository.findById(UUID.fromString(request.userId)).orElseThrow(() -> new IllegalArgumentException("User not found"));
        UserExerciseInfo info = new UserExerciseInfo();
        info.setUser(user);
        info.setSquatLegs(request.squatLegs);
        info.setBenchChest(request.benchChest);
        info.setSeatedPressShoulders(request.seatedPressShoulders);
        info.setDeadliftBack(request.deadliftBack);
        info.setCurlBiceps(request.curlBiceps);
        info.setExtensionTriceps(request.extensionTriceps);
        return userExerciseInfoRepository.save(info);
    }

    public UserExerciseInfo getByUserId(UUID userId) {
        return userExerciseInfoRepository.findByUser_Id(userId);
    }
}
