package com.anonyblah.xxalimi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication/*(exclude=DispatcherServletAutoConfiguration.class)*/
@EnableScheduling
@EnableAutoConfiguration
@ImportResource("tasks.xml")
public class XxAlimiApplication {

	public static void main(String[] args) {
		SpringApplication.run(XxAlimiApplication.class, args);
	}
}
