package com.kalashnyky.ticketmachine;

import com.kalashnyky.ticketmachine.service.ScreenService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Scanner;

@SpringBootApplication
public class TicketMachineApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(TicketMachineApplication.class, args);
		ScreenService screenService = applicationContext.getBean(ScreenService.class);

		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("________________\n\n");

			outputScreenText();

			String userChoice = scanner.nextLine();
			screenService.handleChoice(userChoice);
		}
	}

	private static void outputScreenText() {
		System.out.println("1. Add adult ticket");
		System.out.println("2. Add senior ticket");
		System.out.println("3. Print receipt");
		System.out.println("0. Exit");
	}
}
