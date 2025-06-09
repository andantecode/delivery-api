package org.delivery.api.exceptionhandler;

import lombok.extern.slf4j.Slf4j;
import org.delivery.api.common.api.Api;
import org.delivery.api.common.error.ErrorCode;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@Order(value=Integer.MAX_VALUE) // 가장 최후의 실행
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Api<Object>> exception(
        Exception exception // spring이 알아서 DI
    ) {
        log.error("", exception); // stacktrace 찍어주기

        // 클라이언트에 굳이 상세 정보를 내려줄 필요는 없음
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        Api.ERROR(ErrorCode.SERVER_ERROR)
                );
    }
}
