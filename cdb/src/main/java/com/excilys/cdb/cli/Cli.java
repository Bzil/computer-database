package com.excilys.cdb.cli;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.CompanyService;
import com.excilys.cdb.service.ComputerService;
import com.excilys.cdb.util.dto.CompanyDTO;
import com.excilys.cdb.util.dto.ComputerDTO;
import com.excilys.cdb.util.page.CompanyPage;
import com.excilys.cdb.util.page.ComputerPage;
import com.excilys.cdb.util.validation.Validator;

@Component
public class Cli {

	@Autowired
	private ComputerService computerService;

	@Autowired
	private CompanyService companyService;

	@SuppressWarnings("resource")
	private String getChoice(List<String> choices) {
		final Scanner scanner = new Scanner(System.in);
		String choice = null;

		while (choice == null || !choices.contains(choice.toLowerCase())) {
			System.out.println("Make your choice :");
			choice = scanner.nextLine().trim();
		}
		return choice.toLowerCase();
	}

	@SuppressWarnings("resource")
	private String getChoice() {
		final Scanner scanner = new Scanner(System.in);
		String choice = null;

		while (choice == null || !isNumeric(choice)) {
			System.out.println("Make your choice :");
			choice = scanner.nextLine().trim();
		}
		return choice.toLowerCase();
	}

	@SuppressWarnings("resource")
	private String getChoiceDate() {
		final Scanner scanner = new Scanner(System.in);
		String choice = null;

		while (choice == null || !isCorrectDate(choice)) {
			System.out.println("date (pattern : dd-mm-yyyy or null) : ");
			choice = scanner.nextLine().trim();
		}
		return choice.toLowerCase();
	}

	@SuppressWarnings("resource")
	private String getString() {
		final Scanner scanner = new Scanner(System.in);
		String str = null;

		while (str == null || str.trim().isEmpty()) {
			System.out.println("Write your string : ");
			str = scanner.nextLine().trim();
		}
		return str.trim();
	}

	private List<String> toList(String... strings) {
		final List<String> ret = new ArrayList<>();
		for (final String s : strings) {
			ret.add(s);
		}
		return ret;
	}

	private boolean isNumeric(String str) {
		return (str.matches("\\d+?") || str.equals("null"));
	}

	private boolean isCorrectDate(String str) {
		return (Validator.getInstance().validate(str, "fr") || str
				.equals("null"));
	}

	private void mainCli() {
		while (true) {
			System.out.println("\n========= MENU =========");
			System.out.println("1) List computers");
			System.out.println("2) List companies");
			System.out
					.println("3) Show computer details (the detailed information of only one computer)");
			System.out.println("4) Create a computer");
			System.out.println("5) Update a computer");
			System.out.println("6) Delete a computer");
			System.out.println("7) Delete a company");
			System.out.println("0) Quit");
			System.out.println("Choose between 0 - 7 : ");

			switch (getChoice(toList("1", "2", "3", "4", "5", "6", "7", "8",
					"0"))) {
			case "1":
				showComputers();
				break;
			case "2":
				showCompanies();
				break;
			case "3":
				showComputer();
				break;
			case "4":
				createComputer();
				break;
			case "5":
				updateComputer();
				break;
			case "6":
				deleteComputer();
				break;
			case "7":
				deleteCompany();
				break;
			case "0":
				System.out.println("Program ended.");
				System.exit(0);
				;
			}
			;
		}
	}

	private void showComputers() {
		final ComputerPage cp = new ComputerPage();
		int start = 0;
		int offset = 10;
		boolean exit = false;
		while (!exit) {
			cp.showEntities(computerService.findAll(start, offset, null));

			System.out.println("Show more ? [y/n]");
			final String choice = getChoice(toList("y", "n"));
			switch (choice) {
			case "y":
				start = offset;
				offset += 10;
				break;
			case "n":
				exit = true;
			}
		}
	}

