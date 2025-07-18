package com.breno.IoTDataService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class IoTDataServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(IoTDataServiceApplication.class, args);
	}

}
