package com.alphamplyer.website.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.alphamplyer.website.shop")
public class WebsiteShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebsiteShopApplication.class, args);
	}

}
