package hu.cubix.logistics.bencepar;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import hu.cubix.logistics.bencepar.service.InitDbService;

@SpringBootApplication
public class LogisticsApplication implements CommandLineRunner {

	@Autowired
	InitDbService initDbService;

	public static void main(String[] args) {
		SpringApplication.run(LogisticsApplication.class, args);

	}

	public void run(String... args) throws Exception {
		initDbService.clearDb();
//		initDbService.initDb();

		System.out.println("Fut az applikáció!");

	}
}
