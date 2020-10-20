package com.kleenxcoder.spring.rest.jackson.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author kleenxcoder
 */

@Configuration
@ComponentScan({"com.kleenxcoder.spring.rest.jackson"})
@EnableJpaRepositories(basePackages = {"com.kleenxcoder.spring.rest.jackson.repository"})
@EntityScan(basePackages = {"com.kleenxcoder.spring.rest.jackson.entity"})
public class AppConfig {
	
}
