package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_Wait_Part_I {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	WebDriverWait explicitWait;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver, 15);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://www.facebook.com/");
	}

	@Test
	public void TC_01_Visible() {
		//Có trên UI và có trong DOM/HTML
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='email']")));
		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='email']")).isDisplayed());
	}
	
	@Test
	public void TC_02_Invisible_In_DOM() {
		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
		sleepInsecond(2);
		//Không có trên UI nhưng có trong DOM
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
		Assert.assertFalse(driver.findElement(By.xpath("//input[@name='reg_email_confirmation__']")).isDisplayed());
	}
	
	@Test
	public void TC_02_Invisible_Not_In_DOM() {
		driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
		sleepInsecond(2);
		//Không có trên UI và cũng không có trong DOM
		//Chạy mất 15s
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
	}
	
	@Test
	public void TC_03_Presence() {
		//Có trong DOM và có trên UI
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='email']")));
		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='email']")).isDisplayed());
		
		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
		sleepInsecond(2);
		
		//Có trong DOM nhưng không có trên UI
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
	}
	
	@Test
	public void TC_04_Staleness() {
		//Không có trong DOM và không có trên UI
		//1.Bật popup lên
		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
		sleepInsecond(2);
		
		//2. Tìm là lưu Element
		WebElement regEmailConfirmation = driver.findElement(By.xpath("//input[@name='reg_email_confirmation__']"));
		
		//3. Đóng popup đi
		driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
		sleepInsecond(2);
		
		//4. Wait Element ở trạng thái Staleness
		explicitWait.until(ExpectedConditions.stalenessOf(regEmailConfirmation));
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