package org.delivery.api.domain.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.delivery.api.common.api.Api;
import org.delivery.api.domain.user.business.UserBusiness;
import org.delivery.api.domain.user.controller.model.UserLoginRequest;
import org.delivery.api.domain.user.controller.model.UserRegisterRequest;
import org.delivery.api.domain.user.controller.model.UserResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 인증을 태우지 않은 주소 시리즈
 * 사용자 회원 가입, 로그인 등
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/open-api/user")
public class UserOpenApiController {

    private final UserBusiness userBusiness;


    /**
     * 회원 가입
     * @param request
     * @return
     */
    @PostMapping("/register")
    public Api<UserResponse> register(
        @Valid
        @RequestBody Api<UserRegisterRequest> request
    ) {
        var response = userBusiness.register(request.getBody());

        return Api.OK(response);
    }

    /**
     * 로그인
     * @param request
     * @return
     */
    @PostMapping("/login")
    public Api<UserResponse> login(
        @Valid
        @RequestBody Api<UserLoginRequest> request
    ) {
        var response = userBusiness.login(request.getBody());

        return Api.OK(response);
    }
}
