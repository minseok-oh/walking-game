package org.calorieburn.server.core.member.api;

import lombok.RequiredArgsConstructor;
import org.calorieburn.server.core.member.dto.SignUpCommandDto;
import org.calorieburn.server.core.member.service.MemberService;
import org.calorieburn.server.global.api.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/auth/sign-up")
    public ResponseEntity<ApiResponse> signUp(@RequestBody SignUpCommandDto signUpCommandDto) {
        memberService.signUp(signUpCommandDto.name(), signUpCommandDto.email(), signUpCommandDto.password(),
                signUpCommandDto.phone(), signUpCommandDto.school());
        return ResponseEntity.ok(ApiResponse.of(null));
    }

}
