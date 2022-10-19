package com.libreapp.store.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import lombok.extern.log4j.Log4j2;

@Log4j2
@EnableAdminServer
@SpringBootApplication
public class MsStoreAdminBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsStoreAdminBootApplication.class, args);
		log.info("--> Load Admin Boot Complete!!!");
	}

}
