package org.calorieburn.server.core.health.dto;

public record HealthInfoResponse(
        Long walkingStep,
        Long calorie,
        Long beforeWeight,
        Long afterWeight
) {
}
