package com.ratones_colorados.ahorro_compras;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

@SpringBootApplication(exclude = {UserDetailsServiceAutoConfiguration.class})
public class AhorroComprasApplication {

	public static void main(String[] args) {
		SpringApplication.run(AhorroComprasApplication.class, args);
	}

}
