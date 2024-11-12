package org.calorieburn.server.global.security;

import lombok.RequiredArgsConstructor;
import org.calorieburn.server.core.member.exception.MemberErrorCode;
import org.calorieburn.server.global.common.LoginMember;
import org.calorieburn.server.global.exception.type.ApiException;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@RequiredArgsConstructor
public class LoginArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean hasParameterAnnotation = parameter.hasParameterAnnotation(LoginMember.class);
        boolean hasLongParameterType = parameter.getParameterType().isAssignableFrom(Long.class);
        return hasParameterAnnotation && hasLongParameterType;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        checkAuthenticated(authentication);
        return authentication.getPrincipal();
    }

    private void checkAuthenticated(Authentication jwtAuthentication) {
        if (jwtAuthentication != null) {
            return;
        }
        throw new ApiException(MemberErrorCode.M007);
    }
}
