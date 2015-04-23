package com.excilys.cdb.cli;

import static com.excilys.cdb.cli.CliUtil.getChoice;
import static com.excilys.cdb.cli.CliUtil.toList;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CliWebService {

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
						// showComputers();
						break;
					case "2":
						// showCompanies();
						break;
					case "3":
						showComputer();
						break;
					case "4":
						// createComputer();
						break;
					case "5":
						// updateComputer();
						break;
					case "6":
						// deleteComputer();
						break;
					case "7":
						// deleteCompany();
						break;
					case "0":
						System.out.println("Program ended.");
						System.exit(0);
						;
			}
			;
		}
	}

	public void showComputer() {
		System.out.println("Computer id ?");
		final String choice = getChoice();
		String output = null;
		try {
			final URL url = new URL("http://localhost:8080/cdb/api/computer/"
					+ choice);
			final HttpURLConnection conn = (HttpURLConnection) url
					.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}
			final BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}

			conn.disconnect();
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		final CliWebService cli = new CliWebService();
		cli.mainCli();
	}

}
