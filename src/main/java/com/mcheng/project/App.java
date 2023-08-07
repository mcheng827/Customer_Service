package com.mcheng.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.mcheng.project")
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}