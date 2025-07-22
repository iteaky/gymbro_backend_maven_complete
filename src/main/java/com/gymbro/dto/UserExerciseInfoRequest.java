package com.gymbro.dto;

import lombok.Data;

@Data
public class UserExerciseInfoRequest {
    public String userId; // UUID as String
    public Integer squatLegs;
    public Integer benchChest;
    public Integer seatedPressShoulders;
    public Integer deadliftBack;
    public Integer curlBiceps;
    public Integer extensionTriceps;
}
