package com.climbingCRM.climbingcrm.common.context;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

/**
 * ThreadLocal에 저장하여 사용할 Context
 */
@Builder
@AllArgsConstructor
public class UserContext {

    private Long id;

    private String phone;

    //TODO: 지점 관리 개발 완료시 이용
    private Long regionId;

}
