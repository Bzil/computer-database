package com.excilys.cdb.ui;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

@Ignore
public class EditTest {

	private WebDriver driver;
	private String baseUrl;

	@Before
	public void setUp() {
		driver = new FirefoxDriver();
		baseUrl = "http://localhost:8080/cdb/";
	}

	@After
	public void close() {
		driver.close();
	}

	@Ignore
	public void testEditFromDashBoard() {
		baseUrl += "dashboard";
		driver.get(baseUrl);
		// Put value into field
		driver.findElement(By.id("name_1")).click();
		driver.findElement(By.id("computerName")).sendKeys("testSelenium");

		driver.findElement(By.tagName("form")).submit();
		driver.close();
	}

	@Test
	public void testEditWithSpecificUrl() {
		baseUrl += "edit?id=10";
		driver.get(baseUrl);
		// Put value into field
		driver.findElement(By.id("computerName")).sendKeys("testSelenium");

		driver.findElement(By.tagName("form")).submit();
		driver.close();
	}

	@Ignore
	public void testEditWithSpecificUrlWhitoutParam() {
		baseUrl += "edit";
		driver.get(baseUrl);

		driver.findElement(By.tagName("form")).submit();
		driver.close();
	}

	@Test
	public void testEditWithSpecificUrlWrongDate() {
		baseUrl += "edit?id=10";
		driver.get(baseUrl);
		// Put value into field
		driver.findElement(By.id("computerName")).sendKeys("testSelenium");
		driver.findElement(By.id("introduced")).sendKeys("testSelenium");

		driver.findElement(By.tagName("form")).submit();
		driver.close();
	}

	@Test
	public void testEditWithSpecificUrlWhithoutName() {
		baseUrl += "edit?id=10";
		driver.get(baseUrl);
		// Put value into field
		driver.findElement(By.id("computerName")).sendKeys("");

		driver.findElement(By.tagName("form")).submit();
		driver.close();
	}
}
