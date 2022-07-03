package webdriver;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_Wait_Part_V {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	FluentWait<WebDriver> fluentWait;
	WebDriverWait explicitWait;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Enough(){
		driver.get("https://automationfc.github.io/dynamic-loading/");
		fluentWait = new FluentWait<WebDriver> (driver);
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		fluentWait.withTimeout(Duration.ofSeconds(6))
		.pollingEvery(Duration.ofSeconds(1))
		.ignoring(NoSuchElementException.class)
		.until(new Function<WebDriver, Boolean>() {

			@Override
			public Boolean apply(WebDriver driver) {
				boolean textStatus = driver.findElement(By.cssSelector("div#finish>h4")).isDisplayed();
				return textStatus;
			}
			
		});
	}
	
	//@Test
	public void TC_02_Less(){
		
	}
	
	//@Test
	public void TC_03_More(){
		
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