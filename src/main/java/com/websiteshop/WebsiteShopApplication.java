package com.websiteshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.websiteshop.config.StorageProperties;

@EnableConfigurationProperties(StorageProperties.class)
@SpringBootApplication
public class WebsiteShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebsiteShopApplication.class, args);
    }

}