package com.gymbro.service;

import com.gymbro.dto.SessionRequest;
import com.gymbro.model.Session;
import com.gymbro.repository.SessionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SessionService {

    private final SessionRepository sessionRepository;

    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public Session save(Session session) {
        return sessionRepository.save(session);
    }

    public Optional<Session> findById(UUID id) {
        return sessionRepository.findById(id);
    }

    public List<Session> findAll() {
        return sessionRepository.findAll();
    }

    public Session createSession(SessionRequest request) {
        Session session = new Session();
        session.setGym(request.gym);
        session.setTime(request.time);
        session.setMuscleGroups(request.muscleGroups);
        session.setBench(request.bench);
        session.setSquat(request.squat);
        session.setDeadlift(request.deadlift);
        session.setMaxParticipants(request.maxParticipants);
        return sessionRepository.save(session);
    }

    public List<Session> getSessions() {
        return sessionRepository.findAll();
    }

    public Session getSession(UUID id) {
        return sessionRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Сессия не найдена"));
    }

    public String joinSession(UUID id) {
        // Здесь должна быть бизнес-логика присоединения пользователя к сессии
        return "Вы присоединились к сессии " + id;
    }
}