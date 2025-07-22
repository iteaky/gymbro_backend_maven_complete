package com.gymbro.controller;

import com.gymbro.dto.*;
import com.gymbro.model.Session;
import com.gymbro.model.UserExerciseInfo;
import com.gymbro.service.UserService;
import com.gymbro.service.SessionService;
import com.gymbro.service.UserExerciseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class GymBroController {
    private final UserService userService;
    private final SessionService sessionService;
    private final UserExerciseInfoService userExerciseInfoService;

    @Autowired
    public GymBroController(UserService userService, SessionService sessionService, UserExerciseInfoService userExerciseInfoService) {
        this.userService = userService;
        this.sessionService = sessionService;
        this.userExerciseInfoService = userExerciseInfoService;
    }

    @PostMapping("/auth/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        try {
            userService.register(request.email, request.password, request.name);
            return ResponseEntity.status(201).body("Пользователь зарегистрирован");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(409).body(e.getMessage());
        }
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            UUID login = userService.login(request.email, request.password);
            // Здесь должен быть реальный JWT, сейчас просто заглушка
            return ResponseEntity.ok(login);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

    @PostMapping("/sessions")
    public ResponseEntity<?> createSession(@RequestBody SessionRequest request) {
        sessionService.createSession(request);
        return ResponseEntity.status(201).body("Сессия создана");
    }

    @GetMapping("/sessions")
    public ResponseEntity<List<Session>> getSessions(
        @RequestParam(name = "gym", required = false) String gym,
        @RequestParam(name = "date", required = false) String date,
        @RequestParam(name = "muscleGroup", required = false) String muscleGroup) {
        List<Session> sessions = sessionService.getSessions();
        return ResponseEntity.ok(sessions);
    }

    @GetMapping("/sessions/{id}")
    public ResponseEntity<?> getSession(@PathVariable String id) {
        try {
            Session session = sessionService.getSession(UUID.fromString(id));
            return ResponseEntity.ok(session);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @PostMapping("/sessions/{id}/join")
    public ResponseEntity<?> joinSession(@PathVariable String id) {
        String result = sessionService.joinSession(UUID.fromString(id));
        return ResponseEntity.ok(result);
    }

    @PostMapping("/checkin")
    public ResponseEntity<?> checkin(@RequestBody CheckinRequest request) {
        return ResponseEntity.status(201).body("Checked in");
    }

    @GetMapping("/match/suggestions")
    public ResponseEntity<List<String>> matchSuggestions() {
        return ResponseEntity.ok(List.of("UserA", "UserB"));
    }

    @PostMapping("/user-exercise-info")
    public ResponseEntity<UserExerciseInfo> saveUserExerciseInfo(@RequestBody UserExerciseInfoRequest request) {
        UserExerciseInfo info = userExerciseInfoService.save(request);
        return ResponseEntity.status(201).body(info);
    }

    @GetMapping("/user-exercise-info/{userId}")
    public ResponseEntity<UserExerciseInfo> getUserExerciseInfo(@PathVariable(name = "userId") UUID userId) {
        System.out.printf("Fetching UserExerciseInfo for userId: %s%n", userId);
        UserExerciseInfo info = userExerciseInfoService.getByUserId(userId);
        System.out.printf("UserExerciseInfo for userId %s: %s%n", userId, info);
        if (info == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(info);
    }
}