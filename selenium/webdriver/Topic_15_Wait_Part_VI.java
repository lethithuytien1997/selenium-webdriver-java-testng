package webdriver;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_Wait_Part_VI {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
	Actions action;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		// driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		action = new Actions(driver);
	}

	//@Test
	public void TC_01_OrangeHRM() {
		driver.get("https://opensource-demo.orangehrmlive.com");
		driver.findElement(By.name("txtUsername")).sendKeys("Admin");
		driver.findElement(By.name("txtPassword")).sendKeys("admin123");
		driver.findElement(By.name("Submit")).click();
		
		Assert.assertTrue(isPageLoadedSuccess());
		
		Assert.assertTrue(driver.findElement(By.id("div_graph_display_emp_distribution")).isDisplayed());
	}

	@Test
	public void TC_02_Test_Auto_Blog() {
		driver.get("https://blog.testproject.io/");
		if(driver.findElement(By.xpath("//div[@class='mailch-wrap']")).isDisplayed()) {
			driver.findElement(By.id("close-mailch")).click();
		}
		action.moveToElement(driver.findElement(By.xpath("//section[@id='search-2']//input[@name='s']"))).perform();
		Assert.assertTrue(isPageLoadedSuccess());
		driver.findElement(By.xpath("//section[@id='search-2']//input[@name='s']")).sendKeys("Selenium");
		driver.findElement(By.xpath("//section[@id='search-2']//span[@class='glass']")).click();
		Assert.assertTrue(isPageLoadedSuccess());
		List<WebElement> postTitles = driver.findElements(By.xpath("//h3[@class='post-title']"));
		for (WebElement title : postTitles) {
			Assert.assertTrue(title.getText().contains("Selenium"));
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public boolean isPageLoadedSuccess() {
		explicitWait = new WebDriverWait(driver, 30);
		jsExecutor = (JavascriptExecutor) driver;

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {

			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {

			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};
		
		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
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