package com.rays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class SpringBootProjApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootProjApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer webConfig() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {

				CorsRegistration cors = registry.addMapping("/**").allowedOrigins("http://localhost:4200");
			}
			/*
			 * @Override public void addInterceptors(InterceptorRegistry registry) {
			 * registry.addInterceptor(frontCtl).addPathPatterns("/**").excludePathPatterns(
			 * "/Auth/**"); }
			 */
		};
	}
}
