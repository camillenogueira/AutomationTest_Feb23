import static org.junit.Assert.assertEquals;

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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Test Scenario - Login")
public class Login_Functionality {
	
	//Declaration of the object webdriver
	public static WebDriver driver = null;
	
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
		driver = new ChromeDriver();
	}
	
	/*@BeforeEach
	public static void beforeEach() {
		//block of code to be executed before each test case
	}*/
	
	@AfterAll
	public static void afterAll() {
		driver.quit(); 
		//driver.close();
	}
	
	/*@AfterEach
	public static void afterEach() {
		//block of code to be executed after each test case
	}*/
	
	
	@Test
	@Order(1)
	@DisplayName("Check results on entering valid User Id & Password.")
	public void tc001() throws InterruptedException {	
		
		//---------------------------
		//Test Steps ----------------
		//---------------------------
		
		//Open the website
		driver.get("https://demo.guru99.com/v4/index.php");
		Thread.sleep(4000);
		
		//Close the iframe - Privacy Police
		driver.switchTo().frame("gdpr-consent-notice").findElement(By.id("save")).click();
		Thread.sleep(4000);
		
		//Enter UserID
		driver.findElement(By.name("uid")).sendKeys("mngr483896");
		
		//Enter Password
		driver.findElement(By.name("password")).sendKeys("azyjYry");
		
		//Click on Submit
		driver.findElement(By.name("btnLogin")).click();
		
		
		//----------------------------------------------
		//Expected Results: Check the welcome message
		//----------------------------------------------
		
		String expectedResults = "Welcome To Manager's Page of Guru99 Bank";
		String actualResults = driver.findElement(By.cssSelector("body > table > tbody > tr > td > table > tbody > tr:nth-child(2) > td > marquee")).getText();
		
		assertEquals(expectedResults,actualResults);
		
	}
	
	
	@Test
	@Order(2)
	@DisplayName("Check response when a User ID is Empty & Login Button is pressed")
	public void tc002() {
		
		
	}
	
	@Test
	@Order(3)
	@DisplayName("Check results after you add new customer.")
	public void tc003() {
		
		
	}

}
