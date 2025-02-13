package com.elengel.api.fullstack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class FullstackApplication {

	public static void main(String[] args) {
		SpringApplication.run(FullstackApplication.class, args);
	}
	//generaremos los password para los usuarios, estas son las password que se utilizan en el importsql
	/*@Bean
	public CommandLineRunner createPassword(PasswordEncoder passwordEncoder){
		return angumentos ->{

			System.out.println(passwordEncoder.encode("Root1234"));
			System.out.println(passwordEncoder.encode("Root1234."));
			System.out.println(passwordEncoder.encode("Root1234.$"));
		};
	}*/

}
