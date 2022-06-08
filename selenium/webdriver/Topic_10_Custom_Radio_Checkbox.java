package webdriver;

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

public class Topic_10_Custom_Radio_Checkbox {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	//@Test
	public void TC_01_Example() {
		driver.get("https://material.angular.io/components/checkbox/examples");
		WebElement checkbox = driver.findElement(By.xpath("//span[text()='Checked']/preceding-sibling::span/input"));
		WebElement rad = driver.findElement(By.xpath("//span[text()='Before']/preceding-sibling::span/input"));
		//Select
		jsExecutor.executeScript("arguments[0].click()", checkbox);
		sleepInsecond(3);
		Assert.assertTrue(checkbox.isSelected());
		
		jsExecutor.executeScript("arguments[0].click()", rad);
		sleepInsecond(3);
		Assert.assertTrue(rad.isSelected());
		
		//De-Select
		jsExecutor.executeScript("arguments[0].click()", checkbox);
		sleepInsecond(3);
		Assert.assertFalse(checkbox.isSelected());
		
		jsExecutor.executeScript("arguments[0].click()", rad);
		sleepInsecond(3);
		Assert.assertTrue(rad.isSelected());
	}
	
	//@Test
	public void TC_02_Custom_Checkbox_Radio() {
		//Radio	
		driver.get("https://material.angular.io/components/radio/examples");
		WebElement rad = driver.findElement(By.xpath("//span[text()=' Summer ']/preceding-sibling::span/input"));
		
		jsExecutor.executeScript("arguments[0].click()", rad);
		sleepInsecond(1);
		Assert.assertTrue(rad.isSelected());
		
		jsExecutor.executeScript("arguments[0].click()", rad);
		sleepInsecond(1);
		Assert.assertTrue(rad.isSelected());
		
		//Checkbox
		driver.get("https://material.angular.io/components/checkbox/examples");
		WebElement checkbox1 = driver.findElement(By.xpath("//span[text()='Checked']/preceding-sibling::span/input"));
		WebElement checkbox2 = driver.findElement(By.xpath("//span[text()='Indeterminate']/preceding-sibling::span/input"));
		
		jsExecutor.executeScript("arguments[0].click()", checkbox1);
		sleepInsecond(1);
		Assert.assertTrue(checkbox1.isSelected());
		
		jsExecutor.executeScript("arguments[0].click()", checkbox2);
		sleepInsecond(1);
		Assert.assertTrue(checkbox2.isSelected());
		
		jsExecutor.executeScript("arguments[0].click()", checkbox1);
		sleepInsecond(1);
		Assert.assertFalse(checkbox1.isSelected());
		
		jsExecutor.executeScript("arguments[0].click()", checkbox2);
		sleepInsecond(1);
		Assert.assertFalse(checkbox2.isSelected());
		
	}
	
	@Test
	public void TC_03_Google_Checkbox_Radio() {
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		WebElement canthoRadio = driver.findElement(By.xpath("//div[@aria-label='Cần Thơ']"));
		Assert.assertEquals(canthoRadio.getAttribute("aria-checked"), "false");
		
		canthoRadio.click();
		
		Assert.assertEquals(canthoRadio.getAttribute("aria-checked"), "true");
		
		WebElement miquangRadio = driver.findElement(By.xpath("//div[@aria-label='Mì Quảng']"));
		Assert.assertEquals(miquangRadio.getAttribute("aria-checked"), "false");
		
		miquangRadio.click();
		
		Assert.assertEquals(miquangRadio.getAttribute("aria-checked"), "true");
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