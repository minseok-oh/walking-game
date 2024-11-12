package org.calorieburn.server.global.security;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtAuthentication {

    private final Long memberId;
    private final String accessToken;

    public Long getPrincipal() {
        return memberId;
    }

    public String getCredential() {
        return accessToken;
    }

}
