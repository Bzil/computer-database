package com.excilys.cdb.cli;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.persistence.CompanyDao;
import com.excilys.cdb.persistence.ComputerDao;
import com.excilys.cdb.persistence.impl.CompanyDaoImpl;
import com.excilys.cdb.persistence.impl.ComputerDaoImpl;

public class Main {

	public static void main(String[] args) {
		ComputerDao computerDao = ComputerDaoImpl.INSTENSE.getInstance();
		CompanyDao companyDao = CompanyDaoImpl.INSTENSE.getInstance();
		
		System.out.println("Nb computer " + computerDao.count());
		System.out.println("Nb compagny " + companyDao.count());
		System.out.println(companyDao.find(1));
		System.out.println(companyDao.update(new Company(49,"test2")));
		//System.out.println("Nb compagny " + companyDao.count());
		//companyDao.delete(companyDao.find(47));
		//System.out.println("Delete \nNb compagny " + companyDao.count());
		System.out.println(companyDao.findAll());
		

	}

}
