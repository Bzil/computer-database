package com.excilys.cdb.persistence;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.persistence.impl.CompanyDaoImpl;
import com.excilys.cdb.util.H2Util;

public class CompanyDaoTest {

	private static final String COMPANIES_XML = "src/test/resources/company/company.xml";
	private static final String EMPTY_XML = "src/test/resources/company/company.empty.xml";

	@BeforeClass
	public static void setUp() {
		DaoManager.INSTANCE.propertiesFile = "src/test/resources/h2.properties";

		try {
			H2Util.executeSqlFile(H2Util.getConnection());
		} catch (IOException | SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	@After
	public void tearDown() throws Exception {
		H2Util.dbTester.onTearDown();
	}

	@Test
	public void testFindWithWrongValue() {
		try {
			H2Util.cleanlyInsert(new FlatXmlDataSetBuilder().build(new File(
					COMPANIES_XML)));
		} catch (Exception e) {
			fail("Can't load data");
		}
		final Company company = CompanyDaoImpl.getInstance().find(-1);
		assertNull(company);
	}

	@Test
	public void testFind() {
		try {
			H2Util.cleanlyInsert(new FlatXmlDataSetBuilder().build(new File(
					COMPANIES_XML)));
		} catch (Exception e) {
			fail("Can't load data");
		}
		final Company company = CompanyDaoImpl.getInstance().find(3);
		assertEquals(3, company.getId());
		assertEquals("Apple Inc.", company.getName());
	}

	@Test
	public void testFindWhitoutData() {
		try {
			H2Util.cleanlyInsert(new FlatXmlDataSetBuilder().build(new File(
					EMPTY_XML)));
		} catch (Exception e) {
			fail("Can't load data");
		}
		final Company company = CompanyDaoImpl.getInstance().find(3);
		assertNull(company);
	}

	@Test
	public void testCountWhitoutData() {
		try {
			H2Util.cleanlyInsert(new FlatXmlDataSetBuilder().build(new File(
					EMPTY_XML)));
		} catch (Exception e) {
			fail("Can't load data");
		}
		final int countExpected = 0;
		final int count = CompanyDaoImpl.getInstance().count();
		assertEquals(countExpected, count);
	}

	@Test
	public void testCountWhitData() {
		try {
			H2Util.cleanlyInsert(new FlatXmlDataSetBuilder().build(new File(
					COMPANIES_XML)));
		} catch (Exception e) {
			fail("Can't load data");
		}
		final int countExpected = 3;
		final int count = CompanyDaoImpl.getInstance().count();
		assertEquals(countExpected, count);
	}

	@Test(expected = NullPointerException.class)
	public void testCreateWithNullData() {
		try {
			H2Util.cleanlyInsert(new FlatXmlDataSetBuilder().build(new File(
					COMPANIES_XML)));
		} catch (Exception e) {
			fail("Can't load data");
		}
		CompanyDaoImpl.getInstance().create(null);
	}

	@Test(expected = AssertionError.class)
	public void testCreateWithoutName() {
		try {
			H2Util.cleanlyInsert(new FlatXmlDataSetBuilder().build(new File(
					EMPTY_XML)));
		} catch (Exception e) {
			fail("Can't load data");
		}
		Company c = new Company();
		final Company company = CompanyDaoImpl.getInstance().create(c);
		assertNull(company);
	}

	@Test
	public void testCreateWithName() {
		try {
			try {
				DaoManager.INSTANCE.getConnection().setAutoCommit(false);

				H2Util.cleanlyInsert(new FlatXmlDataSetBuilder()
						.build(new File(COMPANIES_XML)));
				Company c = new Company("Test");
				final Company company = CompanyDaoImpl.getInstance().create(c);
				assertNotNull(company);
				assertEquals(4, company.getId());
				assertEquals(c.getName(), company.getName());
			} catch (Exception e) {
				fail("Can't load data");
			} finally {
				DaoManager.INSTANCE.getConnection().rollback();
				DaoManager.INSTANCE.getConnection().setAutoCommit(true);
			}
		} catch (Exception e) {
			fail("Can't load data");
		}

	}

	@Test(expected = NullPointerException.class)
	public void testUpdateWhithNullData() {
		try {
			H2Util.cleanlyInsert(new FlatXmlDataSetBuilder().build(new File(
					COMPANIES_XML)));
		} catch (Exception e) {
			fail("Can't load data");
		}
		final Company company = CompanyDaoImpl.getInstance().update(null);
		assertNull(company);
	}

	@Test(expected = AssertionError.class)
	public void testUpdateWithoutName() {
		try {
			H2Util.cleanlyInsert(new FlatXmlDataSetBuilder().build(new File(
					COMPANIES_XML)));
		} catch (Exception e) {
			fail("Can't load data");
		}
		Company c = new Company();
		c.setId(3);
		final Company company = CompanyDaoImpl.getInstance().update(c);
		assertNull(company);
	}
	
	@Test
	public void testUpdateWithName() {
		try {
			H2Util.cleanlyInsert(new FlatXmlDataSetBuilder().build(new File(
					COMPANIES_XML)));
		} catch (Exception e) {
			fail("Can't load data");
		}
		Company c = new Company(1,"Test");
		final Company company = CompanyDaoImpl.getInstance().update(c);
		assertNotNull(company);
		assertEquals(company.getId(), c.getId());
		assertEquals(company.getName(), c.getName());
	}

	@Test
	public void testFindAllWithoutData() {
		try {
			H2Util.cleanlyInsert(new FlatXmlDataSetBuilder().build(new File(
					EMPTY_XML)));
		} catch (Exception e) {
			fail("Can't load data");
		}
		final List<Company> companies = CompanyDaoImpl.getInstance().findAll();
		assertTrue(companies.isEmpty());
	}

	@Test
	public void testFindAllWithData() {
		final List<Company> companiesExpected = new ArrayList<>();
		companiesExpected.add(new Company(1, "IBM"));
		companiesExpected.add(new Company(2, "Dell"));
		companiesExpected.add(new Company(3, "Apple Inc."));
		try {
			H2Util.cleanlyInsert(new FlatXmlDataSetBuilder().build(new File(
					COMPANIES_XML)));
		} catch (Exception e) {
			fail("Can't load data");
		}
		final List<Company> companies = CompanyDaoImpl.getInstance().findAll();
		assertEquals(companiesExpected, companies);
	}

	@Test
	public void testFindAllWithParamWithData() {
		final List<Company> companiesExpected = new ArrayList<>();
		companiesExpected.add(new Company(1, "IBM"));
		companiesExpected.add(new Company(2, "Dell"));
		try {
			H2Util.cleanlyInsert(new FlatXmlDataSetBuilder().build(new File(
					COMPANIES_XML)));
		} catch (Exception e) {
			fail("Can't load data");
		}
		final List<Company> companies = CompanyDaoImpl.getInstance().findAll(0,2);
		assertEquals(2, companies.size());
		// assertEquals(companiesExpected, companies);
	}
}