package com.excilys.cdb.cli;

import static com.excilys.cdb.cli.CliUtil.getChoice;
import static com.excilys.cdb.cli.CliUtil.getChoiceDate;
import static com.excilys.cdb.cli.CliUtil.getString;
import static com.excilys.cdb.cli.CliUtil.toList;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

import com.excilys.cdb.dto.ComputerDTO;
import com.excilys.cdb.mapper.ComputerMapper;
import com.excilys.cdb.mapper.impl.ComputerMapperImpl;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;

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
			System.out.println("8) Show company details");
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
				// updateComputer();
				break;
			case "6":
				deleteComputer();
				break;
			case "7":
				deleteCompany();
				break;
			case "8":
				showCompany();
				break;
			case "0":
				System.out.println("Program ended.");
				System.exit(0);
				break;
			}
			;
		}
	}

	public void createComputer() {
		final ComputerMapper mapper = new ComputerMapperImpl();
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
		computer.setCompany(new Company());
		if (!buffer.equals("null")) {

			computer.getCompany().setId(Integer.parseInt(buffer));
		} else {
			computer.getCompany().setId(-1);
		}
		computer.setIntroduced(introduced);
		computer.setDiscontinued(discontinued);

		final ComputerDTO dto = mapper.toDto(computer);

		final Client client = ClientBuilder.newClient();
		final WebTarget target = client.target(getBaseURI());
		System.out.println(target.path("computer")
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.json(dto), String.class));
	}

	public void showComputer() {
		System.out.println("Computer id ?");
		final String choice = getChoice();
		final ClientConfig config = new ClientConfig();
		final Client client = ClientBuilder.newClient(config);
		final WebTarget target = client.target(getBaseURI());
		System.out.println(target.path("computer").path(choice).request()
				.get(String.class));

	}

	public void showCompanies() {
		final ClientConfig config = new ClientConfig();
		final Client client = ClientBuilder.newClient(config);
		final WebTarget target = client.target(getBaseURI());
		System.out.println(target.path("company").path("list").request()
				.get(String.class));
	}

	public void showCompany() {
		System.out.println("Company id ?");
		final String choice = getChoice();

		final ClientConfig config = new ClientConfig();
		final Client client = ClientBuilder.newClient(config);
		final WebTarget target = client.target(getBaseURI());
		System.out.println(target.path("company").path(choice).request()
				.get(String.class));
	}

	public void showComputers() {
		final ClientConfig config = new ClientConfig();
		final Client client = ClientBuilder.newClient(config);
		final WebTarget target = client.target(getBaseURI());
		System.out.println(target.path("computer").path("list").request()
				.get(String.class));
	}

	private void deleteComputer() {
		System.out.println("Computer id ?");
		final String choice = getChoice();

		final ClientConfig config = new ClientConfig();
		final Client client = ClientBuilder.newClient(config);
		final WebTarget target = client.target(getBaseURI());
		System.out.println(target.path("computer").path(choice).request()
				.delete());
	}

	private void deleteCompany() {
		System.out.println("Company id ?");
		final String choice = getChoice();

		final ClientConfig config = new ClientConfig();
		final Client client = ClientBuilder.newClient(config);
		final WebTarget target = client.target(getBaseURI());
		System.out.println(target.path("company").path(choice).request()
				.delete());

	}

	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost:8080/cdb/api").build();
	}

	public static void main(String[] args) {
		final CliWebService cli = new CliWebService();
		cli.mainCli();
	}

}
