package com.libreapp.store.sale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import lombok.extern.log4j.Log4j2;

@Log4j2
@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
public class MsStoreSaleApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsStoreSaleApplication.class, args);
		log.info("--> Load MSSale Complete!!!");
	}

}
