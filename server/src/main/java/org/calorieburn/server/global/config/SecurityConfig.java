package org.calorieburn.server.global.config;


import lombok.RequiredArgsConstructor;
import org.calorieburn.server.global.security.AuthenticationFilter;
import org.calorieburn.server.global.security.JwtProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, JwtProvider jwtProvider) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .authorizeRequests()
                .requestMatchers("/auth/sign-up", "/auth/sign-in", "/h2-console/**").permitAll()
                .and()
                .headers((headerConfig) ->
                        headerConfig.frameOptions((frameOptionsConfig -> frameOptionsConfig.disable())))
                .addFilterBefore(
                        new AuthenticationFilter(jwtProvider),
                        UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtProvider jwtProvider(
            @Value("${jwt.issuer}") String issuer,
            @Value("${jwt.expiry-seconds}") int expirySeconds,
            @Value("${jwt.secret}") String secret) {
        return new JwtProvider(issuer, expirySeconds, secret);
    }
}