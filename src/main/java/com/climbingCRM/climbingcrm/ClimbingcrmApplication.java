package com.climbingCRM.climbingcrm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EntityScan(basePackages = {"com.climbingCRM.climbingcrm.entity"})  // 추가
public class ClimbingcrmApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClimbingcrmApplication.class, args);
	}

}