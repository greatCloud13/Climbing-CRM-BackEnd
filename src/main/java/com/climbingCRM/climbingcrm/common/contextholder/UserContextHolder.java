package com.climbingCRM.climbingcrm.common.contextholder;

import com.climbingCRM.climbingcrm.common.context.UserContext;
import org.springframework.stereotype.Component;

/**
 * UserContext를 ThreadLocal에서 관리
 */
@Component
public class UserContextHolder {

    private static final ThreadLocal<UserContext> currentUser = new ThreadLocal<>();

    public static void setCurrentUser(UserContext userContext){currentUser.set(userContext);}

    public static UserContext getCurrentUser(){return currentUser.get();}

    public static void clear(){currentUser.remove();}
}
