package HrmTest.HrmTest;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;



public class HRM {

	//	Give the base URL
	public String url = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";

	//	Give driver 
	public WebDriver driver;

	//	Setup the necessary things 

	@BeforeTest
	public void setup() {

		driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.get(url);

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));

	}

	//	Start Test Cases Here 

	@Test

	public void login() {
		
		driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("Admin");
		
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("admin123");
		
		driver.findElement(By.xpath("//button[@type='submit']")).click();

	
	
	String pageTitle = driver.getTitle();
	
/*	if (pageTitle.equals("OrangeHRM")) {
	
		System.out.println("Your test case is passed");
	} else {
	
		System.out.println("Your test case is failed");
	} */
	
//	we can also use assertions of validate test 
	

	Assert.assertEquals("OrangeHRM", pageTitle);
	
	driver.findElement(By.xpath("//span[@class='oxd-userdropdown-tab']")).click();
	
	driver.findElement(By.xpath("//a[normalize-space()='Logout']")).click();
	
	}

	@AfterTest 

	public void tearDown() throws InterruptedException {

		Thread.sleep(5000);
		driver.close();
		driver.quit();
	}



}
