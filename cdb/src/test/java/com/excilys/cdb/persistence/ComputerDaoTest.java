package com.excilys.cdb.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import com.excilys.cdb.manage.H2dbManager;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.persistence.impl.ComputerDaoImpl;

public class ComputerDaoTest {
//	private static final String COMPUTERS_XML = "src/test/resources/computer/computer.xml";
//	private static final String EMPTY_XML = "src/test/resources/computer/computer.empty.xml";
//
//	@BeforeClass
//	public static void setUp() {
//		DaoManager.INSTANCE.propertiesFile = "/h2.properties";
//		DaoManager.INSTANCE.driver = "org.h2.Driver";
//		
//		try {
//			H2dbManager.executeSqlFile(H2dbManager.getConnection());
//		} catch (IOException | SQLException e) {
//			System.err.println(e.getMessage());
//		}
//	}
//
//	@After
//	public void tearDown() throws Exception {
//		H2dbManager.dbTester.onTearDown();
//	}
//
//	@Test
//	public void testFindWithWrongValue() {
//		try {
//			H2dbManager.cleanlyInsert(new FlatXmlDataSetBuilder().build(new File(
//					COMPUTERS_XML)));
//		} catch (Exception e) {
//			fail("Can't load data");
//		}
//		final Computer computer = ComputerDaoImpl.getInstance().find(-1);
//		assertNull(computer);
//	}
//
//	@Test
//	public void testFind() {
//		final Computer computerExpeted = new Computer(1, "TEST",
//				LocalDateTime.of(1991, 01, 01, 00, 00, 00), LocalDateTime.of(
//						1993, 02, 01, 00, 00, 00), new Company(1, "Apple Inc."));
//		try {
//			H2dbManager.cleanlyInsert(new FlatXmlDataSetBuilder().build(new File(
//					COMPUTERS_XML)));
//		} catch (Exception e) {
//			fail("Can't load data");
//		}
//		final Computer computer = ComputerDaoImpl.getInstance().find(1);
//		assertEquals(computerExpeted, computer);
//	}
//	
//	@Test
//	public void testFindByName() {
//		final List<Computer> computersExpeted = new ArrayList<>();
//		computersExpeted.add(new Computer(1, "TEST",
//				LocalDateTime.of(1991, 01, 01, 00, 00, 00), LocalDateTime.of(
//						1993, 02, 01, 00, 00, 00), new Company(1, "Apple Inc.")));
//		computersExpeted.add(new Computer(2, "TEST2",
//				LocalDateTime.of(1992, 01, 01, 00, 00, 00), LocalDateTime.of(
//						1993, 02, 01, 00, 00, 00), new Company(1, "Apple Inc.")));
//		try {
//			H2dbManager.cleanlyInsert(new FlatXmlDataSetBuilder().build(new File(
//					COMPUTERS_XML)));
//		} catch (Exception e) {
//			fail("Can't load data");
//		}
//		final List<Computer> computers = ComputerDaoImpl.getInstance().find("TEST", null);
//		assertEquals(computersExpeted, computers);
//	}
//
//	@Test
//	public void testFindWhitoutData() {
//		try {
//			H2dbManager.cleanlyInsert(new FlatXmlDataSetBuilder().build(new File(
//					EMPTY_XML)));
//		} catch (Exception e) {
//			fail("Can't load data");
//		}
//		final Computer computer = ComputerDaoImpl.getInstance().find(3);
//		assertNull(computer);
//	}
//
//	@Test
//	public void testCountWhitoutData() {
//		try {
//			H2dbManager.cleanlyInsert(new FlatXmlDataSetBuilder().build(new File(
//					EMPTY_XML)));
//		} catch (Exception e) {
//			fail("Can't load data");
//		}
//		final int countExpected = 0;
//		final int count = ComputerDaoImpl.getInstance().count();
//		assertEquals(countExpected, count);
//	}
//
//	@Test
//	public void testCountWhitData() {
//		try {
//			H2dbManager.cleanlyInsert(new FlatXmlDataSetBuilder().build(new File(
//					COMPUTERS_XML)));
//		} catch (Exception e) {
//			fail("Can't load data");
//		}
//		final int countExpected = 2;
//		final int count = ComputerDaoImpl.getInstance().count();
//		assertEquals(countExpected, count);
//	}
//
//	@Test(expected = NullPointerException.class)
//	public void testCreateWithNullData() {
//		try {
//			H2dbManager.cleanlyInsert(new FlatXmlDataSetBuilder().build(new File(
//					COMPUTERS_XML)));
//		} catch (Exception e) {
//			fail("Can't load data");
//		}
//		ComputerDaoImpl.getInstance().create(null);
//	}
//
//	@Test(expected = AssertionError.class)
//	public void testCreateWithoutName() {
//		try {
//			H2dbManager.cleanlyInsert(new FlatXmlDataSetBuilder().build(new File(
//					EMPTY_XML)));
//		} catch (Exception e) {
//			fail("Can't load data");
//		}
//		Computer c = new Computer();
//		final Computer computer = ComputerDaoImpl.getInstance().create(c);
//		assertNull(computer);
//	}
//
//	@Test
//	public void testCreateWithName() {
//		try {
//			try {
//				DaoManager.INSTANCE.getConnection().setAutoCommit(false);
//				H2dbManager.cleanlyInsert(new FlatXmlDataSetBuilder()
//						.build(new File(COMPUTERS_XML)));
//				final Computer c = new Computer("Test");
//				c.setId(3);
//				final Computer computer = ComputerDaoImpl.getInstance().create(
//						c);
//				assertNotNull(computer);
//				assertEquals(c, computer);
//			} catch (Exception e) {
//				fail("Can't load data");
//			} finally {
//				DaoManager.INSTANCE.getConnection().rollback();
//				DaoManager.INSTANCE.getConnection().setAutoCommit(true);
//			}
//		} catch (Exception e) {
//			fail("Can't load data");
//		}
//	}
//
//	@Test(expected = NullPointerException.class)
//	public void testUpdateWhithNullData() {
//		try {
//			H2dbManager.cleanlyInsert(new FlatXmlDataSetBuilder().build(new File(
//					COMPUTERS_XML)));
//		} catch (Exception e) {
//			fail("Can't load data");
//		}
//		final Computer computer = ComputerDaoImpl.getInstance().update(null);
//		assertNull(computer);
//	}
//
//	@Test(expected = AssertionError.class)
//	public void testUpdateWithoutName() {
//		try {
//			H2dbManager.cleanlyInsert(new FlatXmlDataSetBuilder().build(new File(
//					COMPUTERS_XML)));
//		} catch (Exception e) {
//			fail("Can't load data");
//		}
//		Computer c = new Computer();
//		c.setId(3);
//		final Computer computer = ComputerDaoImpl.getInstance().update(c);
//		assertNull(computer);
//	}
//
//	@Test
//	public void testFindAllWithoutData() {
//		try {
//			H2dbManager.cleanlyInsert(new FlatXmlDataSetBuilder().build(new File(
//					EMPTY_XML)));
//		} catch (Exception e) {
//			fail("Can't load data");
//		}
//		final List<Computer> computers = ComputerDaoImpl.getInstance()
//				.findAll(null);
//		assertTrue(computers.isEmpty());
//	}
//
//	@Test
//	public void testFindAllWithData() {
//		final List<Computer> computersExpected = new ArrayList<>();
//		computersExpected.add(new Computer(1, "TEST", LocalDateTime.of(1991,
//				01, 01, 00, 00, 00),
//				LocalDateTime.of(1993, 02, 01, 00, 00, 00), new Company(1,
//						"Apple Inc.")));
//		computersExpected.add(new Computer(2, "TEST2", LocalDateTime.of(1992,
//				01, 01, 00, 00, 00),
//				LocalDateTime.of(1993, 02, 01, 00, 00, 00), new Company(1,
//						"Apple Inc.")));
//		try {
//			H2dbManager.cleanlyInsert(new FlatXmlDataSetBuilder().build(new File(
//					COMPUTERS_XML)));
//		} catch (Exception e) {
//			fail("Can't load data");
//		}
//		final List<Computer> computers = ComputerDaoImpl.getInstance()
//				.findAll(null);
//		assertEquals(computersExpected, computers);
//	}
//
//	@Test
//	public void testFindAllWithParamWithData() {
//		final List<Computer> computersExpected = new ArrayList<>();
//		computersExpected.add(new Computer(1, "TEST", LocalDateTime.of(1991,
//				01, 01, 00, 00, 00),
//				LocalDateTime.of(1993, 02, 01, 00, 00, 00), new Company(1,
//						"Apple Inc.")));
//		try {
//			H2dbManager.cleanlyInsert(new FlatXmlDataSetBuilder().build(new File(
//					COMPUTERS_XML)));
//		} catch (Exception e) {
//			fail("Can't load data");
//		}
//		final List<Computer> computers = ComputerDaoImpl.getInstance().findAll(
//				0, 1, null);
//		assertEquals(1, computers.size());
//		assertEquals(computersExpected, computers);
//	}

}
