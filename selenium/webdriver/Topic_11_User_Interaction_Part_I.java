package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_User_Interaction_Part_I {
	WebDriver driver;
	Actions action;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Hover_Tooltip() {
		driver.get("https://automationfc.github.io/jquery-tooltip/");
		
		action.moveToElement(driver.findElement(By.id("age"))).perform();
		sleepInsecond(2);
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='ui-tooltip-content']")).getText(), "We ask for your age only for statistical purposes.");
	}
	
	@Test
	public void TC_02_Hover_Myntra() {
		driver.get("https://www.myntra.com/");
		
		action.moveToElement(driver.findElement(By.xpath("//header[@id='desktop-header-cnt']//a[text()='Kids']"))).perform();
		sleepInsecond(2);
		
		action.click(driver.findElement(By.xpath("//a[text()='Home & Bath']"))).perform();
		sleepInsecond(2);
		
		Assert.assertEquals(driver.findElement(By.xpath("//h1[text()='Kids Home Bath']")).getText(), "Kids Home Bath");
	}
	
	@Test
	public void TC_03_Hover_Lazada() {
		driver.get("https://www.lazada.vn/");
		
		action.moveToElement(driver.findElement(By.xpath("//span[text()='Electronic Devices']"))).perform();
		sleepInsecond(2);
		action.moveToElement(driver.findElement(By.xpath("//span[text()='Laptops']"))).perform();
		sleepInsecond(2);
		
		action.click(driver.findElement(By.xpath("//span[text()='Traditional Laptops']"))).perform();
		sleepInsecond(2);
		
		Assert.assertEquals(driver.findElement(By.xpath("//h1[text()='Laptop Cơ Bản']")).getText(), "Laptop Cơ Bản");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public void sleepInsecond (long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}