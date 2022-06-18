package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_Random_Popup {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		jsExecutor = (JavascriptExecutor) driver;
	}

	@Test
	public void TC_01_In_DOM_VNK() {
		driver.get("https://vnk.edu.vn/");
		sleepInsecond(15);
		
		By vnkPopup = By.xpath("//div[@class='tve_p_lb_inner']");
		
		if(driver.findElement(vnkPopup).isDisplayed())
		{
			jsExecutor.executeScript("arguments[0].click()", driver.findElement(By.cssSelector("div.tcb-icon-display")));
			sleepInsecond(2);
			Assert.assertFalse(driver.findElement(vnkPopup).isDisplayed());
		}
		
		driver.findElement(By.xpath("//a[@title='Liên hệ']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//h1[text()='Liên hệ']")).getText(), "Liên Hệ");
	}
	
	@Test
	public void TC_02_Not_In_DOM_DeHieu() {
		driver.get("https://dehieu.vn/");
		sleepInsecond(5);
		
		By dangkyPopup = By.xpath("//div[@class='popup-content']");
		
		//Tìm Element theo dạng List để có thể dùng thuộc tính size để Verify dữ liệu
		// Khi size > 0 => Element xuất hiện
		// Khi size < 0 => Element đang ẩn
		
		List<WebElement> allPopups = driver.findElements(dangkyPopup);
		
		if(allPopups.size() > 0)
		{
			driver.findElement(By.xpath("//input[@id='popup-name']")).sendKeys("Halley");
			driver.findElement(By.xpath("//input[@id='popup-email']")).sendKeys("Halley1458@yahoo.net");
			driver.findElement(By.xpath("//input[@id='popup-phone']")).sendKeys("0154789632");
			
			driver.findElement(By.xpath("//button[@id='close-popup']")).click();
			
			Assert.assertEquals(driver.findElements(dangkyPopup).size(), 0);
		}
		
		driver.findElement(By.xpath("//a[text()='Tất cả khóa học']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='search-courses']")).isDisplayed());
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
	
	public void sleepInsecond (long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}