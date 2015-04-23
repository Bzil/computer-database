package com.excilys.cdb.cli;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.excilys.cdb.validation.DateValidator;

public class CliUtil {

	@SuppressWarnings("resource")
	public static String getChoice(List<String> choices) {
		final Scanner scanner = new Scanner(System.in);
		String choice = null;

		while (choice == null || !choices.contains(choice.toLowerCase())) {
			System.out.println("Make your choice :");
			choice = scanner.nextLine().trim();
		}
		return choice.toLowerCase();
	}

	@SuppressWarnings("resource")
	public static String getChoice() {
		final Scanner scanner = new Scanner(System.in);
		String choice = null;

		while (choice == null || !isNumeric(choice)) {
			System.out.println("Make your choice :");
			choice = scanner.nextLine().trim();
		}
		return choice.toLowerCase();
	}

	@SuppressWarnings("resource")
	public static String getChoiceDate() {
		final Scanner scanner = new Scanner(System.in);
		String choice = null;

		while (choice == null || !isCorrectDate(choice)) {
			System.out.println("date (pattern : dd-mm-yyyy or null) : ");
			choice = scanner.nextLine().trim();
		}
		return choice.toLowerCase();
	}

	@SuppressWarnings("resource")
	public static String getString() {
		final Scanner scanner = new Scanner(System.in);
		String str = null;

		while (str == null || str.trim().isEmpty()) {
			System.out.println("Write your string : ");
			str = scanner.nextLine().trim();
		}
		return str.trim();
	}

	public static List<String> toList(String... strings) {
		final List<String> ret = new ArrayList<>();
		for (final String s : strings) {
			ret.add(s);
		}
		return ret;
	}

	public static boolean isNumeric(String str) {
		return (str.matches("\\d+?") || str.equals("null"));
	}

	public static boolean isCorrectDate(String str) {
		return (new DateValidator().isValid(str, null) || str.equals("null"));
	}
}
