package at.fhstp.sniffable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SniffableApplication {

	public static void main(String[] args) {
		SpringApplication.run(SniffableApplication.class, args);
		//test
	}

}
