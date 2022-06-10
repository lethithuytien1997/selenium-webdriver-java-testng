package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_Default_Alert {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;
	Alert alert;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Accept_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();

		// Switch vào Alert sau khi Alert bật lên
		alert = driver.switchTo().alert();

		// Verify title của Alert
		Assert.assertEquals(alert.getText(), "I am a JS Alert");

		// Nhấn Ok
		alert.accept();

		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(),
				"You clicked an alert successfully");
	}

	@Test
	public void TC_02_Confirm_Alert() {
		driver.navigate().refresh();
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();

		alert = driver.switchTo().alert();

		Assert.assertEquals(alert.getText(), "I am a JS Confirm");

		// Cancel Alert
		alert.dismiss();

		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You clicked: Cancel");
	}

	@Test
	public void TC_03_Promt_Alert() {
		driver.navigate().refresh();
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();

		alert = driver.switchTo().alert();

		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		
		//SendKeys into Alert
		alert.sendKeys("Tien");

		// Accept Alert
		alert.accept();

		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You entered: Tien");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
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