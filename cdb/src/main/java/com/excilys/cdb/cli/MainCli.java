package com.excilys.cdb.cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.persistence.CompanyDao;
import com.excilys.cdb.persistence.ComputerDao;
import com.excilys.cdb.persistence.impl.CompanyDaoImpl;
import com.excilys.cdb.persistence.impl.ComputerDaoImpl;

public class MainCli {

	private static ComputerDao computerDao = ComputerDaoImpl.INSTANCE
			.getInstance();
	private static CompanyDao companyDao = CompanyDaoImpl.INSTANCE
			.getInstance();

	public static void main(String[] args) throws IOException {
		int index = -1;
		MainCli mainCli = new MainCli();
		while (true) {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					System.in));
			System.out
					.print("\n========= MENU =========\n1) List computers\n2) List companies\n3)Show computer details (the detailed information of only one computer)\n4) Create a computer\n5) Update a computer\n6) Delete a computer\n0) Quit\n");

			System.out.print("Choose between 1 - 6 : ");
			try {
				index = Integer.parseInt(br.readLine());
			} catch (NumberFormatException nfe) {
				System.out.println("Invalid Format!");
			}
			switch (index) {
			case 1:
				System.out.print("\nList computer");
				mainCli.findAllComputers();
				break;
			case 2:
				System.out.print("\nList company");
				mainCli.findAllCompanies();
				break;
			case 3:
				System.out.print("\nComputer id :");
				try {
					int value = Integer.parseInt(br.readLine());
					mainCli.findOne(value);
				} catch (NumberFormatException nfe) {
					System.err.println("Invalid Format!");
				}
				break;
			case 4:
				System.out.print("\nCreate");
				try {
					mainCli.add();
				} catch (NumberFormatException nfe) {
					System.err.println("Invalid Format!");
				}
				break;
			case 5:
				System.out.print("\nUpdate, computer id : ");
				try {
					int value = Integer.parseInt(br.readLine());
					mainCli.update(value);
				} catch (NumberFormatException nfe) {
					System.err.println("Invalid Format!");
				}
				break;
			case 6:
				System.out.print("\nDelete Computer id ?");
				try {
					int value = Integer.parseInt(br.readLine());
					mainCli.delete(value);
				} catch (NumberFormatException nfe) {
					System.err.println("Invalid Format!");
				}
				break;

			default:
				System.out.print("Choose between 1 - 6 : ");
				break;
			}
		}
	}

	private void add() {
		String name = "";
		LocalDateTime introduced = null;
		LocalDateTime discontinued = null;
		int companyId;
		System.out.print("\nName : ");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			name = br.readLine();
		} catch (IOException e) {
		}
		System.out.print("introduced date (pattern yyyy-MM-dd) ");
		introduced = getLocalDateTime();
		System.out.print("discontinued date (pattern yyyy-MM-dd) ");
		discontinued = getLocalDateTime();
		System.out.print("Company id :");
		try {
			companyId = Integer.parseInt(br.readLine());
		} catch (NumberFormatException | IOException e) {
			companyId = -1;
		}
		System.out.println(computerDao.create(new Computer(name, introduced,
				discontinued, companyId)));
	}

	private void delete(int index) {
		try {
			Computer c = computerDao.find(index);
			computerDao.delete(c);
		} catch (NullPointerException e) {
			System.out.println("Id incorrect");
		}
	}

	private void update(int index) {
		System.out.println("Actual values : " + computerDao.find(index));
		String name = "";
		LocalDateTime introduced = null, discontinued = null;
		int companyId;
		System.out.print("\nName : ");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			name = br.readLine();
		} catch (IOException e) {
		}
		System.out.print("introduced date (pattern yyyy-MM-dd) ");
		introduced = getLocalDateTime();
		System.out.print("discontinued date (pattern yyyy-MM-dd) ");
		discontinued = getLocalDateTime();
		System.out.print("Company id :");
		try {
			companyId = Integer.parseInt(br.readLine());
		} catch (NumberFormatException | IOException e) {
			companyId = -1;
		}
		System.out.println(computerDao.update(new Computer(index, name,
				introduced, discontinued, companyId)));
	}

	private void findOne(int index) {
		System.out.println(computerDao.find(index));
	}

	private void findAllCompanies() {
		System.out.println(companyDao.findAll());
	}

	private void findAllComputers() {
		System.out.println(computerDao.findAll());
	}

	private LocalDateTime getLocalDateTime(){
		LocalDateTime date = null;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			String str = br.readLine();
			if(str != null && !str.trim().isEmpty())
				date = LocalDateTime.parse(str, formatter);
		} catch (IOException e1) {
			System.out.println("Wrong pattern");
			date = null;
		}	
		return date;
	}

}
