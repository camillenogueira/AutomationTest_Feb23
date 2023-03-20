import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Test Scenario - Login")
public class Login_Functionality {
	
	public static int count = 1;
	
	@BeforeAll
	public static void beforeALL() {
		//---------------------------
		//Setup Environment  --------
		//---------------------------
		//Set environment variable
		System.setProperty("webdriver.http.factory", "jdk-http-client");
		
		//WebDriverManager will setup the chrome browser 
		WebDriverManager.chromedriver().setup();
		
		//Inicialize our virtual Browser
		TestData.driver = new ChromeDriver();
	}
	
	@BeforeEach
	public void beforeEach() {
		//block of code to be executed before each test case
		 
	}
	
	@AfterAll
	public static void afterAll() {
		//TestData.driver.quit(); 
		//driver.close();
	}
	
	/*@AfterEach
	public static void afterEach() {
		//block of code to be executed after each test case
		 * 
		 * 
	}*/
	
	
	@Test
	@Order(1)
	@DisplayName("Check results on entering valid User Id & Password.")
	public void tc001() throws InterruptedException, IOException {	
		
		//---------------------------
		//Test Steps ----------------
		//---------------------------
		
		//Open the website
		TestData.driver.get("https://demo.guru99.com/v4/index.php");
		Thread.sleep(4000);
		
		//Close the iframe - Privacy Police
		
		if (count==1) {
			System.out.println("it is here - TC001");
			TestData.driver.switchTo().frame("gdpr-consent-notice").findElement(By.id("save")).click();
			count--;
			Thread.sleep(4000);
		} 
		
		//Enter UserID
		TestData.driver.findElement(By.name("uid")).sendKeys(TestData.userID_Manager);
		
		//Enter Password
		TestData.driver.findElement(By.name("password")).sendKeys(TestData.password_Manager);
		
		//Click on Submit
		TestData.driver.findElement(By.name("btnLogin")).click();
		
		
		//----------------------------------------------
		//Expected Results: Check the welcome message
		//----------------------------------------------
		
		String expectedResults = "Welcome To Manager's Page of Guru99 Bank";
		String actualResults = TestData.driver.findElement(By.cssSelector("body > table > tbody > tr > td > table > tbody > tr:nth-child(2) > td > marquee")).getText();
		
		assertEquals(expectedResults,actualResults);
		
		System.out.println("TC001 - Test Passed!");
		
		//Take Screenshot
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime now = LocalDateTime.now();
		//System.out.println(dtf.format(now));
		File shot = ((TakesScreenshot)TestData.driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(shot, new File("/Users/camillenogueira/Downloads/loginTC001"+dtf.format(now)+".jpg"));
		
		
	}
	
	
	@Test
	@Order(2)
	@DisplayName("Check response when a User ID is Empty & Login Button is pressed")
	public void tc002() throws InterruptedException {
		
		//Test Steps
		
		//Open the URL GURU99
		TestData.driver.get("https://demo.guru99.com/v4/index.php");
		Thread.sleep(4000);
		
		//Close the iframe - Privacy Police
		if (count==1) {
			System.out.println("it is here - TC002");
			TestData.driver.switchTo().frame("gdpr-consent-notice").findElement(By.id("save")).click();
			count--;
			Thread.sleep(4000);
		}
		
		//Leave UserID and password empty
		
		//Click on Submit
		TestData.driver.findElement(By.name("btnLogin")).click();
		
		
		//Check Results
		String expectedResults = "User or Password is not valid";
		String actualResults = TestData.driver.switchTo().alert().getText();
		
		//System.out.println(actualResults);
		
		assertEquals(expectedResults,actualResults);
		//assertTrue(actualResults.equals(expectedResults));
		TestData.driver.switchTo().alert().accept();
		
		System.out.println("TC002 - Test Passed!");
		
		
	}
	
	@Test
	@Order(3)
	@DisplayName("Check results after click on reset button")
	public void tc003() throws InterruptedException {
		
		//Open the URL GURU99
		TestData.driver.get("https://demo.guru99.com/v4/index.php");
		Thread.sleep(4000);
		
		//Close the iframe - Privacy Police
		if (count==1) {
			System.out.println("it is here - TC003");
			TestData.driver.switchTo().frame("gdpr-consent-notice").findElement(By.id("save")).click();
			count--;
			Thread.sleep(4000);
		}
		
		boolean checkEmpty = TestData.driver.findElement(By.name("uid")).getText().isBlank();
		
		assertTrue(checkEmpty);
		
	}

}
