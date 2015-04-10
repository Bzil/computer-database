package com.excilys.cdb.persistence;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test-context.xml" })
public class CompanyDaoTest {

	// private static final String COMPANIES_XML =
	// "src/test/resources/company/company.xml";
	// private static final String EMPTY_XML =
	// "src/test/resources/company/company.empty.xml";
	//
	// @Autowired
	// private CompanyDao companyDao;
	//
	// @Autowired
	// private DaoManager manager;
	//
	// @BeforeClass
	// public static void init() {
	// try {
	// H2dbManager.executeSqlFile(H2dbManager.getConnection());
	// } catch (IOException | SQLException e) {
	// System.err.println(e.getMessage());
	// }
	// }
	//
	// @After
	// public void tearDown() throws Exception {
	// H2dbManager.dbTester.onTearDown();
	// }
	//
	// @Test
	// public void testFindWithWrongValue() {
	// try {
	// H2dbManager.cleanlyInsert(new FlatXmlDataSetBuilder()
	// .build(new File(COMPANIES_XML)));
	// } catch (Exception e) {
	// fail("Can't load data");
	// }
	// final Company company = companyDao.find(-1);
	// assertNull(company);
	// }
	//
	// @Test
	// public void testFind() {
	// try {
	// H2dbManager.cleanlyInsert(new FlatXmlDataSetBuilder()
	// .build(new File(COMPANIES_XML)));
	// } catch (Exception e) {
	// fail("Can't load data");
	// }
	// final Company company = companyDao.find(3);
	// assertEquals(3, company.getId());
	// assertEquals("Apple Inc.", company.getName());
	// }
	//
	// @Test
	// public void testFindWhitoutData() {
	// try {
	// H2dbManager.cleanlyInsert(new FlatXmlDataSetBuilder()
	// .build(new File(EMPTY_XML)));
	// } catch (Exception e) {
	// fail("Can't load data");
	// }
	// final Company company = companyDao.find(3);
	// assertNull(company);
	// }
	//
	// @Test
	// public void testFindAllWithoutData() {
	// try {
	// H2dbManager.cleanlyInsert(new FlatXmlDataSetBuilder()
	// .build(new File(EMPTY_XML)));
	// } catch (Exception e) {
	// fail("Can't load data");
	// }
	// final List<Company> companies = companyDao.findAll();
	// assertTrue(companies.isEmpty());
	// }
	//
	// @Test
	// public void testFindAllWithData() {
	// final List<Company> companiesExpected = new ArrayList<>();
	// companiesExpected.add(new Company(1, "IBM"));
	// companiesExpected.add(new Company(2, "Dell"));
	// companiesExpected.add(new Company(3, "Apple Inc."));
	// try {
	// H2dbManager.cleanlyInsert(new FlatXmlDataSetBuilder()
	// .build(new File(COMPANIES_XML)));
	// } catch (Exception e) {
	// fail("Can't load data");
	// }
	// final List<Company> companies = companyDao.findAll();
	// assertEquals(companiesExpected, companies);
	// }
	//
	// @Test
	// public void testFindAllWithParamWithData() {
	// final List<Company> companiesExpected = new ArrayList<>();
	// companiesExpected.add(new Company(1, "IBM"));
	// companiesExpected.add(new Company(2, "Dell"));
	// try {
	// H2dbManager.cleanlyInsert(new FlatXmlDataSetBuilder()
	// .build(new File(COMPANIES_XML)));
	// } catch (Exception e) {
	// fail("Can't load data");
	// }
	// final List<Company> companies = companyDao.findAll(0, 2);
	// assertEquals(2, companies.size());
	// assertEquals(companiesExpected, companies);
	// }
}