package com.gymbro.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CheckinRequest {
    public String gym;
    public double latitude;
    public double longitude;
    public LocalDateTime timestamp;
}