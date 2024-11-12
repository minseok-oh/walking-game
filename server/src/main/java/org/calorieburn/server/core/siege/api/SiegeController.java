package org.calorieburn.server.core.siege.api;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.calorieburn.server.core.siege.dto.SimpleSiegeInfo;
import org.calorieburn.server.core.siege.service.SiegeService;
import org.calorieburn.server.global.api.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SiegeController {

    private final SiegeService siegeService;

    @GetMapping("/sieges")
    public ResponseEntity<ApiResponse> getSiegeList() {
        List<SimpleSiegeInfo> simpleSiegeInfos = siegeService.getSiegeList();
        return ResponseEntity.ok(ApiResponse.of(simpleSiegeInfos));
    }

    @GetMapping("/sieges/{siegeId}")
    public ResponseEntity<ApiResponse> getSiegeDetail(@PathVariable Long siegeId) {
        return ResponseEntity.ok(ApiResponse.of(siegeService.getSiegeDetail(siegeId)));
    }
}