	private void showCompanies() {
		final CompanyPage cp = new CompanyPage();
		int start = 0;
		int offset = 10;
		boolean exit = false;
		while (!exit) {
			cp.showEntities(companyService.findAll(start, offset, null));

			System.out.println("Show more ? [y/n]");
			final String choice = getChoice(toList("y", "n"));
			switch (choice) {
			case "y":
				start = offset;
				offset += 10;
				break;
			case "n":
				exit = true;
			}
		}
	}

	private void showComputer() {
		final String choice = getChoice();
		System.out.println(computerService.find(Integer.parseInt(choice)));
	}

	private void deleteComputer() {
		System.out.println("Choose computer id : ");
		final String choice = getChoice();
		final ComputerDTO computer = computerService.find(Integer
				.parseInt(choice));
		if (computer != null) {
			System.out.println(computer);
			System.out.println("Confirm deletion ? [y/n]");
			final String str = getChoice(toList("y", "n"));
			switch (str) {
			case "y":
				computerService.delete(computer.id);
				System.out.println("Computer delete");
				break;
			case "n":
				return;
			}
		} else {
			System.out.println("Computer doesn't exist");
		}
	}

	private void deleteCompany() {
		System.out.println("Choose company id : ");
		final String choice = getChoice();
		final CompanyDTO company = companyService
				.find(Integer.parseInt(choice));
		if (company != null) {
			System.out.println(company);
			System.out.println("Confirm deletion ? [y/n]");
			final String str = getChoice(toList("y", "n"));
			switch (str) {
			case "y":
				companyService.delete(company.id);
				System.out.println("Company delete");
				break;
			case "n":
				return;
			}
		} else {
			System.out.println("Company doesn't exist");
		}
	}

	private void createComputer() {
		String buffer;
		final Computer computer = new Computer();
		LocalDateTime introduced = null, discontinued = null;
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
				"dd-MM-uuuu HH:mm:ss", new Locale("fr"));
		System.out.println("Name : ");
		computer.setName(getString());
		System.out.println("Introduced");
		buffer = getChoiceDate();
		if (!buffer.equals("null")) {
			buffer += " 00:00:00";
			introduced = LocalDateTime.parse(buffer, formatter);
		}
		System.out.println("Discontinuted");
		buffer = getChoiceDate();
		if (!buffer.equals("null")) {
			buffer += " 00:00:00";
			discontinued = LocalDateTime.parse(buffer, formatter);
		}
		System.out.println("Company Id");
		buffer = getChoice();
		if (!buffer.equals("null")) {
			computer.setCompany(new Company());
			computer.getCompany().setId(Integer.parseInt(buffer));
		}
		computer.setIntroduced(introduced);
		computer.setDiscontinued(discontinued);
		System.out.println(computerService.add(computer));
	}

	private void updateComputer() {
		String buffer;
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
				"dd-MM-uuuu HH:mm:ss", new Locale("fr"));
		System.out.println("iD : ");
		final Computer computer = ComputerDTO.fromDTO(computerService
				.find(Integer.parseInt(getChoice())));
		if (computer != null) {
			System.out.println("Introduced");
			buffer = getChoiceDate();
			if (!buffer.equals("null")) {
				buffer += " 00:00:00";
				final LocalDateTime introduced = LocalDateTime.parse(buffer,
						formatter);
				computer.setIntroduced(introduced);
			}
			System.out.println("Discontinuted");
			buffer = getChoiceDate();
			if (!buffer.equals("null")) {
				buffer += " 00:00:00";
				final LocalDateTime discontinued = LocalDateTime.parse(buffer,
						formatter);
				computer.setDiscontinued(discontinued);
			}
			System.out.println("Company Id");
			buffer = getChoice();
			if (!buffer.equals("null")) {
				if (computer.getCompany() == null) {
					computer.setCompany(new Company());
				}
				computer.getCompany().setId(Integer.parseInt(buffer));

			}
			System.out.println(computerService.update(computer));
		} else {
			System.out.println("Computer doesn't exist");
		}
	}

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		final ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"classpath:context-cli.xml");
		final Cli cli = ctx.getBean(Cli.class);
		cli.mainCli();
	}
}
