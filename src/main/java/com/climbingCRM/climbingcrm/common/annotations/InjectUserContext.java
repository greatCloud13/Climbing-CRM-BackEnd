package com.climbingCRM.climbingcrm.common.annotations;

import java.lang.annotation.*;

/**
 * 현재 메소드에 UserContext 객체를 주입함
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface InjectUserContext {

    /**
     * User를 찾지 못했을 때 예외를 발생시키는지 여부
     * @return true면 예외 발생, false면 null 허용
     */
    boolean required() default true;

}
