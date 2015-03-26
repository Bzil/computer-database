package com.excilys.cdb.ui;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class AddTest {

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

	@Test
	public void testAddByDashBoard() {
		baseUrl += "dashboard";
		driver.get(baseUrl);
		// Go to add page
		driver.findElement(By.id("addComputer")).click();
		// Put value into field
		driver.findElement(By.id("computerName")).sendKeys("testSelenium");

		driver.findElement(By.tagName("form")).submit();
		driver.close();
	}

	@Test
	public void testAddWithSpecificUrl() {
		baseUrl += "add";
		driver.get(baseUrl);
		// Put value into field
		driver.findElement(By.id("computerName")).sendKeys("testSelenium");

		driver.findElement(By.tagName("form")).submit();
		driver.close();
	}
	
	@Test
	public void testAddWithSpecificUrlWrongDate() {
		baseUrl += "add";
		driver.get(baseUrl);
		// Put value into field
		driver.findElement(By.id("computerName")).sendKeys("testSelenium");
		driver.findElement(By.id("introduced")).sendKeys("testSelenium");
		
		driver.findElement(By.tagName("form")).submit();
		driver.close();
	}
	
	@Test
	public void testAddWithSpecificUrlWhithoutName() {
		baseUrl += "add";
		driver.get(baseUrl);
		// Put value into field
		driver.findElement(By.id("computerName")).sendKeys("");

		driver.findElement(By.tagName("form")).submit();
		driver.close();
	}
}
