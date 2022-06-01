package co.com.pragma.store.custom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
@EnableEurekaClient
public class CustomServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomServiceApplication.class, args);
	}

}
