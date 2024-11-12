package org.calorieburn.server.core.health.api;

import lombok.RequiredArgsConstructor;
import org.calorieburn.server.core.health.dto.HealthInfoResponse;
import org.calorieburn.server.core.health.service.HealthInfoService;
import org.calorieburn.server.global.api.ApiResponse;
import org.calorieburn.server.global.common.LoginMember;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HealthInfoController {

    private final HealthInfoService healthInfoService;

    @GetMapping("/health-infos")
    public ResponseEntity<ApiResponse> getHealthInfo(@LoginMember Long memberId) {
        HealthInfoResponse healthInfoResponse = healthInfoService.getHealthInfo(memberId);
        return ResponseEntity.ok(ApiResponse.of(healthInfoResponse));
    }
}
