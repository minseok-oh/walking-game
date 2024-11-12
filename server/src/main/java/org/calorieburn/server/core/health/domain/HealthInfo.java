package org.calorieburn.server.core.health.domain;

import lombok.Getter;
import org.calorieburn.server.core.health.exception.HealthInfoErrorCode;
import org.calorieburn.server.global.exception.type.ApiException;

@Getter
public class HealthInfo {

    private final Long id;
    private final Long walkingStep;
    private final Long calorie;
    private final Long beforeWeight;
    private final Long afterWeight;
    private final Long memberId;

    public HealthInfo(Long id, Long walkingStep, Long calorie, Long beforeWeight, Long afterWeight, Long memberId) {
        validateWalkingStep(walkingStep);
        validateCalorie(calorie);
        validateBeforeWeight(beforeWeight);
        validateAfterWeight(afterWeight);

        this.id = id;
        this.walkingStep = walkingStep;
        this.calorie = calorie;
        this.beforeWeight = beforeWeight;
        this.afterWeight = afterWeight;
        this.memberId = memberId;
    }

    private void validateWalkingStep(Long walkingStep) {
        if (walkingStep == null || walkingStep < 0) {
            throw new ApiException(HealthInfoErrorCode.H001);
        }
    }

    private void validateCalorie(Long calorie) {
        if (calorie == null || calorie < 0) {
            throw new ApiException(HealthInfoErrorCode.H002);
        }
    }

    private void validateBeforeWeight(Long beforeWeight) {
        if (beforeWeight == null || beforeWeight < 0) {
            throw new ApiException(HealthInfoErrorCode.H003);
        }
    }

    private void validateAfterWeight(Long afterWeight) {
        if (afterWeight == null || afterWeight < 0) {
            throw new ApiException(HealthInfoErrorCode.H004);
        }
    }

    public HealthInfo appendExercise(Long walkingStep, Long calorie) {
        return new HealthInfo(
                this.id,
                this.walkingStep + walkingStep,
                this.calorie + calorie,
                this.beforeWeight,
                this.afterWeight,
                this.memberId
        );
    }

    public HealthInfo updateCurrentWeight(Long currentWeight) {
        return new HealthInfo(
                this.id,
                this.walkingStep,
                this.calorie,
                this.beforeWeight,
                currentWeight,
                this.memberId
        );
    }
}
