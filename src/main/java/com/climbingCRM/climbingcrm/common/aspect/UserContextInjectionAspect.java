package com.climbingCRM.climbingcrm.common.aspect;

import com.climbingCRM.climbingcrm.common.annotations.InjectUserContext;
import com.climbingCRM.climbingcrm.common.context.UserContext;
import com.climbingCRM.climbingcrm.common.contextholder.UserContextHolder;
import com.climbingCRM.climbingcrm.entity.user.User;
import com.climbingCRM.climbingcrm.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * UserContext를 생성하고 ThreadLocal에 저장함
 */
@Aspect
@Component
@RequiredArgsConstructor
public class UserContextInjectionAspect {

    private final UserRepository userRepository;

    @Around("@annotation(com.climbingCRM.climbingcrm.common.annotations.InjectUserContext")
    public Object injectUserEntity(ProceedingJoinPoint joinPoint) throws Throwable{

        try{
            String username = SecurityContextHolder.getContext().getAuthentication().getName();

            User user = userRepository.findByUsername(username)
                    .orElseThrow(()-> new UsernameNotFoundException(
                            "사용자를 찾을 수 없습니다"
                    ));

            UserContextHolder.setCurrentUser(
             UserContext.builder()
                    .id(user.getId())
                    .build());

            return joinPoint.proceed();
        }finally {
            UserContextHolder.clear();
        }

    }

}
