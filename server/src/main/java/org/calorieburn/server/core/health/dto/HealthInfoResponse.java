package org.calorieburn.server.core.health.dto;

public record HealthInfoResponse(
        Long workingStep,
        Long calorie,
        Long beforeWeight,
        Long afterWeight
) {
}
