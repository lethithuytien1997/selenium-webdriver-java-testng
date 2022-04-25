package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Selenium_Locator {
	// Khai báo 1 biến Driver đại diện cho Selenium WebDriver
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		// Set Geckodriver giao tiếp giữa Browser và Code
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		
		// Bật trình duyệt Firefox
		driver = new FirefoxDriver();
		
		// Set thời gian tìm Element
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// Bật trình duyệt to lên
		driver.manage().window().maximize();
		
		// Mở trang web mới
		driver.get("https://www.facebook.com/");
	}

	@Test
	public void TC_01_ValidateCurrentUrl() {
		// Có 8 loại Locator
		// Id
		driver.findElement(By.id("email"));
		// Class
		driver.findElement(By.className("fb_logo"));
		// Name
		driver.findElement(By.name("email"));
		// Tag name
		driver.findElement(By.tagName("a"));
		// Link text
		driver.findElement(By.linkText("Tiếng Việt"));
		// Partial link text
		driver.findElement(By.partialLinkText("Tiếng Việt"));
		driver.findElement(By.partialLinkText("Tiếng"));
		driver.findElement(By.partialLinkText("Việt"));
		driver.findElement(By.partialLinkText("iếng Việ"));
		// Css
		driver.findElement(By.cssSelector("input[id='email']"));
		driver.findElement(By.cssSelector("input#email"));
		driver.findElement(By.cssSelector("#email"));
		
		driver.findElement(By.cssSelector("img[class='fb_logo _8ilh img']"));
		driver.findElement(By.cssSelector("img.fb_logo"));
		driver.findElement(By.cssSelector(".fb_logo"));
		
		driver.findElement(By.cssSelector("input[name='email']"));
		driver.findElement(By.cssSelector("a"));
		
		//Css không làm việc với text => Dùng thuộc tính khác của thẻ a để làm việc
		driver.findElement(By.cssSelector("a[title='Vietnamese']"));
		driver.findElement(By.cssSelector("a[onclick*='vi_VN']"));
		driver.findElement(By.cssSelector("a[title*='Viet']"));
		// Xpath
		driver.findElement(By.xpath("//input[@id='email']"));
		driver.findElement(By.xpath("//img[@class='fb_logo _8ilh img']"));
		driver.findElement(By.xpath("//img[contains(@class, 'fb_logo')]"));
		driver.findElement(By.xpath("//img[starts-with(@class, 'fb_logo')]"));
		driver.findElement(By.xpath("//input[@name='email']"));
		driver.findElement(By.xpath("//a[text()='Tiếng Việt']"));
		driver.findElement(By.xpath("//a[contains(text(), 'Tiếng Việt')]"));
		driver.findElement(By.xpath("//a[contains(text(), 'Việt')]"));
	}

	@Test
	public void TC_02_() {
		
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