package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Xpath_CSS_Part_III {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("");
	}

	@Test
	public void TC_01() {
		
		driver.findElement(By.xpath("//li[6]"));
		driver.findElement(By.xpath("//li[last()]"));
		
		driver.findElement(By.xpath("//span[text()='Add to Cart'])[1]"));
		driver.findElement(By.xpath("//span[text()='Add to Cart'])[2]"));
		driver.findElement(By.xpath("//span[text()='Add to Cart'])[3]"));
		driver.findElement(By.xpath("//span[text()='Add to Cart'])[last()]"));
	}

	@Test
	public void TC_02_() {
		
		driver.findElement(By.xpath("//a[text()='IPhone']/parent::h2/following-sibling::div[@class='actions']/button"));
		
		driver.findElement(By.xpath("//a[text()='IPhone']/parent::h2/following-sibling::div//*[@class='price']/ancestor::div[contains(@class,'products')]/div"));
		
		driver.findElement(By.xpath("//div[text()='The Complete Coding Interview Guide in Java']/ancestor::*[contains(@target,'blank')]/child::*/following-sibling::*"));
		
		driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td"));
		
		driver.findElement(By.xpath("//span[text()='Total']/preceding-sibling::span"));
	}

	@Test
	public void TC_03_() {
		
	}
	
	@Test
	public void TC_04_() {
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}