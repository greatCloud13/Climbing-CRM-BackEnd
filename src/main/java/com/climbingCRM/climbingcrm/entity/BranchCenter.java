package com.climbingCRM.climbingcrm.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="branch_centers")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BranchCenter extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String centerName;

    @Column(nullable = false, length = 100)
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Type type;



    private enum Type{
        // 볼더링
        BOULDER,

        // 리드
        LEAD
    }
}

