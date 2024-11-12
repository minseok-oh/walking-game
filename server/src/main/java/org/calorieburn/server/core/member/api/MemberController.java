package org.calorieburn.server.core.member.api;

import lombok.RequiredArgsConstructor;
import org.calorieburn.server.core.member.dto.MemberInfoResponse;
import org.calorieburn.server.core.member.service.MemberService;
import org.calorieburn.server.global.api.ApiResponse;
import org.calorieburn.server.global.common.LoginMember;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members")
    public ResponseEntity<ApiResponse> getMemberInfo(@LoginMember Long memberId) {
        MemberInfoResponse memberInfo = memberService.getMemberInfo(memberId);
        return ResponseEntity.ok(ApiResponse.of(memberInfo));
    }
}
