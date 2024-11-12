package org.calorieburn.server.global.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.calorieburn.server.core.member.exception.MemberErrorCode;
import org.calorieburn.server.global.exception.type.ApiException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@RequiredArgsConstructor
public class AuthenticationFilter extends OncePerRequestFilter {

    private static final String HEADER = "Authorization";
    private static final String BEARER = "Bearer ";

    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        log.debug("[auth] JWT 인증 인터셉터 시작");
        String bearerAccessToken = request.getHeader(HEADER);
        if (Objects.nonNull(bearerAccessToken)) {
            log.debug("[auth] JWT 인증 프로세스 시작");
            String accessToken = removeBearer(bearerAccessToken);
            JwtAuthentication jwtAuthentication = authenticate(accessToken);
            UsernamePasswordAuthenticationToken authentication =
                    UsernamePasswordAuthenticationToken.authenticated(
                            jwtAuthentication.getPrincipal(),
                            accessToken,
                            null);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            log.debug(
                    "[auth] JWT 인증 프로세스 종료. 사용자 인증됨. principal={}",
                    jwtAuthentication.getPrincipal());
        }
        log.debug("[auth] Jwt 인증 인터셉터 종료");
        filterChain.doFilter(request, response);
    }

    private String removeBearer(String bearerAccessToken) {
        if (bearerAccessToken.contains(BEARER)) {
            return bearerAccessToken.replace(BEARER, "");
        }
        throw new ApiException(MemberErrorCode.M007);
    }

    private JwtAuthentication authenticate(String accessToken) {
        Long memberId = jwtProvider.parseAccessToken(accessToken);
        return new JwtAuthentication(memberId, accessToken);
    }
}
