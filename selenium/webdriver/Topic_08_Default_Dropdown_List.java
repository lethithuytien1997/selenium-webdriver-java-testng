package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Default_Dropdown_List {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	Select select;
	String firstName, lastName, email, company, pass;
	String day, month, year;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		firstName = "Tien";
		lastName = "Le";
		email = "tienle" + RandomEmail() + "@vmail.net";
		company = "CMH CO.OP";
		pass = "123456";
		day = "10";
		month = "June";
		year = "1995";
	}

	@Test
	public void TC_01_Register() {
		driver.get("https://demo.nopcommerce.com/register");
		
		driver.findElement(By.id("FirstName")).sendKeys(firstName);
		driver.findElement(By.id("LastName")).sendKeys(lastName);
		
		//Day
		select = new Select(driver.findElement(By.name("DateOfBirthDay")));
		select.selectByVisibleText(day);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), day);
		
		//Month
		select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		select.selectByVisibleText(month);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), month);
		
		//Year
		select = new Select(driver.findElement(By.name("DateOfBirthYear")));
		select.selectByVisibleText(year);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), year);
		
		driver.findElement(By.id("Email")).sendKeys(email);
		driver.findElement(By.id("Company")).sendKeys(company);
		driver.findElement(By.id("Password")).sendKeys(pass);
		driver.findElement(By.id("ConfirmPassword")).sendKeys(pass);
		
		driver.findElement(By.id("register-button")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='result']")).getText(), "Your registration completed");
	}

	@Test
	public void TC_02_Verify() {
		driver.findElement(By.xpath("//a[@class='ico-account']")).click();
		
		Assert.assertEquals(driver.findElement(By.id("FirstName")).getAttribute("value"), firstName);
		Assert.assertEquals(driver.findElement(By.id("LastName")).getAttribute("value"), lastName);
		
		select = new Select(driver.findElement(By.name("DateOfBirthDay")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), day);
		
		select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), month);
		
		select = new Select(driver.findElement(By.name("DateOfBirthYear")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), year);
		
		Assert.assertEquals(driver.findElement(By.id("Email")).getAttribute("value"), email);
		Assert.assertEquals(driver.findElement(By.id("Company")).getAttribute("value"), company);
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