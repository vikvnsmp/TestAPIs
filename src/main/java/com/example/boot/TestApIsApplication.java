package com.example.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = "com.example")
public class TestApIsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestApIsApplication.class, args);
	}
}
