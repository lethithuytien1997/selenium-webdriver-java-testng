package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_Frame_iFrame {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	//@Test
	public void TC_01_iFrame_KYNA() {
		driver.get("https://kyna.vn/");
		
		//Switch to Facebook Content iFrame
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='face-content']/iframe")));
		sleepInsecond(2);
		Assert.assertEquals(driver.findElement(By.xpath("//div[text()='166K likes']")).getText(), "166K likes");
		
		//Switch ra trang chính
		driver.switchTo().defaultContent();
		sleepInsecond(2);
		
		//Switch vào Chat iframe
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='cs-live-chat']/iframe")));
		sleepInsecond(2);
		driver.findElement(By.cssSelector("div.meshim_widget_Widget")).click();

		driver.findElement(By.cssSelector("input.input_name")).sendKeys("tronh");
		driver.findElement(By.cssSelector("input.input_phone")).sendKeys("0988745632");
		driver.findElement(By.cssSelector("div.meshim_widget_Widget")).click();
		sleepInsecond(3);
		
		//Switch ra trang chính
		driver.switchTo().defaultContent();
		sleepInsecond(2);
		driver.findElement(By.id("live-search-bar")).sendKeys("Excel");
		driver.findElement(By.cssSelector("button.search-button")).click();
		List<WebElement> allCourses = driver.findElements(By.cssSelector("div.content>h4"));
		
		for (WebElement course : allCourses) {
			System.out.println(course);
			Assert.assertTrue(course.getText().contains("Excel"));
		}
		
	}
	
	@Test
	public void TC_02_Frame_HDFC_Bank() {
		driver.get("https://netbanking.hdfcbank.com/netbanking/");
		
		driver.switchTo().frame("login_page");
		
		driver.findElement(By.xpath("//input[@name='fldLoginUserId']")).sendKeys("automationtest");
		driver.findElement(By.xpath("//a[text()='CONTINUE']")).click();
		sleepInsecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//input[@name='fldPassword']")).isDisplayed());
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