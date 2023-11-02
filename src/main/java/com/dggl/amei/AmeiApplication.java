package com.dggl.amei;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
//@EnableScheduling
public class AmeiApplication {

	public static String STRIPE_API_KEY = "sk_test_51Nq3F7Ez6oa2bkpUYnGIZJaxzHec0LGk1jt9dMRljwK3kUzcrYyIqqDoJd9OE11b0h4b9CyX6QR546apBqc13BSY00EURk2Kvz";
	public static void main(String[] args) {
		SpringApplication.run(AmeiApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedMethods("*").allowedOrigins("*");
			}
		};
	}
}
