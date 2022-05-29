package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Textbox_TextArea {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecute;
	String userID, name, password, gender, pin, phone, email, loginURL;
	String addressIn, addressOut, dobIn, dobOut, city, state, customerID;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		jsExecute = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://demo.guru99.com/v4/");
		
		loginURL = driver.getCurrentUrl();

		userID = "";
		name = "Le Tien";
		password = "";
		email = "tien" + RandomEmail() + "@vmail.net";
		gender = "female";
		pin = "123789";
		phone = "0123456789";
		customerID = "";
		addressIn = "20 DT743\nWard 4";
		addressOut = "20 DT743 Ward 4";
		dobIn = "12/08/1992";
		dobOut = "1992-12-08";
		city = "Hun";
		state = "Michigan";
	}

	@Test
	public void TC_01_Register() {
		driver.findElement(By.xpath("//a[text()='here']")).click();
		
		driver.findElement(By.name("emailid")).sendKeys(email);
		driver.findElement(By.name("btnLogin")).click();
		
		userID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
	}

	@Test
	public void TC_02_Login() {
		driver.get(loginURL);
		driver.findElement(By.name("uid")).sendKeys(userID);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.name("btnLogin")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//tr[@class='heading3']//td")).getText(), "Manger Id : " + userID);
	}

	@Test
	public void TC_03_Create_New_Customer() {
		
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		
		// Input
		driver.findElement(By.name("name")).sendKeys(name);
		driver.findElement(By.xpath("//input[@value='f']")).click();
		
		jsExecute.executeScript("arguments[0].removeAttribute('type')", driver.findElement(By.name("dob")));
		
		driver.findElement(By.name("dob")).sendKeys(dobIn);
		driver.findElement(By.name("addr")).sendKeys(addressIn);
		driver.findElement(By.name("city")).sendKeys(city);
		driver.findElement(By.name("state")).sendKeys(state);
		driver.findElement(By.name("pinno")).sendKeys(pin);
		driver.findElement(By.name("telephoneno")).sendKeys(phone);
		driver.findElement(By.name("emailid")).sendKeys(email);
		driver.findElement(By.name("password")).sendKeys(password);
		
		driver.findElement(By.xpath("//input[@value='Submit']")).click();
		
		// Output
		
		Assert.assertEquals(driver.findElement(By.xpath("//p[@class='heading3']")).getText(), "Customer Registered Successfully!!!");
		
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), name);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(), gender);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), dobOut);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), addressOut);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), pin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), phone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), email);
		
	}


	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public int RandomEmail() {
		Random rand = new Random();
		return rand.nextInt(99999);
	}

	public void sleepInsecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}