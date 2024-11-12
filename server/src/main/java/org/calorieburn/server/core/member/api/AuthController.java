package org.calorieburn.server.core.member.api;

import lombok.RequiredArgsConstructor;
import org.calorieburn.server.core.member.dto.SignInRequest;
import org.calorieburn.server.core.member.dto.SignInResponse;
import org.calorieburn.server.core.member.dto.SignUpRequest;
import org.calorieburn.server.core.member.service.AuthService;
import org.calorieburn.server.global.api.ApiResponse;
import org.calorieburn.server.global.common.LoginMember;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/auth/sign-up")
    public ResponseEntity<ApiResponse> signUp(@RequestBody SignUpRequest signUpRequest) {
        authService.signUp(signUpRequest.name(), signUpRequest.email(), signUpRequest.password(),
                signUpRequest.phone(), signUpRequest.school());
        return ResponseEntity.ok(ApiResponse.of(null));
    }

    @PostMapping("/auth/sign-in")
    public ResponseEntity<ApiResponse> signIn(@RequestBody SignInRequest signInRequest) {
        String accessToken = authService.signIn(signInRequest.email(), signInRequest.password());
        return ResponseEntity.ok(ApiResponse.of(new SignInResponse(accessToken)));
    }

    @GetMapping("/auth/check")
    public ResponseEntity<ApiResponse> check(@LoginMember Long memberId) {
        return ResponseEntity.ok(ApiResponse.of(memberId));
    }
}
