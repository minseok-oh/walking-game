package org.calorieburn.server.core.member.api;

import lombok.RequiredArgsConstructor;
import org.calorieburn.server.core.member.dto.SignInRequestDto;
import org.calorieburn.server.core.member.dto.SignInResponseDto;
import org.calorieburn.server.core.member.dto.SignUpRequestDto;
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
    public ResponseEntity<ApiResponse> signUp(@RequestBody SignUpRequestDto signUpRequestDto) {
        authService.signUp(signUpRequestDto.name(), signUpRequestDto.email(), signUpRequestDto.password(),
                signUpRequestDto.phone(), signUpRequestDto.school());
        return ResponseEntity.ok(ApiResponse.of(null));
    }

    @PostMapping("/auth/sign-in")
    public ResponseEntity<ApiResponse> signIn(@RequestBody SignInRequestDto signInRequestDto) {
        String accessToken = authService.signIn(signInRequestDto.email(), signInRequestDto.password());
        return ResponseEntity.ok(ApiResponse.of(new SignInResponseDto(accessToken)));
    }

    @GetMapping("/auth/check")
    public ResponseEntity<ApiResponse> check(@LoginMember Long memberId) {
        return ResponseEntity.ok(ApiResponse.of(memberId));
    }
}
