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

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

	}
	
	public void login(){
		
		driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("Admin");

		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("admin123");

		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
	}

	public void logOut() {
		
		driver.findElement(By.xpath("//span[@class='oxd-userdropdown-tab']")).click();

		driver.findElement(By.xpath("//a[normalize-space()='Logout']")).click();
	}
	
	//	Start Test Cases Here 

	@Test (priority = 1)

	public void invalidLogin() {

		driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("Admin");

		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("123");

		driver.findElement(By.xpath("//button[@type='submit']")).click();

		String ErrorMessage = "Invalid credentials";

		String mesageAct = driver.findElement(By.xpath("//p[@class='oxd-text oxd-text--p oxd-alert-content-text']")).getText();

		Assert.assertTrue(ErrorMessage.contains(mesageAct));
	}
	
//	Check with valid credential 

	@Test (priority = 2)

	public void loginValid() {

		login();

		String pageTitle = driver.getTitle();

		/*	if (pageTitle.equals("OrangeHRM")) {

		System.out.println("Your test case is passed");
	} else {

		System.out.println("Your test case is failed");
	} */

		//	we can also use assertions of validate test 


		Assert.assertEquals("OrangeHRM", pageTitle);
		
		logOut();
		
	}
	
	
//	Add Employee 
	
	@Test (priority = 3)
	
	public void addEmployee() throws InterruptedException {
		
//		For Login 
		
		login();
		
		Thread.sleep(3000);
		
//		For Add Employee 
		
		driver.findElement(By.xpath("//span[text()='PIM']")).click();

		driver.findElement(By.xpath("//a[text()='Add Employee']")).click();
		
		driver.findElement(By.xpath("//input[@placeholder='First Name']")).sendKeys("Ahmad");
		
		driver.findElement(By.xpath("//input[@placeholder='Last Name']")).sendKeys("Ch");
		
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
//		String detailPage = "Personal Details";

		String mesageAct = driver.findElement(By.xpath("//h6[normalize-space()='Personal Details']")).getText();

		Assert.assertEquals("Personal Details", mesageAct);
		
//		Assert.assertTrue(detailPage.contains(mesageAct));
		
		
		
//		For Logout
		
		logOut();
		
	}
	
	
	@Test (priority = 4) 
		
		public void searchEmployee() throws InterruptedException {
		
		login();
		
		driver.findElement(By.xpath("//span[@class='oxd-text oxd-text--span oxd-main-menu-item--name'][normalize-space()='PIM']")).click();
		
		driver.findElement(By.xpath("//a[normalize-space()='Employee List']")).click();
		
		driver.findElement(By.xpath("//div[@class='oxd-grid-4 orangehrm-full-width-grid']//div[1]//div[1]//div[2]//div[1]//div[1]//input[1]")).sendKeys("ahmad");
		
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		Thread.sleep(3000);
		
		String searchName = driver.findElement(By.xpath("//div[@role='rowgroup']//div[1]//div[1]//div[3]//div[1]")).getText();
		
		Assert.assertEquals("Ahmad", searchName);
		
		logOut();
	}
	

	@AfterTest 

	public void tearDown() throws InterruptedException {

		
		Thread.sleep(5000);

		driver.close();
		driver.quit();
	}



}
