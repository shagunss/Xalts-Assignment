package tests;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;
import java.util.UUID;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

public class NodeOCNAdding {
	
	WebDriver driver;
		@BeforeTest
		public void setup() {
		WebDriverManager.chromedriver().setup();

		// Create an instance of ChromeDriver
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // inserted implicitly wait preventing for
																			// no_such_element_exception in any case

		// navigate to URL
		driver.get("https://xaltsocnportal.web.app/");
		}
		
		@Test(priority=1)
		public void signup_and_signin() {
		driver.findElement(By.xpath("//button[contains(text(), 'Sign In')]")).click(); // click on Sign in button

		
		String email = "shagun" + UUID.randomUUID().toString().substring(0, 5) + "@gmail.com";

		driver.findElement(By.xpath("//input[@type='text']")).sendKeys(email);
		driver.findElement(By.xpath("(//input[@type='password'])[1]")).sendKeys("Shagun@123");
		

		driver.findElement(By.xpath("(//input[@type='password'])[2]")).sendKeys("Shagun@123" + Keys.ENTER);// confirm
																											// password
		driver.findElement(By.xpath("//button[contains(text(), 'Sign Up')]")).click(); // click on sign up button

		driver.findElement(By.xpath("//button[contains(text(), 'Sign Out')]")).click();
		
		
		driver.findElement(By.xpath("//button[contains(text(), 'Sign In')]")).click();
		driver.findElement(By.xpath("//button[contains(text(), 'Already have an account? Click here to sign in.')]"))
				.click();
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys(email);
		driver.findElement(By.xpath("(//input[@type='password'])[1]")).sendKeys("Shagun@123" + Keys.ENTER);
		driver.findElement(By.xpath("//button[contains(text(), 'Sign In')]")).click();
		
		}
		@Test(priority=2)
		public void navigate_to_Onboard_OCN_Node() {
		driver.findElement(By.xpath("//button[contains(text(), 'Get Started')]")).click();
		driver.findElement(By.xpath("//h2[normalize-space()='Onboard OCN Node']")).click();
		}
		
		@Test(priority=3)
		public void adding_nodes() {
		for(int i=1;i<=3;i++) {
		driver.findElement(By.xpath("(//input[@type='text'])[1]")).sendKeys("NodeID-"+i+" ");
		driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys("192.168.1."+i+"");
		
		driver.findElement(By.xpath("//button[contains(text(), '+ Add Node ')]")).click();
		
		}
		
		driver.findElement(By.xpath("(//input[@type='checkbox'])[1]")).click();
		
		driver.findElement(By.xpath("//button[normalize-space() = 'Next']")).click();
		}
		
		@Test(priority=4)
		public void adding_wallet() {
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys("0x88fa61d2faA13aad8Fbd5B030372B4A159BbbDFb");
		driver.findElement(By.xpath("//button[contains(text(), '+ Add Wallet ')]")).click();
		driver.findElement(By.xpath("(//input[@type='checkbox'])[1]")).click();
		
		driver.findElement(By.xpath("//button[normalize-space() = 'Next']")).click();
		}
		
		@Test(priority=5)
		public void submit_request_and_Logout() {
		
		driver.findElement(By.xpath("//button[normalize-space() = 'Submit']")).click();
		
		driver.findElement(By.xpath("//button[contains(text(), 'Sign Out')]")).click();
		}
		
		@AfterTest()
		public void teardown() throws Exception {
			Thread.sleep(3000);
			driver.quit();
		}
	
}
