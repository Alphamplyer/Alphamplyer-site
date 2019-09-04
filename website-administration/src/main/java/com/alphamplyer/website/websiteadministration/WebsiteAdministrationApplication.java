package com.alphamplyer.website.websiteadministration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class WebsiteAdministrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebsiteAdministrationApplication.class, args);
	}

}
