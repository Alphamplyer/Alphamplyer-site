package com.alphamplyer.website.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.alphamplyer.website.main")
public class WebsiteMainApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebsiteMainApplication.class, args);
	}

}
