package com.example.demo;

import com.example.demo.Client.GoogleClient;
import com.example.demo.Controller.PlaylistController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class EsteiraApplication {

	public static void main(String[] args) {
		SpringApplication.run(EsteiraApplication.class, args);
	}

}
