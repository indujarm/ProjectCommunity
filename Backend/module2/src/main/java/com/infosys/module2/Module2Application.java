package com.infosys.module2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class Module2Application {
	public static void main(String[] args) {
		SpringApplication.run(Module2Application.class, args);
		System.out.println("Module 2 started...");
	}
}
