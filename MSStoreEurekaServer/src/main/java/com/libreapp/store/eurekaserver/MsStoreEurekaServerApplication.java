package com.libreapp.store.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

import lombok.extern.log4j.Log4j2;

@Log4j2
@EnableEurekaServer
@SpringBootApplication
public class MsStoreEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsStoreEurekaServerApplication.class, args);
		log.info("--> Load Eureka Server Complete!!!");
	}

}
