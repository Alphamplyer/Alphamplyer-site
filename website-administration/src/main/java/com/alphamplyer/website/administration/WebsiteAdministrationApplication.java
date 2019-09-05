package com.alphamplyer.website.administration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.alphamplyer.website.administration")
public class WebsiteAdministrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebsiteAdministrationApplication.class, args);
	}

}
