package com.dss.springVaadinBasicProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:springconfig.xml")
public class SpringVaadinBasicProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringVaadinBasicProjectApplication.class, args);
	}
}
