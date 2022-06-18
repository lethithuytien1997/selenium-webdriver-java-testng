package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_Fixed_PopUp {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Ngoai_Ngu_24h() {
		driver.get("https://ngoaingu24h.vn/");
		
		By loginPopup = By.xpath("//div[@class='main-banner-panel']//preceding-sibling::div[@id='modal-login-v1']");
		
		Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());
		
		driver.findElement(By.xpath("//button[@class='login_ icon-before']")).click();
		
		Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
		
		driver.findElement(By.xpath("//div[@class='main-banner-panel']//preceding-sibling::div[@id='modal-login-v1']//input[@id='account-input']")).sendKeys("tien123tien");
		driver.findElement(By.xpath("//div[@class='main-banner-panel']//preceding-sibling::div[@id='modal-login-v1']//input[@id='password-input']")).sendKeys("tien123");
		driver.findElement(By.xpath("//div[@class='main-banner-panel']//preceding-sibling::div[@id='modal-login-v1']//button[@class='btn-v1 btn-login-v1 buttonLoading']")).click();
		sleepInsecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='main-banner-panel']//preceding-sibling::div[@id='modal-login-v1']//div[@class='row error-login-panel']")).getText(), "Tài khoản không tồn tại!");
		
		driver.findElement(By.xpath("//div[@class='main-banner-panel']//preceding-sibling::div[@id='modal-login-v1']//button[@class='close']")).click();
		Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());
	}
	
	@Test
	public void TC_02_JT_Express() {
		
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