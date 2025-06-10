package org.delivery.api.domain.user.converter;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.annotation.Converter;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.api.domain.user.controller.model.UserRegisterRequest;
import org.delivery.api.domain.user.controller.model.UserResponse;
import org.delivery.db.user.UserEntity;

import javax.swing.text.html.Option;
import java.util.Optional;

@RequiredArgsConstructor
@Converter
public class UserConverter {

    /**
     * UserRegisterRequest -> UserEntity
     * @param request
     * @return userEntity
     */
    public UserEntity toEntity(UserRegisterRequest request) {
        return Optional.ofNullable(request)
                .map(it -> UserEntity.builder()
                        .name(it.getName())
                        .email(it.getEmail())
                        .password(it.getPassword())
                        .address(it.getAddress())
                        .build()
                )
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "UserRegister Request Null"));
    }

    /**
     * UserEntity -> UserResponse
     * @param userEntity
     * @return userResponse
     */
    public UserResponse toResponse(UserEntity userEntity) {
        return Optional.ofNullable(userEntity)
                .map(it -> UserResponse.builder()
                        .id(it.getId())
                        .name(it.getName())
                        .status(it.getStatus())
                        .email(it.getEmail())
                        .address(it.getAddress())
                        .registeredAt(it.getRegisteredAt())
                        .unregisteredAt(it.getUnregisteredAt())
                        .lastLoginAt(it.getLastLoginAt())
                        .build())
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "UserEntity Null"));
    }
}
