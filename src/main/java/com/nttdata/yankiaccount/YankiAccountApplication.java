package com.nttdata.yankiaccount;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class YankiAccountApplication {

	public static void main(String[] args) {
		SpringApplication.run(YankiAccountApplication.class, args);
	}

}
