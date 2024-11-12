package org.calorieburn.server.core.health.dto;

public record ExerciseRequest(
        Long walkingStep,
        Long calorie
) {
}
