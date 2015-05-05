package com.excilys.cdb.persistence;

import com.excilys.ebi.spring.dbunit.test.DataSet;
import com.excilys.ebi.spring.dbunit.test.ExpectedDataSet;
import com.excilys.ebi.spring.dbunit.test.RollbackTransactionalDataSetTestExecutionListener;
import com.excilys.cdb.model.Company;
import junit.framework.TestCase;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:persistence-test-context.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        RollbackTransactionalDataSetTestExecutionListener.class })
@TransactionConfiguration

@Transactional
public class CompanyDaoTest extends TestCase {
        @Autowired
        CompanyDao companyDAO;

        @Test
        @DataSet("company_getAllNotEmpty.xml")
        public void getAllWhenNotEmpty() {
                // GIVEN
                final int expectedSize = 3;
                final List<Company> expectedCompanies = Arrays.asList(
                        Company.builder().id(1).name("company1").build(),
                        Company.builder().id(2).name("company2").build(),
                        Company.builder().id(3).name("company3").build()
                );

                // WHEN
                final List<Company> companies = companyDAO.findAll();

                // THEN
                Assertions.assertThat(companies.size()).isEqualTo(expectedSize);
                Assertions.assertThat(companies).containsAll(expectedCompanies);
        }

        @Test
        @DataSet("company_getAllEmpty.xml")
        public void getAllWhenEmpty() {
                // GIVEN

                // WHEN
                final List<Company> companies = companyDAO.findAll();

                // THEN
                Assertions.assertThat(companies).isNotNull();
                Assertions.assertThat(companies).isEmpty();
        }

        @Test
        @DataSet("company_getById.xml")
        public void getByIdSuccess() {
                // GIVEN
                final int expectedId = 1;
                final Company expectedCompany = Company.builder().id(1)
                        .name("company1").build();

                // WHEN
                final Company company = companyDAO.find(expectedId);

                // THEN
                Assertions.assertThat(company).isEqualTo(expectedCompany);
        }


        @Test
        public void getByIdWhenWrongIdThrowsException() {
                // GIVEN

                // WHEN
                try {
                        Company c = companyDAO.find(-1);
                        Assertions.assertThat(c).isNull();
                } catch (Exception ex) {
                        // THEN OK
                        Assertions.assertThat(ex).isInstanceOf(NullPointerException.class);
                }
        }

        @Test
        public void getByIdReturnsNullWhenNotExistsID() {
                // GIVEN

                // WHEN
                final Company company = companyDAO.find(7879465);

                // THEN
                Assertions.assertThat(company).isNull();
        }

        @Test
        @DataSet("company_delete.xml")
        @ExpectedDataSet("company_empty.xml")
        public void deleteSuccess() {
                // GIVEN
                final int id = 1;

                // WHEN
                companyDAO.delete(id);
        }

        @Test
        @DataSet("company_delete.xml")
        @ExpectedDataSet("company_delete.xml")
        public void deleteWhenNullId() {
                // GIVEN

                // WHEN
                companyDAO.delete(-1);

        }

        @Test
        @DataSet("company_delete.xml")
        @ExpectedDataSet("company_delete.xml")
        public void deleteWhenWrongIdThrowsException() {
                // GIVEN

                // WHEN
                        companyDAO.delete(-1);


        }

        @Test
        @DataSet("company_delete.xml")
        @ExpectedDataSet("company_delete.xml")
        public void deleteSuccessWhenNotExistsID() {
                // GIVEN

                // WHEN
                companyDAO.delete(79465);
        }

}