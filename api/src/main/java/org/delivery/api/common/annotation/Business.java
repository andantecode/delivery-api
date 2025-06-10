package org.delivery.api.common.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Service // Spring에서 자동으로 감지, bean으로 등록 (다만, @Business는 비즈니스 로직을 처리하는 곳이야. 라고 명시적으로 설정)
public @interface Business {

    @AliasFor(annotation = Service.class) // Service도 Component고 타고가면 이게 있음
    String value() default "";
}
