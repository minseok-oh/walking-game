package org.calorieburn.server.global.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import javax.crypto.SecretKey;
import lombok.extern.slf4j.Slf4j;
import org.calorieburn.server.core.member.domain.Member;
import org.calorieburn.server.core.member.exception.MemberErrorCode;
import org.calorieburn.server.global.exception.type.ApiException;

@Slf4j
public class JwtProvider {

    private final String issuer;
    private final int expirySeconds;
    private final SecretKey secretKey;
    private final JwtParser jwtParser;

    public JwtProvider(String issuer, int expirySeconds, String secret) {
        this.issuer = issuer;
        this.expirySeconds = expirySeconds;
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.jwtParser = Jwts.parser().verifyWith(secretKey).build();
    }

    public Long parseAccessToken(String accessToken) {
        try {
            Claims payload = jwtParser.parseSignedClaims(accessToken).getPayload();
            return Long.parseLong(payload.getSubject());
        } catch (ExpiredJwtException e) {
            throw new ApiException(MemberErrorCode.M007);
        } catch (RuntimeException e) {
            log.debug("액세스 토큰이 유효하지 않습니다. token={}", accessToken);
            throw new ApiException(MemberErrorCode.M007);
        }
    }

    public String createAccessToken(Member member) {
        Date now = new Date();
        Date expiresAt = new Date(now.getTime() + expirySeconds * 1000L);
        return Jwts.builder()
                .issuer(issuer)
                .issuedAt(now)
                .subject(String.valueOf(member.getId()))
                .expiration(expiresAt)
                .signWith(secretKey)
                .compact();
    }
}
