package edu.eci.cosw.samples;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("edu.eci.cosw.example.persistence")
@EntityScan("edu.eci.cosw.jpa.sample.model")
public class SpringDataRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataRestApiApplication.class, args);
	}
}
