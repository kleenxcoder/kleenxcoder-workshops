package com.kleenxcoder.mapstruct.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author kleenxcoder
 */

@Configuration
@ComponentScan({"com.kleenxcoder.mapstruct"})
@EnableJpaRepositories(basePackages = {"com.kleenxcoder.mapstruct.repository"})
@EntityScan(basePackages = {"com.kleenxcoder.mapstruct.entity"})
public class AppConfig implements WebMvcConfigurer{
	
}
