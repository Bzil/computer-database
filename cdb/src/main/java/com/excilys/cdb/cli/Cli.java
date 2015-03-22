package com.excilys.cdb.cli;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.ComputerService;
import com.excilys.cdb.service.impl.ComputerServiceImpl;
import com.excilys.cdb.util.CompanyPage;
import com.excilys.cdb.util.ComputerPage;

public class Cli {
 
	private static ComputerService computerService = ComputerServiceImpl.INSTANCE
			.getInstance();

	private static final String DATE_PATTERN = "^(0[1-9]|1[0-9]|2[0-8]|29((?=-([0][13-9]|1[0-2])|(?=-(0[1-9]|1[0-2])-([0-9]{2}(0[48]|[13579][26]|[2468][048])|([02468][048]|[13579][26])00))))|30(?=-(0[13-9]|1[0-2]))|31(?=-(0[13578]|1[02])))-(0[1-9]|1[0-2])-[0-9]{4}$";

	@SuppressWarnings("resource")
	private String getChoice(List<String> choices) {
		Scanner scanner = new Scanner(System.in);
		String choice = null;

		while (choice == null || !choices.contains(choice.toLowerCase())) {
			System.out.println("Make your choice :");
			choice = scanner.nextLine().trim();
		}
		return choice.toLowerCase();
	}

	@SuppressWarnings("resource")
	private String getChoice() {
		Scanner scanner = new Scanner(System.in);
		String choice = null;

		while (choice == null || !isNumeric(choice)) {
			System.out.println("Make your choice :");
			choice = scanner.nextLine().trim();
		}
		return choice.toLowerCase();
	}

	@SuppressWarnings("resource")
	private String getChoiceDate() {
		Scanner scanner = new Scanner(System.in);
		String choice = null;

		while (choice == null || !isCorrectDate(choice)) {
			System.out.println("date (pattern : dd-mm-yyyy or null) : ");
			choice = scanner.nextLine().trim();
		}
		return choice.toLowerCase();
	}

	@SuppressWarnings("resource")
	private String getString() {
		Scanner scanner = new Scanner(System.in);
		String str = null;
		while (str == null || str.trim().isEmpty()) {
			System.out.println("Write your string : ");
			str = scanner.nextLine().trim();
		}
		return str.trim();
	}

	private List<String> toList(String... strings) {
		List<String> ret = new ArrayList<>();
		for (String s : strings) {
			ret.add(s);
		}
		return ret;
	}

	private boolean isNumeric(String str) {
		return (str.matches("\\d+?") || str.equals("null"));
	}

	private boolean isCorrectDate(String str) {
		return (str.matches(DATE_PATTERN) || str.equals("null"));
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
			System.out.println("0) Quit");
			System.out.println("Choose between 0 - 6 : ");

			switch (getChoice(toList("1", "2", "3", "4", "5", "6", "0"))) {
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
			case "0":
				System.out.println("Program ended.");
				System.exit(0);
				;
			}
			;
		}
	}

	private void showComputers() {
		ComputerPage pages = new ComputerPage();
		int start = 0;
		int offset = 10;
		boolean exit = false;
		while (!exit) {
			pages.showPaginatedList(pages.paginate(start, offset));
			System.out.println("Show more ? [y/n]");
			String choice = getChoice(toList("y", "n"));
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
		CompanyPage pages = new CompanyPage();
		int start = 0;
		int offset = 10;
		boolean exit = false;
		while (!exit) {
			pages.showPaginatedList(pages.paginate(start, offset));
			System.out.println("Show more ? [y/n]");
			String choice = getChoice(toList("y", "n"));
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
		String choice = getChoice();
		System.out.println(computerService.find(Integer.parseInt(choice)));
	}

	private void deleteComputer() {
		String choice = getChoice();
		Computer computer = computerService.find(Integer.parseInt(choice));
		if (computer != null) {
			System.out.println(computer);
			System.out.println("Confirm deletion ? [y/n]");
			String str = getChoice(toList("y", "n"));
			switch (str) {
			case "y":
				computerService.delete(computer);
				System.out.println("Computer delete");
				break;
			case "n":
				return;
			}
		} else {
			System.out.println("Computer doesn't exist");
		}
	}

	private void createComputer() {
		String buffer;
		Computer computer = new Computer();
		LocalDateTime introduced = null, discontinued = null;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
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
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
				"dd-MM-uuuu HH:mm:ss", new Locale("fr"));
		System.out.println("iD : ");
		Computer computer = computerService.find(Integer.parseInt(getChoice()));
		if (computer != null) {
			System.out.println("Introduced");
			buffer = getChoiceDate();
			if (!buffer.equals("null")) {
				buffer += " 00:00:00";
				LocalDateTime introduced = LocalDateTime.parse(buffer,
						formatter);
				computer.setIntroduced(introduced);
			}
			System.out.println("Discontinuted");
			buffer = getChoiceDate();
			if (!buffer.equals("null")) {
				buffer += " 00:00:00";
				LocalDateTime discontinued = LocalDateTime.parse(buffer,
						formatter);
				computer.setDiscontinued(discontinued);
			}
			System.out.println("Company Id");
			buffer = getChoice();
			if (!buffer.equals("null")) {
				if(computer.getCompany() == null) {
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
		Cli cli = new Cli();
		cli.mainCli();
	}
}
