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

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));

	}

	//	Start Test Cases Here 

	@Test (priority = 2)

	public void loginValid() {

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

		

	}
	
	public void logout() {
		
		driver.findElement(By.xpath("//span[@class='oxd-userdropdown-tab']")).click();

		driver.findElement(By.xpath("//a[normalize-space()='Logout']"));
		
	}

	@Test (priority = 1)

	public void invalidLogin() {

		driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("Admin");

		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("123");

		driver.findElement(By.xpath("//button[@type='submit']")).click();

		String ErrorMessage = "Invalid credentials";

		String mesageAct = driver.findElement(By.xpath("//p[@class='oxd-text oxd-text--p oxd-alert-content-text']")).getText();

		Assert.assertTrue(ErrorMessage.contains(mesageAct));
	}

	@AfterTest 

	public void tearDown() throws InterruptedException {


		
		logout();
		
		Thread.sleep(5000);
		driver.close();
		driver.quit();
	}



}
