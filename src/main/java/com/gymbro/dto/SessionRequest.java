package com.gymbro.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class SessionRequest {
    public String gym;
    public LocalDateTime time;
    public List<String> muscleGroups;
    public Integer bench;
    public Integer squat;
    public Integer deadlift;
    public Integer maxParticipants;
}