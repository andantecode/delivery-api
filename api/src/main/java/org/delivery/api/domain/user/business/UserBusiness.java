package org.delivery.api.domain.user.business;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.delivery.api.common.annotation.Business;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.api.domain.user.controller.model.UserLoginRequest;
import org.delivery.api.domain.user.controller.model.UserRegisterRequest;
import org.delivery.api.domain.user.controller.model.UserResponse;
import org.delivery.api.domain.user.converter.UserConverter;
import org.delivery.api.domain.user.service.UserService;

import java.util.Optional;

@RequiredArgsConstructor
@Business
public class UserBusiness {
    private final UserService userService;
    private final UserConverter userConverter;

    /**
     * 사용자에 대한 가입 처리 로직
     * 1. request -> entity
     * 2. entity -> save
     * 3. save entity -> response
     * 4. return response
     * @param request
     * @return
     */
    public UserResponse register(@Valid UserRegisterRequest request) {

        return Optional.ofNullable(request)
                .map(userConverter::toEntity)
                .map(userService::register)
                .map(userConverter::toResponse)
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "Request Null"));

    }

    /**
     * 로그인
     * 1. email, password를 갖고 사용자 확인
     * 2. user entity 로그인 확인
     * 3. token 생성
     * 4. token을 반환해줌
     * @param request
     * @return
     */
    public UserResponse login(@Valid UserLoginRequest request) {
        var userEntity = userService.login(request.getEmail(), request.getPassword());

        // TODO 토큰 생성 로직으로 변경하기

        return userConverter.toResponse(userEntity);
    }
}
